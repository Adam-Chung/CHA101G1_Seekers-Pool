package tw.idv.Seeker_Pool_Merge.xuan.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import tw.idv.Seeker_Pool_Merge.xuan.dao.impl.OnlineCourseDaoImpl;
import tw.idv.Seeker_Pool_Merge.xuan.vo.OnlineCourseVo;

@WebServlet("/CourseAddServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 100) // 100MB
public class CourseAddServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private OnlineCourseDaoImpl onlineCourseDaoImpl = new OnlineCourseDaoImpl();

	public void doPost(HttpServletRequest req,HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		String onTitle = req.getParameter("onTitle");
		String onIndex = req.getParameter("onIndex");
		String onStatus = req.getParameter("onStatus");

		Part filePart = req.getPart("onPic"); // 取得上傳的檔案
		Part filePart2  =req.getPart("onVideo");

		String imgFileName = getFileName(filePart); // 取得課程封面檔案名稱
		String videoFileName = getFileName(filePart2); // 取得課程影片檔案名稱
		
		String saveDirectory = "/video_uploaded"; // 上傳檔案的目的地目錄; (不要在註解@處指定目的地路徑)
		String realPath = getServletContext().getRealPath(saveDirectory);
		
		File fsaveDirectory = new File(realPath);
		if (!fsaveDirectory.exists()) {
			 fsaveDirectory.mkdirs(); 
			}// 於 ContextPath 之下,自動建立目地目錄，mkdirs()可以建多個資料夾，mkdir()只能建一個
			
		
		File f = new File(fsaveDirectory, imgFileName);  //真實路徑(阿飄路徑)(連動端，實際運作取用) 
		filePart.write(f.toString()); 
		
		File f2 = new File(fsaveDirectory, videoFileName);  //真實路徑(阿飄路徑)(連動端，實際運作取用) 
		filePart2.write(f2.toString()); 
		
		// 建立 OnlineCourseVo 物件
		OnlineCourseVo onlineCourse = new OnlineCourseVo();
		onlineCourse.setOnTitle(onTitle);
		onlineCourse.setOnStatus(Integer.valueOf(onStatus));
		onlineCourse.setOnIndex(onIndex);
		onlineCourse.setOnPic(req.getContextPath()+saveDirectory+"/"+imgFileName); // 儲存檔案路徑到 OnlineCourseVo 物件
		onlineCourse.setOnVideo(req.getContextPath()+saveDirectory+"/"+videoFileName); // 設定影片路徑，如果需要也可進行上傳影片的處理

		// 呼叫 Dao 方法將 OnlineCourseVo 物件儲存到資料庫
		onlineCourseDaoImpl.createOnlineCourse(onlineCourse);
		
		System.out.println("upload success");
		
		try {
	        Thread.sleep(1500); // 延遲5秒後
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
		resp.sendRedirect(req.getContextPath() + "/back-end/course/CourseAdd.html");
	}

		//隨機生成檔名
	public String getFileName(Part filePart) {
	    String submittedFileName = filePart.getSubmittedFileName();
	    String extension = submittedFileName.substring(submittedFileName.lastIndexOf("."));
	    String randomFileName = UUID.randomUUID().toString() + extension;
	    return randomFileName;
	}
}

