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

@WebServlet("/JobTypeShow")
public class JobTypeShow extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		//設定回應的type
		res.setContentType("application/json;charset=utf-8");
		//建立writer把資料列印在網頁上
		PrintWriter out = res.getWriter();
		
		JobPositionDao dao = new JobPositionDao();
		//呼叫dao裡把職務全部列印出來的showAll
		List<PositionTypeVo> list = dao.showAll();
		
		//將list中的資料轉為json字串
		Gson gson = new Gson();
		String json = gson.toJson(list);
	
		//將json字串送至前端
		out.print(json);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doGet(req, res);
	}

	public JobTypeShow() {
		super();
	}
}
