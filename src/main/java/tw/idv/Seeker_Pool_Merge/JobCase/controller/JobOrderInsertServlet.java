package tw.idv.Seeker_Pool_Merge.JobCase.controller;

import static tw.idv.Seeker_Pool_Merge.JobCase.util.CommonUtil.writePojo2Json;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import tw.idv.Seeker_Pool_Merge.JobCase.dao.JobDao;
import tw.idv.Seeker_Pool_Merge.JobCase.dao.JobOrderDao;
import tw.idv.Seeker_Pool_Merge.JobCase.dao.Impl.JobDaoImpl;
import tw.idv.Seeker_Pool_Merge.JobCase.dao.Impl.JobOrderDaoImpl;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.JobOrderVO;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.JobVO;

@WebServlet("/JobOrderInsertServlet")
public class JobOrderInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private JobOrderDao JoDao;
//	private JobOrderDao JoDao = new JobOrderDaoImpl();
	private JobOrderVO JoVO = new JobOrderVO();
	@Autowired
	private JobDao JobDao;
//	private JobDao JobDao = new JobDaoImpl();
	private JobVO JobVo = new JobVO();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws SecurityException, IOException, ServletException {
		req.setCharacterEncoding("UTF-8");
//		System.out.println("request.getContentType(): " + req.getContentType());
//		System.out.println("進入Insert");

		String jcNo = req.getParameter("jcNo");
//		System.out.println("jcNo : " + jcNo);
		String jcDeadline = req.getParameter("jcDeadline");
//		System.out.println("jcDeadline : " + jcDeadline);
		
		DateFormat inputFormat = new SimpleDateFormat("yyyy-M-d");
		DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long time = 0;
		try {
			time = inputFormat.parse(jcDeadline).getTime();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Timestamp timestamp = new Timestamp(time);
		//動態
		int comMemId = (int) req.getSession().getAttribute("companyMember");
		// Integer comMemId = 6;
		JoVO.setJcNo(Integer.valueOf(jcNo));
		JoVO.setJcDeadline(timestamp);
		JoVO.setComMemId(comMemId);

		if(JoDao.insert(JoVO)) {
			writePojo2Json(resp, JoVO);
			Integer comMemId1 = 6;
			JobVo.setComMemId(comMemId1);
			JobDao.updateJoStatus(JobVo);
		}
		
		

	}

}
