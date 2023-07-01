package tw.idv.Seeker_Pool_Merge.yuquann.service;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tw.idv.Seeker_Pool_Merge.yuquann.dao.ReportEnterpriseDao;
import tw.idv.Seeker_Pool_Merge.yuquann.vo.ReportEnterpriseVo;

@WebServlet("/ReportEdit")
public class ReportEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("application/json;charset=UTF-8");
		PrintWriter out = res.getWriter();

		// 取得 URL 參數值
		String reNostr = req.getParameter("reNo");
		int reNo = Integer.parseInt(reNostr);
//		System.out.println("reNo : " + reNo);

		ReportEnterpriseDao dao = new ReportEnterpriseDao();
		ReportEnterpriseVo vo = dao.statusShow(reNo);

		// 建立json , 該建立方法傳至前端時不必另外在前端轉成json 因為有Gson的關係
		Gson gson = new Gson();
		String json = gson.toJson(vo);

		// 將json資料傳送至前端
		out.print(json);
		// 這邊建議可以把json字串印出確認
//		System.out.println("json :" + json);
		out.flush();

		out.close();

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doGet(req, res);
	}

	public ReportEdit() {
		super();
	}
}
