package tw.idv.Seeker_Pool_Merge.yuquann.service;

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

@WebServlet("/ReportEnterpriseTakeData")
public class ReportEnterpriseTakeData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		res.setContentType("application/json;charset=utf-8");
		PrintWriter out = res.getWriter();

		ReportEnterpriseDao dao = new ReportEnterpriseDao();
		List<ReportEnterpriseVo> list = dao.selectAll();

		// 將 List 轉換為 JSON 格式
		Gson gson = new Gson();
		String json = gson.toJson(list);

		// 將 JSON 資料回傳給前端
		out.print(json);
			

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	public ReportEnterpriseTakeData() {
		super();
	}
}
