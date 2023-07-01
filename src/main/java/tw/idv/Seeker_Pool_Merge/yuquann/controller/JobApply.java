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
		Gson gson = new Gson();

		// 接收第一個AJAX確認是否有登入 為了脫離NumberFormatException
		String test = req.getParameter("test");

		Object memId0 = req.getSession().getAttribute("memberLogin");
		if (memId0 == null) {
			// 在前端跳轉頁面
			String json = gson.toJson("notlogin");
			res.getWriter().write(json);
		} else {

			Integer memId = (Integer) memId0;

			String comMemIdStr = req.getParameter("comMemId");
			String jobNoStr = req.getParameter("jobNo");

			if (comMemIdStr == null | jobNoStr == null) {
					System.out.println("JobApply.java出現空值 此為AJAX二次請求造成");
			} else {
				int comMemId = Integer.parseInt(req.getParameter("comMemId"));
				int jobNo = Integer.parseInt(req.getParameter("jobNo"));

				// 商業邏輯判斷三項參數是否同時存在於資料庫中的某一欄位
				Boolean check = JobApplyServiceImpl.applyCheck(memId, comMemId, jobNo);

				if (check) {
					JobApplyDao dao = new JobApplyDao();
					dao.insert(memId, comMemId, jobNo);
//				System.out.println("應徵紀錄新增筆數 : " + rowcount);
				} else {
					String json = gson.toJson(false);
					out.print(json);
				}
			}

		}
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	public JobApply() {
		super();
	}
}
