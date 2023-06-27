package tw.idv.Seeker_Pool_Merge.yuquann.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.idv.Seeker_Pool_Merge.yuquann.dao.SkillTypeDao;

@WebServlet("/JobSkillAdd")
public class JobSkillAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		res.setContentType("application/json;charset=utf-8");

		String skType = req.getParameter("skType");
		String skName = req.getParameter("skName");

		SkillTypeDao dao = new SkillTypeDao();

		int rowcount = dao.skillAdd(skType, skName);
		
		res.sendRedirect("http://localhost:8081/SeekerPool/yuquannpage/job/JobSkill.html");

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	public JobSkillAdd() {
		super();

	}

}
