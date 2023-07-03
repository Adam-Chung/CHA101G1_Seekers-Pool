package tw.idv.Seeker_Pool_Merge.jamie.controller.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;

import tw.idv.Seeker_Pool_Merge.jamie.service.CompanyMemberService;
import tw.idv.Seeker_Pool_Merge.jamie.service.impl.CompanyMemberServiceImpl;
import tw.idv.Seeker_Pool_Merge.jamie.vo.CompanyMemberVo;
import tw.idv.Seeker_Pool_Merge.jamie.vo.ResultInfo;

@WebServlet("/CompanyMemberUpdateInfo")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024, maxRequestSize = 1024 * 1024)
public class UpdateInfoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private CompanyMemberService service = new CompanyMemberServiceImpl();
//	CompanyMemberVo testCompanyMember = new CompanyMemberVo();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		
		Part filePart = null;
		String fileName = null;
		ResultInfo resultInfo = new ResultInfo();
		// 測試用程式碼
//		testCompanyMember.setComMemId(51);
//		req.getSession().setAttribute("companyMember", testCompanyMember);
//		CompanyMemberVo companyMember = (CompanyMemberVo) req.getSession().getAttribute("companyMember");
//		int id = companyMember.getComMemId();
		
		int id = (int) req.getSession().getAttribute("companyMember");
		CompanyMemberVo loginComMem = service.getCompanyMemberById(id);
		
		PrintWriter out = resp.getWriter();
		
		try {
			filePart = req.getPart("comPicture");
			
			if (filePart != null && filePart.getSize() > 0) {				
				LocalDateTime now = LocalDateTime.now();
		        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
		        String formatDateTime = now.format(formatter);
				fileName = formatDateTime + "-" + filePart.getSubmittedFileName();
//				System.out.println("File name: " + fileName);
				
				String saveDirectory = "/uploads/comPicture";
				String realPath = getServletContext().getRealPath(saveDirectory);
				File fsaveDirectory = new File(realPath);
				if (!fsaveDirectory.exists()) {
				    fsaveDirectory.mkdirs();
				}
				
				File f = new File(fsaveDirectory, fileName);
//				System.out.println("Save directory exists: " + fsaveDirectory.exists());
//				System.out.println("Save directory is a directory: " + fsaveDirectory.isDirectory());
//				System.out.println("File part size: " + filePart.getSize());
				filePart.write(f.toString());
//				System.out.println("New file exists: " + f.exists());
				loginComMem.setComPicture(req.getContextPath() + saveDirectory + "/" + fileName);
				
				// 調用Service更新其他資訊
				Map<String, String[]> map = req.getParameterMap();
				try {
					BeanUtils.populate(loginComMem, map);
				} catch (Exception e) {
					e.printStackTrace();
				}
				service.updateCompanyMember(loginComMem);
				
				resultInfo.setFlag(true);
				out.write(new JSONObject(resultInfo).toString());
//				System.out.println("含照片 更新成功");
				
			} else {  // 沒更新照片
				Map<String, String[]> map = req.getParameterMap();
				try {
					BeanUtils.populate(loginComMem, map);
				} catch (Exception e) {
					e.printStackTrace();
				}				
				service.updateCompanyMember(loginComMem);
				
				resultInfo.setFlag(true);
				out.write(new JSONObject(resultInfo).toString());
//				System.out.println("不含照片 更新成功");
			}			
			out.flush();
            
		} catch (Exception e) {
			resultInfo.setFlag(false);
			resultInfo.setErrorMsg("更新過程中發生錯誤，請稍後再試");
			e.printStackTrace();
			
            out.write(new JSONObject(resultInfo).toString());
            out.flush();
		}
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
