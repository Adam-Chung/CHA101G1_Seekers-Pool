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
@WebServlet("/JobCaseUpdateServlet")
public class JobCaseUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private JobCaseDao JcDao = new JobCaseDaoImpl();
//	private JobCaseDao JcDao = new JobCaseDaoImpl();
	private JobCaseVO JcVO = new JobCaseVO();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws SecurityException, IOException, ServletException {
//		System.out.println("進入 JobCaseUpdateServlet 的 doPost 方法");
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		System.out.println(action);
//		System.out.println("request.getContentType(): " + req.getContentType());
		
		//  開始 刪除 單一 資料
		if ("delete".equals(action)) {
//			System.out.println("進入delete");

			/** 1.接收請求參數 **/
			Integer jcNo = Integer.valueOf(req.getParameter("jcNo"));

			/** 2.開始刪除資料 **/
			if (jcNo != null) {
				JobCaseVO jc = JcDao.selectByNo(jcNo);
				if (jc != null) {
					JcDao.delete(jc);
				}
			}
		}

		// 開始 更新 單一 資料
		if ("update".equals(action)) {
//			System.out.println("進入update");
			String jcNo = req.getParameter("jcNo");
//			System.out.println("jcNo : " + jcNo);
			String jcName = req.getParameter("jcName");
//			System.out.println("jcName : " + jcName);
			String jcExpTime = req.getParameter("jcExpTime");
//			System.out.println("jcExpTime : " + jcExpTime);
			String jcAvailableNum = req.getParameter("jcAvailableNum");
//			System.out.println("jcAvailableNum : " + jcAvailableNum);
			String jcTop = req.getParameter("jcTop");
//			System.out.println("jcTop : " + jcTop);
			String jcPrice = req.getParameter("jcPrice");
//			System.out.println("jcPrice : " + jcPrice);
			String jcStatus = req.getParameter("jcStatus");
//			System.out.println("jcStatus : " + jcStatus);

			JcVO.setJcNo(Integer.valueOf(jcNo));
			JcVO.setJcName(jcName);
			JcVO.setJcExpTime(Integer.valueOf(jcExpTime));
			JcVO.setJcAvailableNum(Integer.valueOf(jcAvailableNum));
			JcVO.setJcTop(Integer.valueOf(jcTop));
			JcVO.setJcPrice(Integer.valueOf(jcPrice));
			JcVO.setJcStatus(Integer.valueOf(jcStatus));

			JcDao.update(JcVO);
			writePojo2Json(resp, JcVO);
		}
	}
}
