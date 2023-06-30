package tw.idv.Seeker_Pool_Merge.Administrator.controller;

import static tw.idv.Seeker_Pool_Merge.Administrator.util.CommonUtil.writePojo2Json;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import tw.idv.Seeker_Pool_Merge.Administrator.dao.AdministratorDao;
import tw.idv.Seeker_Pool_Merge.Administrator.dao.Impl.AdministratorDaoImpl;
import tw.idv.Seeker_Pool_Merge.Administrator.vo.AdministratorVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024, maxRequestSize = 1024 * 1024)
@WebServlet("/AdministratorServlet")
public class AdministratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private AdministratorDao admDao;
	
//	private AdministratorDao admDao = new AdministratorDaoImpl();
	private AdministratorVO admVO = new AdministratorVO();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(new Date());
//		System.out.println("request.getContentType(): " + req.getContentType());
		/*************************** 開始 查詢全部資料 轉交 ****************************************/
//		System.out.println(action);
		if ("getAll".equals(action)) {
			System.out.println("yes");
			List<AdministratorVO> list = admDao.getAll();
			writePojo2Json(resp,list);
			System.out.println(new Date());
			return;
		}

		/*************************** 開始 查詢單一資料 轉交 ****************************************/
		if ("getOne_For_Display".equals(action)) {
//			System.out.println(action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/** 1.接收請求參數 - 輸入格式的錯誤處理 **/
			String str = req.getParameter("admId");
//			System.out.println(str);

			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入員工編號");
			} else if (!str.matches("\\d+")) {
				errorMsgs.add("員工編號格式不正確(ex:1)");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/administrator/administrator.html");
				failureView.forward(req, resp);
				return;
			}

			Integer admID=Integer.valueOf(str.trim());
			/** 2.開始查詢資料 **/
			AdministratorVO adm = admDao.findByPrimaryKey(admID);
			writePojo2Json(resp,adm);
		}
		
		
		/*************************** 開始 新增 單一 資料 ****************************************/
		if ("insert".equals(action)) {
			String str2 = req.getParameter("admName");
			String str3 = req.getParameter("admPosition");
			String str4 = req.getParameter("admAccount");
			String str5 = req.getParameter("admPassword");
			String str6 = req.getParameter("admStatus");
			
			admVO.setAdmName(str2);
			admVO.setAdmPosition(Integer.valueOf(str3));
			admVO.setAdmAccount(str4);
			admVO.setAdmPassword(str5);
			admVO.setAdmStatus(Integer.valueOf(str6));
			
			/*-- 抓圖片input並儲存於阿飄路徑 --*/
			Part filepart = null;
			String filename = null;

			try {
				filepart = req.getPart("admPhoto");
			} catch (Exception e) {
				e.printStackTrace();
			} 

			if (filepart != null) { //有照片更新
				filename = filepart.getSubmittedFileName();
				
				String saveDirectory = "/images_uploaded"; // 上傳檔案的目的地目錄; (不要在註解@處指定目的地路徑)
				String realPath = getServletContext().getRealPath(saveDirectory);
				File fsaveDirectory = new File(realPath);
				if (!fsaveDirectory.exists()) {
					fsaveDirectory.mkdirs();
				} // 於 ContextPath 之下,自動建立目地目錄，mkdirs()可以建多個資料夾，mkdir()只能建一個

				File f = new File(fsaveDirectory, filename); // 建立一個檔案，用真實路徑(阿飄路徑)(連動端，實際運作取用)
				filepart.write(f.toString()); // part類別的方法write() 利用File物件, 寫入指定目地目錄,上傳成功
				// 將路徑存入物件中
				admVO.setAdmPhoto(req.getContextPath() + saveDirectory + "/" + filename);
				admDao.insert(admVO);
				
				// 轉為json寫回客戶端
				String jsonStr = new JSONObject(admVO).toString();
				resp.getWriter().write(jsonStr);
			} 
		}
	
		
		/*************************** 開始　刪除 單一 資料 ****************************************/
		if ("delete".equals(action)) {
			
			/** 1.接收請求參數 **/
			Integer admId = Integer.valueOf(req.getParameter("admId"));
			
			/** 2.開始刪除資料 **/
			if(admId != null) {
		        AdministratorVO adm = admDao.findByPrimaryKey(admId);
		        if (adm != null) {
		            admDao.delete(adm);
		        }
			}
		}
		
		/*************************** 開始 更新 單一 資料 ****************************************/
		if ("update".equals(action)) {
			String admId = req.getParameter("admId");
			String admName = req.getParameter("admName");
			String admPosition = req.getParameter("admPosition");
			String admAccount = req.getParameter("admAccount");
			String admPassword = req.getParameter("admPassword");
			String admStatus = req.getParameter("admStatus");
			admVO.setAdmId(Integer.valueOf(admId));
			admVO.setAdmName(admName);
			admVO.setAdmPosition(Integer.valueOf(admPosition));
			admVO.setAdmAccount(admAccount);
			admVO.setAdmPassword(admPassword);
			admVO.setAdmStatus(Integer.valueOf(admStatus));
			
			/*-- 抓圖片input並儲存於阿飄路徑 --*/
			Part filepart = null;
			String filename = null;

			try {
				filepart = req.getPart("admPhoto");
			} catch (Exception e) {
				e.printStackTrace();
			} 

			if (filepart != null) { 
				filename = filepart.getSubmittedFileName();
				
				String saveDirectory = "/images_uploaded"; 
				String realPath = getServletContext().getRealPath(saveDirectory);
				File fsaveDirectory = new File(realPath);
				if (!fsaveDirectory.exists()) {
					fsaveDirectory.mkdirs();
				} 
				File f = new File(fsaveDirectory, filename);
				filepart.write(f.toString()); 
				// 將路徑存入物件中
				admVO.setAdmPhoto(req.getContextPath() + saveDirectory + "/" + filename);
				System.out.println("我是servlet=====" + admVO);
				admDao.update(admVO);
				
				// 轉為json寫回客戶端
				String jsonStr = new JSONObject(admVO).toString();
				resp.getWriter().write(jsonStr);
			}
		}
	}
}