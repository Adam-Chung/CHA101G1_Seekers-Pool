package tw.idv.Seeker_Pool_Merge.yuquann.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tw.idv.Seeker_Pool_Merge.yuquann.dao.ReportEnterpriseDao;
import tw.idv.Seeker_Pool_Merge.yuquann.vo.ReportEnterpriseVo;

@WebServlet("/ReportResultSelect")
public class ReportResultSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		res.setContentType("application/json;charset=utf-8");
		PrintWriter out = res.getWriter();
		String reResultStr = req.getParameter("reResult");
		int reResult = Integer.parseInt(reResultStr);

		if (reResult != 3) {
			ReportEnterpriseDao dao = new ReportEnterpriseDao();
			List<ReportEnterpriseVo> vo = dao.selectResult(reResult);

			Gson gson = new Gson();
			String json = gson.toJson(vo);
			System.out.println("json : " + json);
			out.print(json);
		} else {
			ReportEnterpriseDao dao = new ReportEnterpriseDao();
			List<ReportEnterpriseVo> vo = dao.selectAll();

			Gson gson = new Gson();
			String json = gson.toJson(vo);
			System.out.println("json : " + json);
			out.print(json);
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	public ReportResultSelect() {
		super();
	}
}
