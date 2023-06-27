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

import tw.idv.Seeker_Pool_Merge.yuquann.dao.JobPositionDao;
import tw.idv.Seeker_Pool_Merge.yuquann.vo.PositionTypeVo;

@WebServlet("/JobTypeSelect")
public class JobTypeSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		res.setContentType("application/json;charset=utf-8");
		PrintWriter out = res.getWriter();

		String ptType = req.getParameter("ptType");

		JobPositionDao dao = new JobPositionDao();
		List<PositionTypeVo> list = dao.typeSelect(ptType);

		Gson gson = new Gson();
		String json = gson.toJson(list);

		out.print(json);

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	public JobTypeSelect() {
		super();
	}

}
