package tw.idv.Seeker_Pool_Merge.xuan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tw.idv.Seeker_Pool_Merge.xuan.dao.impl.OnlineCourseDaoImpl;
import tw.idv.Seeker_Pool_Merge.xuan.vo.OnlineCourseVo;

@WebServlet("/GetAllCourseServlet")
public class GetAllCourseServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private OnlineCourseDaoImpl onlineCourseDaoImpl = new OnlineCourseDaoImpl();

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");

		List<OnlineCourseVo> courses = onlineCourseDaoImpl.getAllOnlineCourses();
		
		Gson gson = new Gson();
		String jsonString = gson.toJson(courses);
		resp.setContentType("application/json;charset=utf-8");

		PrintWriter out = resp.getWriter();
//		System.out.println(jsonString);

		out.write(jsonString);
		out.close();
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

}
