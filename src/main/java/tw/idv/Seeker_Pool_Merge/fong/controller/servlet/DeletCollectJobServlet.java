package tw.idv.Seeker_Pool_Merge.fong.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.idv.Seeker_Pool_Merge.fong.service.JobService;
import tw.idv.Seeker_Pool_Merge.fong.service.impl.JobServiceImpl;

@WebServlet("/DeletCollectJobServlet")
public class DeletCollectJobServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private JobService jobServiceImpl = new JobServiceImpl();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer jobNo = Integer.valueOf(req.getParameter("jobNo").trim()) ;
		Integer memId = (Integer) req.getSession().getAttribute("memberLogin");
		
		
		jobServiceImpl.deletColJobByMemIdAndArNo(jobNo, memId);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
