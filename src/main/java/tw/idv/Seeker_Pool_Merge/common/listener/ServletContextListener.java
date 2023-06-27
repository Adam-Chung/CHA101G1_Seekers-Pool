package tw.idv.Seeker_Pool_Merge.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;


/* 解決錯誤 appears to have started a thread named [mysql-cj-abandoned-connection-cleanup] but has failed to stop it. This is very likely to create a memory leak. Stack trace of thread:
 * 這是因為发生这种异常的原因有两个：
一是Tomcat在 6.0.24 后加入了一个 memory leak detection 检查机制，当我们程序中引入了JDBC 4.0 驱动时会自动注册到Tomcat容器中，但是却没有提供自动销毁机制。
二是我们Mysql驱动中自带的AbandonedConnectionCleanupThread类，会在我们加载jdbc驱动时启动一个newSingleThreadExecutor的线程池，这个也不会自动释放。

解决方式如下（注册一个ServletContextListener）讓上面提到thread釋放
 * 為了讓重啟tomcat不報thread的錯，撰寫以下程式碼
 * http://www.itfsw.com/blog/post/2019/10/21/jiejue-registered-the-jdbc-driver-com-mysql-cj-jdbc-driver-but-failed-to-unregister-it-when-the-we/
 */


@WebListener
public class ServletContextListener implements javax.servlet.ServletContextListener {
	
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("!!!!!!!!記得開啟Redis!!!!!!!!!!!!!!!!!!!!!");
	}
	

	 @Override
	    public void contextDestroyed(ServletContextEvent sce) {
	        try {
	            // com.mysql.cj.jdbc.AbandonedConnectionCleanupThread Or com.mysql.jdbc.AbandonedConnectionCleanupThread
	            Class<?> cls = Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread");
	            Method method = cls.getMethod("checkedShutdown");
	            method.invoke(null);
	            
	            
	            
	        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
	            e.printStackTrace();
	        }
	        // Now deregister JDBC drivers in this context's ClassLoader:
	        // Get the webapp's ClassLoader
	        ClassLoader cl = Thread.currentThread().getContextClassLoader();
	        // Loop through all drivers
	        Enumeration<Driver> drivers = DriverManager.getDrivers();
	        while (drivers.hasMoreElements()) {
	            Driver driver = drivers.nextElement();
	            if (driver.getClass().getClassLoader() == cl) {
	                // This driver was registered by the webapp's ClassLoader, so deregister it:
	                try {
//	                	e.printStackTrace();
	                    DriverManager.deregisterDriver(driver);
	                } catch (SQLException ex) {
	                	ex.printStackTrace();
	                }
	            } else {
	                // driver was not registered by the webapp's ClassLoader and may be in use elsewhere
//	                log.trace("Not deregistering JDBC driver {} as it does not belong to this webapp's ClassLoader", driver);
	            }
	        }
	    }

}
