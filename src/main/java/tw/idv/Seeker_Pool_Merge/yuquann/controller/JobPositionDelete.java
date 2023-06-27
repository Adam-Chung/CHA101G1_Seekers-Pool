package tw.idv.Seeker_Pool_Merge.yuquann.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tw.idv.Seeker_Pool_Merge.yuquann.dao.JobPositionDao;

@WebServlet("/JobPositionDelete")
public class JobPositionDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		//因收到請求時需要回應前端的AJAX以即時刪除前端的行 故多做回應的部分
		res.setContentType("application/json;charset=utf-8");
		PrintWriter out = res.getWriter();
		
		String ptNOStr = req.getParameter("ptNO");
		int ptNO = Integer.parseInt(ptNOStr);

		JobPositionDao dao = new JobPositionDao();

		int rowcount = dao.delete(ptNO);
		
		//因收到請求時需要回應前端的AJAX以即時刪除前端的行 故多做回應的部分
		Gson gson = new Gson();
		String json = gson.toJson("刪除成功");
		out.print(json);
		
		out.close();
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	public JobPositionDelete() {
		super();
	}
}
