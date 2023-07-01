package tw.idv.Seeker_Pool_Merge.yuquann.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.idv.Seeker_Pool_Merge.yuquann.dao.JobPositionDao;

@WebServlet("/PositionAdd")
public class PositionAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		
		String ptType = req.getParameter("PT_TYPE");
		String ptName = req.getParameter("PT_NAME");
		
		res.setContentType("text/html;charset=utf-8");
		
		JobPositionDao dao  = new JobPositionDao();
		dao.add(ptType, ptName);
		
		res.sendRedirect("/SeekerPool/back-end/job/JobType.html");
		
//		System.out.println("成功新增職務筆數 : " + rowcount);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}
	public PositionAdd() {
		super();
	}
}
