package tw.idv.Seeker_Pool_Merge.yuquann.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import tw.idv.Seeker_Pool_Merge.yuquann.dao.ReportEnterpriseDao;
import tw.idv.Seeker_Pool_Merge.yuquann.vo.ReportEnterpriseVo;

@WebServlet("/ReportEnterpriseService")
@MultipartConfig(maxFileSize = 10 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
public class ReportEnterpriseService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		//設置請求編碼
		req.setCharacterEncoding("utf-8");
		
		//把要接收的資訊建立好
		String rjt_nostr = req.getParameter("rjtNo");
		int rjtNo = Integer.parseInt(rjt_nostr);
		//以下記得修改時要改成getsession來取值
		int memId = 1;
		int comMemId = 1;
		String reContent = req.getParameter("reContent");
		Date reEndTime = null;
		int reStatus = 0;
		int reResult = 0;
		
		/*================================ 處理圖片檔案的部分 ================================*/
		//圖片檔案需要特殊處理 先使用part類型接收圖片檔案
		Part image = req.getPart("reUpload"); // "reUpload" 是 HTML 表單中文件上傳欄位的名稱
		//取得圖片檔的名字
		String fileName = image.getSubmittedFileName();
		
		//宣告未來存放上傳圖片的資料夾路徑
		//File.separator用於在路徑中分隔目錄和文件名稱。
		//uploadImage是自訂上傳資料夾的名稱
		String uploadPath = getServletContext().getRealPath("") + "uploadImage";
		System.out.println("uploadPath : " + uploadPath);
		
		//儲存檔案至uploadImage資料夾並判斷資料夾是否存在
		File uploadDir = new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		String reUpload = "uploadImage/" + fileName ;
//		System.out.println("reUpload : " + reUpload);
		
		//reUpload為存進去的檔案路徑
		String imageRelativeUrl = uploadPath + File.separator + fileName;
//		System.out.println("imageRelativeUrl : " + imageRelativeUrl);
		 try (InputStream fileContent = image.getInputStream()) {
	            Files.copy(fileContent, new File(imageRelativeUrl).toPath(), StandardCopyOption.REPLACE_EXISTING);
	        }
		/*================================ 處理圖片檔案的部分 ================================*/		

//		ReportEnterpriseVo vo = new ReportEnterpriseVo(rjtNo, reContent, reEndTime, reStatus, reResult, reUpload);
		ReportEnterpriseVo vo = new ReportEnterpriseVo();
		vo.setRjtNo(rjtNo);
		vo.setMemId(memId);
		vo.setComMemId(comMemId);
		vo.setReContent(reContent);
		vo.setReEndTime(null);
		vo.setReStatus(reStatus);
		vo.setReResult(reResult);
		vo.setReUpload(reUpload);
		
		ReportEnterpriseDao dao = new ReportEnterpriseDao();

		try {
			//把respones的編碼設定好
			res.setContentType("text/html;charset=utf-8");
			res.setCharacterEncoding("UTF-8");
			//呼叫DAO的insert方法並檢測是否有成功新增
			int result = dao.insert(vo);
			System.out.println("成功新增行數 : " + result);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	public ReportEnterpriseService() {
		super();
	}
}
