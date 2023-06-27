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

import tw.idv.Seeker_Pool_Merge.yuquann.dao.JobSearchDao;
import tw.idv.Seeker_Pool_Merge.yuquann.vo.JobVo;

@WebServlet("/JobResultSearch")
public class JobResultSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		res.setContentType("application/json;charset=utf-8");
		String keyword = req.getParameter("keyword");
		
		PrintWriter out = res.getWriter();
		
		JobSearchDao dao = new JobSearchDao();
		List<JobVo> list = dao.searchResult(keyword);
//		System.out.println("JobSearch List : " + list);
		
		Gson gson = new Gson();
		String json = gson.toJson(list);
		out.print(json);
//		System.out.println("json : " + json);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}
	public JobResultSearch() {
		super();
	}
}