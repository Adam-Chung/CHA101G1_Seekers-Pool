package tw.idv.Seeker_Pool_Merge.jamie.controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;

import tw.idv.Seeker_Pool_Merge.jamie.dao.JobDao;
import tw.idv.Seeker_Pool_Merge.jamie.dao.impl.JobDaoImpl;
import tw.idv.Seeker_Pool_Merge.jamie.vo.JobVo;

@WebServlet("/FrontEnd/InterviewInvite")
public class InterviewInviteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private JobDao dao = new JobDaoImpl();
//	CompanyMemberVo testCompanyMember = new CompanyMemberVo();
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// 測試用程式碼
//		testCompanyMember.setComMemId(5);
//		req.getSession().setAttribute("companyMember", testCompanyMember);
//		int comMemId = ((CompanyMemberVo) req.getSession().getAttribute("companyMember")).getComMemId();
		
		int comMemId = (int) req.getSession().getAttribute("companyMember");
		List<JobVo> jobList = dao.findJobsByComMemId(comMemId);
		
		String jsonStr =  new JSONArray(jobList).toString();
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.write(jsonStr);
    	out.flush();
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
