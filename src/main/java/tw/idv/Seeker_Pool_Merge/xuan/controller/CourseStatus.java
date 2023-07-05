package tw.idv.Seeker_Pool_Merge.xuan.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.idv.Seeker_Pool_Merge.xuan.dao.impl.OnlineCourseDaoImpl;
import tw.idv.Seeker_Pool_Merge.xuan.vo.OnlineCourseVo;

@WebServlet("/CourseStatus")
public class CourseStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OnlineCourseDaoImpl onlineCourseDaoImpl = new OnlineCourseDaoImpl();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setCharacterEncoding("UTF-8");
		
		int onNo = Integer.parseInt(req.getParameter("onNo"));
		int onStatus = Integer.parseInt(req.getParameter("onStatus"));

		OnlineCourseVo onlineCourse = new OnlineCourseVo();
		onlineCourse.setOnNo(onNo);
		onlineCourse.setOnStatus(onStatus);

		onlineCourseDaoImpl.updateOnlineCourse(onlineCourse);

		// 回傳成功訊息
		resp.getWriter().print("success");
	}

}
