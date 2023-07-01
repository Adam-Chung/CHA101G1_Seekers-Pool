package tw.idv.Seeker_Pool_Merge.JobCase.controller;

import static tw.idv.Seeker_Pool_Merge.JobCase.util.CommonUtil.writePojo2Json;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import tw.idv.Seeker_Pool_Merge.JobCase.dao.JobOrderDao;
import tw.idv.Seeker_Pool_Merge.JobCase.dao.Impl.JobOrderDaoImpl;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.JobOrderVO;

@WebServlet("/JobUpdateTopServlet")
public class JobUpdateTopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private JobOrderDao JoDao;
//	private JobOrderDao JoDao = new JobOrderDaoImpl();
	private JobOrderVO JoVO = new JobOrderVO();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws SecurityException, IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//		System.out.println("request.getContentType(): " + req.getContentType());

		/*****************************
		 * 開始 新增 單一 資料            *
		 *****************************/
		
		if ("insert".equals(action)) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//			System.out.println("VVV");

			String comMemId = req.getParameter("comMemId");
			String jcNo = req.getParameter("jcNo");
			String jobPublishedNum = req.getParameter("jobPublishedNum");
			String jobPublishedTopNum = req.getParameter("jobPublishedTopNum");
			String jcDeadline = req.getParameter("jcDeadline");
			
			JoVO.setComMemId(Integer.valueOf(comMemId));
			JoVO.setJcNo(Integer.valueOf(jcNo));
			JoVO.setJobPublishedNum(Integer.valueOf(jobPublishedNum));
			JoVO.setJobPublishedTopNum(Integer.valueOf(jobPublishedTopNum));
			
			try {
			    java.util.Date utilDate = dateFormat.parse(jcDeadline);
			    Timestamp sqlDate = new java.sql.Timestamp(utilDate.getTime());
			    JoVO.setJcDeadline(sqlDate);
			} catch (Exception e) {
			    e.printStackTrace();
			}
		   
			JoDao.insert(JoVO);
			writePojo2Json(resp, JoVO);
		}
		
		
	}

}
