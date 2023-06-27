package tw.idv.Seeker_Pool_Merge.jamie.controller.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.idv.Seeker_Pool_Merge.jamie.service.CompanyMemberService;
import tw.idv.Seeker_Pool_Merge.jamie.service.impl.CompanyMemberServiceImpl;

@WebServlet("/CompanyFileDownload")
public class FileDownloadServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private CompanyMemberService service = new CompanyMemberServiceImpl();
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		try {
			System.out.println("來下載囉");
			String idStr = req.getParameter("id");
			int comMemId = Integer.parseInt(idStr);
			String filePath = service.getCompanyMemberById(comMemId).getDataUpload();
			
			// 檢查文件是否存在
			File file = new File(filePath);
			if (!file.exists()) {
				// 處理文件不存在的情況
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
			
			// 得到文件名
			String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
			System.out.println(fileName);
			
			// 設置瀏覽器能讀取內容為下載
			resp.setHeader("content-type", "application/octet-stream");
			resp.setContentType("application/zip");
			// 告訴瀏覽器該響應的內容是一個附件，並指定附件的文件名
			resp.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
			
			// 讀取文件
			try (FileInputStream in = new FileInputStream(filePath);
				 OutputStream out = resp.getOutputStream()) {
				
				int len;
				byte[] buffer = new byte[1024];
				while ((len = in.read(buffer)) > 0) {
					out.write(buffer, 0, len);
				}
			} catch (IOException ex) {
				// 處理讀取或寫入文件的錯誤
				// ...
				throw ex;
			}
		} catch (NumberFormatException ex) {
			// 處理ID格式不正確的錯誤
			// ...
			throw new ServletException(ex);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
