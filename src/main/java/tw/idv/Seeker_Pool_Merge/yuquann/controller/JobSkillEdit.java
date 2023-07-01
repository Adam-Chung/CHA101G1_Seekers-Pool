package tw.idv.Seeker_Pool_Merge.yuquann.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tw.idv.Seeker_Pool_Merge.yuquann.dao.SkillTypeDao;

@WebServlet("/JobSkillEdit")
public class JobSkillEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		String skType = req.getParameter("skType");
		String skName = req.getParameter("skName");
		String skNoStr = req.getParameter("skNo");
		int skNo = Integer.parseInt(skNoStr);

		SkillTypeDao dao = new SkillTypeDao();

		dao.skillEdit(skType, skName, skNo);
		
		res.sendRedirect("/SeekerPool/back-end/job/JobSkill.html");
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	public JobSkillEdit() {
		super();
	}
}
