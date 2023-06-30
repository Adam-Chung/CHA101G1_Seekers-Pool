package tw.idv.Seeker_Pool_Merge.yuquann.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tw.idv.Seeker_Pool_Merge.yuquann.dao.JobShowDao;

@WebServlet("/JobResultShow")
public class JobResultShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.setContentType("application/json;charset=utf-8");
		PrintWriter out = res.getWriter();

		// 要注意屆時接收參數若為空值會不好判斷 因為沒有另外用一個String去接 確認有資料後再改回單行
		String jobNoStr = req.getParameter("jobNo");
		int jobNo = Integer.parseInt(jobNoStr);
		
		JobShowDao dao = new JobShowDao();
		
		//因該次資料是用LeftJoin 無法使用一般的VO來裝資料 故改使用MAP集合來裝資料
		Map<String, Object> job = dao.jobShow(jobNo);

		//就算使用Object資料 最終都是轉成JSON輸出至前端
		Gson gson = new Gson();
		String json = gson.toJson(job);
//		System.out.println("JobResultShow json : " + json);
		out.print(json);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	public JobResultShow() {
		super();
	}
}
