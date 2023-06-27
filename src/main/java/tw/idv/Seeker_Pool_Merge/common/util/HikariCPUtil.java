package tw.idv.Seeker_Pool_Merge.common.util;

import java.io.InputStream;

import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.HikariConfig;
import java.util.Properties;

/**
 * 取Hikari.properties檔案方式獲取連線池
 * properties要放在resource裡面，或其他地方都可以?
 * @author fong
 *
 */
public class HikariCPUtil {
    private static final String CONFIG_FILE_PATH = "Hikari.properties";
    private static HikariDataSource dataSource;

    static { 
        try {
            ClassLoader classLoader = HikariCPUtil.class.getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(CONFIG_FILE_PATH);

            Properties properties = new Properties();
            properties.load(inputStream);

            HikariConfig config = new HikariConfig(properties);
            dataSource = new HikariDataSource(config);

        } catch (Exception e) {
            // 异常处理
            e.printStackTrace();
            dataSource = null;
        }
    }

    public static HikariDataSource getDataSource() {
        return dataSource;
    }
}

