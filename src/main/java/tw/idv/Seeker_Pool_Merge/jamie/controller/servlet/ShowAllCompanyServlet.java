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

import tw.idv.Seeker_Pool_Merge.jamie.service.CompanyMemberService;
import tw.idv.Seeker_Pool_Merge.jamie.service.impl.CompanyMemberServiceImpl;
import tw.idv.Seeker_Pool_Merge.jamie.vo.CompanyMemberVo;

@WebServlet("/BackEnd/ShowAllCompany")
public class ShowAllCompanyServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private CompanyMemberService service = new CompanyMemberServiceImpl();
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		List<CompanyMemberVo> companyMembers = service.findAll();
		
		// 轉換成JSONArray給前端
		String jsonStr =  new JSONArray(companyMembers).toString();
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.write(jsonStr);
    	out.flush();

	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
