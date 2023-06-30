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

@WebServlet("/JobSkillDelete")
public class JobSkillDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		res.setContentType("application/json;charset=utf-8");
		PrintWriter out = res.getWriter();
		
		String skNoStr = req.getParameter("skNo");
		int skNo = Integer.parseInt(skNoStr);
		
		SkillTypeDao dao = new SkillTypeDao();
		int rowcount = dao.skillDelete(skNo);
		
//		System.out.println("Skill rowcount : " + rowcount);
		
		if(rowcount == 1) {
			Gson gson = new Gson();
			String json = gson.toJson("刪除成功");
			out.print(json);
		}else {
			System.out.println("刪除失敗");
		}
		
		
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}

	public JobSkillDelete() {
		super();

	}

}
