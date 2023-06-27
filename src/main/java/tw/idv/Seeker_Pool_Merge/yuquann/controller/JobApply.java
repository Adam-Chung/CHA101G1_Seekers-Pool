package tw.idv.Seeker_Pool_Merge.yuquann.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tw.idv.Seeker_Pool_Merge.yuquann.dao.JobApplyDao;
import tw.idv.Seeker_Pool_Merge.yuquann.service.impl.JobApplyServiceImpl;

@WebServlet("/JobApply")
public class JobApply extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JobApplyServiceImpl JobApplyServiceImpl = new JobApplyServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		res.setContentType("application/json;charset=utf-8");
		PrintWriter out = res.getWriter();

		// 這邊直接單行轉型 資料驗證的時候要特別注意
		// memId整合時改成使用getsession來取值
		int memId = 3;
		int comMemId = Integer.parseInt(req.getParameter("comMemId"));
		int jobNo = Integer.parseInt(req.getParameter("jobNo"));

		// 商業邏輯判斷三項參數是否同時存在於資料庫中的某一欄位
		Boolean check = JobApplyServiceImpl.applyCheck(memId, comMemId, jobNo);

		if (check) {
			JobApplyDao dao = new JobApplyDao();

			int rowcount = dao.insert(memId, comMemId, jobNo);
//			System.out.println("應徵紀錄新增筆數 : " + rowcount);
		} else {
			Gson gson = new Gson();
			String json = gson.toJson(false);
			out.print(json);
		}
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}
	public JobApply() {
		super();
	}
}
