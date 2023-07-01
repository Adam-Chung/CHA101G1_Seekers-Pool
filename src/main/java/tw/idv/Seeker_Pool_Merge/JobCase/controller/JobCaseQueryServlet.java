package tw.idv.Seeker_Pool_Merge.JobCase.controller;

import static tw.idv.Seeker_Pool_Merge.JobCase.util.CommonUtil.writePojo2Json;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import tw.idv.Seeker_Pool_Merge.JobCase.dao.JobCaseDao;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.ComOrderQueryVO;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.JobCaseVO;

@WebServlet("/JobCaseQueryServlet")
public class JobCaseQueryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private JobCaseDao JcDao;
	
//	private JobCaseDao JcDao = new JobCaseDaoImpl();
//	private JobCaseVO JcVO = new JobCaseVO();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws SecurityException, IOException, ServletException {
		resp.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		System.out.println("request.getContentType(): " + req.getContentType());
		/***************************
		 * 開始 查詢全部資料 轉交
		 ****************************************/
//		System.out.println(action);
		if ("selectAll".equals(action)) {
//			System.out.println("yes");
			List<JobCaseVO> list = JcDao.selectAll();
//			System.out.println(list);
			writePojo2Json(resp, list);
		}

		/***************************
		 * 開始 查詢單一資料 轉交
		 ****************************************/
		if ("selectByName".equals(action)) {
//			System.out.println("進入 selectByName");
//			List<String> errorMsgs = new LinkedList<String>();
//			req.setAttribute("errorMsgs", errorMsgs);

			/** 1.接收請求參數 - 輸入格式的錯誤處理 **/
			String str_jcName = req.getParameter("queryName");
//			System.out.println(str_jcName);

//			if (str_jcNo == null || str_jcNo.trim().isEmpty()) {
//				errorMsgs.add("請輸入方案編號");
//			} else if (!str_jcNo.matches("\\d+")) {
//				errorMsgs.add("方案編號格式不正確(ex:方案一)");
//			}

//			if (!errorMsgs.isEmpty()) {
//				// Forward to the appropriate error handling mechanism and display the error
//				// messages
//				RequestDispatcher failureView = req.getRequestDispatcher("../../jobcase/jobcase.html");
//				failureView.forward(req, resp);
//				return;
//			}

			// Convert the validated jcNo to an Integer
//			Integer jcNo = Integer.valueOf(str_jcNo);

			// Perform the data query using jcNo
			JobCaseVO jc = JcDao.selectByCaseName(str_jcName);
			writePojo2Json(resp, jc);

		}

		/***************************
		 * 開始 查詢單一資料 轉交
		 ****************************************/
		if ("selectByOrderNo".equals(action)) {
//			System.out.println("進入 selectByOrderNo");
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/** 1.接收請求參數 - 輸入格式的錯誤處理 **/
			String str_jcNo = req.getParameter("joNo");
//			System.out.println(str_jcNo);

			if (str_jcNo == null || str_jcNo.trim().isEmpty()) {
				errorMsgs.add("請輸入訂單編號");
			} else if (!str_jcNo.matches("\\d+")) {
				errorMsgs.add("訂單編號格式不正確(ex:1)");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("../../jobcase/jobcase.html");
				failureView.forward(req, resp);
				return;
			}

			// Convert the validated jcNo to an Integer
			Integer joNo = Integer.valueOf(str_jcNo);
			List<ComOrderQueryVO> list = JcDao.selectOrderNo(joNo);
//			System.out.println("list" + list);
			writePojo2Json(resp, list);

		}
		/***************************
		 * 開始 查詢訂單資料 轉交
		 ****************************************/
		if ("selectByOrder".equals(action)) {
//			System.out.println("進入 selectByOrder");
//			System.out.println(action);
			ComOrderQueryVO covo=new ComOrderQueryVO();
			List<ComOrderQueryVO> list = JcDao.selectOrderCase(covo);
//			System.out.println("list" + list);
			writePojo2Json(resp, list);
			
		}

		/***************************
		 * 開始 查詢單一 方案名稱 轉交
		 ****************************************/
		if ("selectByCaseName".equals(action)) {
//			System.out.println(action);
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/** 1.接收請求參數 - 輸入格式的錯誤處理 **/
			String str = req.getParameter("jcName");
//			System.out.println(str);

			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入正確方案名稱()");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("#");
				failureView.forward(req, resp);
				return;
			}

			String jcName = null;
			try {
				jcName = str.trim();
			} catch (Exception e) {
				errorMsgs.add("方案名稱不正確");
			}

			/** 2.開始查詢資料 **/
			JobCaseVO jc = JcDao.selectByCaseName(jcName);
//			System.out.println("jc : " + jc);
			writePojo2Json(resp, jc);
		}

	}

}
