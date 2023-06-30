package tw.idv.Seeker_Pool_Merge.JobCase.controller;

import static tw.idv.Seeker_Pool_Merge.JobCase.util.CommonUtil.writePojo2Json;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import tw.idv.Seeker_Pool_Merge.JobCase.dao.JobCaseDao;
import tw.idv.Seeker_Pool_Merge.JobCase.dao.Impl.JobCaseDaoImpl;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.JobCaseVO;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024, maxRequestSize = 1024 * 1024)
@WebServlet("/JobCaseInsertServlet")
public class JobCaseInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private JobCaseDao JcDao;
//	private JobCaseDao JcDao = new JobCaseDaoImpl();
	private JobCaseVO JcVO = new JobCaseVO();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws SecurityException, IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
//		System.out.println("request.getContentType(): " + req.getContentType());
		
			String str1 = req.getParameter("jcName");
//			System.out.println("jcName : " + str1);
			String str2 = req.getParameter("jcExpTime");
//			System.out.println("jcExpTime : " + str2);
			String str3 = req.getParameter("jcAvailableNum");
//			System.out.println("jcAvailableNum  " + str3);
			String str4 = req.getParameter("jcTop");
//			System.out.println("jcTop : " + str4);
			String str5 = req.getParameter("jcPrice");
//			System.out.println("jcPrice : " + str5);
			String str6 = req.getParameter("jcStatus");
//			System.out.println("jcStatus : " + str6);

			JcVO.setJcName(str1);
			JcVO.setJcExpTime(Integer.valueOf(str2));
			JcVO.setJcAvailableNum(Integer.valueOf(str3));
			JcVO.setJcTop(Integer.valueOf(str4));
			JcVO.setJcPrice(Integer.valueOf(str5));
			JcVO.setJcStatus(Integer.valueOf(str6));
			
			JcDao.insert(JcVO);
//			System.out.println(JcVO);
			writePojo2Json(resp, JcVO);
	}

}
