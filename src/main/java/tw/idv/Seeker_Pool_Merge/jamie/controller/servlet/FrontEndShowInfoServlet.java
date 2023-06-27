package tw.idv.Seeker_Pool_Merge.jamie.controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import tw.idv.Seeker_Pool_Merge.jamie.service.CompanyMemberService;
import tw.idv.Seeker_Pool_Merge.jamie.service.impl.CompanyMemberServiceImpl;
import tw.idv.Seeker_Pool_Merge.jamie.vo.CompanyMemberVo;

@WebServlet("/FrontEnd/ShowCompanyMemberInfo")
public class FrontEndShowInfoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private CompanyMemberService service = new CompanyMemberServiceImpl();
//	CompanyMemberVo testCompanyMember = new CompanyMemberVo();

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// 測試用程式碼
//		testCompanyMember.setComMemId(51);
//		testCompanyMember.setComPictureUrl("/FrontEnd/ShowCompanyMemberInfo?id=" + testCompanyMember.getComMemId());
//		req.getSession().setAttribute("companyMember", testCompanyMember);
//		int id = ((CompanyMemberVo) req.getSession().getAttribute("companyMember")).getComMemId();
		
		// 正式用程式碼
		int id = (int) req.getSession().getAttribute("companyMember");
		
		CompanyMemberVo companyMember = service.getCompanyMemberById(id);
			    
		String jsonStr =  new JSONObject(companyMember).toString();
		resp.setContentType("application/json; charset=UTF-8");
		try (PrintWriter out = resp.getWriter()) {
					out.write(jsonStr);
					out.flush();
		}	
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
