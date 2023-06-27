package tw.idv.Seeker_Pool_Merge.yuquann.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tw.idv.Seeker_Pool_Merge.yuquann.dao.SkillTypeDao;
import tw.idv.Seeker_Pool_Merge.yuquann.vo.SkillTypeVo;

@WebServlet("/JobSkillEditShow")
public class JobSkillEditShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		res.setContentType("application/json;charset=utf-8");
		PrintWriter out = res.getWriter();

		String skNoStr = req.getParameter("skNo");
		int skNo = Integer.parseInt(skNoStr);
		System.out.println("skNo : " + skNo);

		SkillTypeDao dao = new SkillTypeDao();

		SkillTypeVo vo = dao.skillEditShow(skNo);
		

		Gson gson = new Gson();
		String json = gson.toJson(vo);
		System.out.println("json : " + json);
		
		out.print(json);
		out.close();
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	public JobSkillEditShow() {
		super();
	}
}
