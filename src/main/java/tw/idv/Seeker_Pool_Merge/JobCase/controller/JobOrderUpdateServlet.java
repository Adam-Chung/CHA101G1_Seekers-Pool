package tw.idv.Seeker_Pool_Merge.JobCase.controller;

import static tw.idv.Seeker_Pool_Merge.JobCase.util.CommonUtil.writePojo2Json;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import tw.idv.Seeker_Pool_Merge.JobCase.dao.JobOrderDao;
import tw.idv.Seeker_Pool_Merge.JobCase.dao.Impl.JobOrderDaoImpl;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.JobOrderVO;

@WebServlet("/JobOrderUpdateServlet")
public class JobOrderUpdateServlet extends HttpServlet {
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
		System.out.println("request.getContentType(): " + req.getContentType());
//		System.out.println("進入update");
		String joNo = req.getParameter("joNo");
//		System.out.println("joNo : " + joNo);
		String comMemId = req.getParameter("comMemId");
//		System.out.println("comMemId : " + comMemId);
		String jcNo = req.getParameter("jcNo");
//		System.out.println("jcNo : " + jcNo);
		String jobPublishedNum = req.getParameter("jobPublishedNum");
//		System.out.println("jobPublishedNum : " + jobPublishedNum);
		String jobPublishedTopNum = req.getParameter("jobPublishedTopNum");
//		System.out.println("jobPublishedTopNum : " + jobPublishedTopNum);
		String jcDeadline = req.getParameter("jcDeadline");
//		System.out.println("jcDeadline : " + jcDeadline);
		String jcStatus = req.getParameter("jcStatus");
//		System.out.println("jcStatus : " + jcStatus);

		JoVO.setJoNo(Integer.valueOf(joNo));
		JoVO.setComMemId(Integer.valueOf(comMemId));
		JoVO.setJcNo(Integer.valueOf(jcNo));
		JoVO.setJobPublishedNum(Integer.valueOf(jobPublishedNum));
		JoVO.setJobPublishedTopNum(Integer.valueOf(jobPublishedTopNum));
		JoVO.setJcDeadline(Timestamp.valueOf(jcDeadline));

		JoDao.update(JoVO);
		writePojo2Json(resp, JoVO);

	}

}
