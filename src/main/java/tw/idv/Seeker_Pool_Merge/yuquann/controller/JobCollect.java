package tw.idv.Seeker_Pool_Merge.yuquann.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tw.idv.Seeker_Pool_Merge.yuquann.dao.JobCollectDao;
import tw.idv.Seeker_Pool_Merge.yuquann.service.impl.CollectJobServiceImpl;

@WebServlet("/JobCollect")
public class JobCollect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CollectJobServiceImpl CollectJobServiceImpl = new CollectJobServiceImpl();

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		res.setContentType("application/json;charset=utf-8");
		PrintWriter out = res.getWriter();
		Gson gson = new Gson();

		// 這邊的memId整合時要用getsession去替換
		Object memId0 = req.getSession().getAttribute("memberLogin");
		if (memId0 == null) {
			// 在前端跳轉頁面
			String json = gson.toJson("notlogin");
			res.getWriter().write(json);
		} else {

			Integer memId = (Integer) memId0;

//		int memId = 3;
			int jobNo = Integer.parseInt(req.getParameter("jobNo"));

			JobCollectDao dao = new JobCollectDao();

			Boolean check = CollectJobServiceImpl.check(memId, jobNo);

			if (check) {
				dao.insert(memId, jobNo);
//			System.out.println("收藏職缺新增筆數 : " + rowcount);
				String json = gson.toJson(true);
				out.print(json);
			} else {
				// 回應給前端以確認是否有新增收藏職缺 之後要寫service邏輯判斷
				String json = gson.toJson(false);
				out.print(json);
			}
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	public JobCollect() {
		super();
	}

}
