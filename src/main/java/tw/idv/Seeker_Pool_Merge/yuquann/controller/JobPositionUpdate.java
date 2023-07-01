package tw.idv.Seeker_Pool_Merge.yuquann.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.idv.Seeker_Pool_Merge.yuquann.dao.JobPositionDao;

@WebServlet("/JobPositionUpdate")
public class JobPositionUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		
		String ptNOStr = req.getParameter("ptNO");
		int ptNO = Integer.parseInt(ptNOStr);
		String ptType = req.getParameter("ptType");
		String ptName = req.getParameter("ptName");

		JobPositionDao dao = new JobPositionDao();

		dao.update(ptType, ptName, ptNO);
//		System.out.println("成功更改職務規格行數 : " + rowcount);

		res.sendRedirect("http://localhost:8081/SeekerPool/yuquannpage/job/JobType.html");
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doGet(req, res);
	}

	public JobPositionUpdate() {
		super();

	}

}
