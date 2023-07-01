package tw.idv.Seeker_Pool_Merge.jamie.controller.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import tw.idv.Seeker_Pool_Merge.jamie.dao.JobDao;
import tw.idv.Seeker_Pool_Merge.jamie.dao.impl.JobDaoImpl;
import tw.idv.Seeker_Pool_Merge.jamie.service.CompanyMemberService;
import tw.idv.Seeker_Pool_Merge.jamie.service.impl.CompanyMemberServiceImpl;
import tw.idv.Seeker_Pool_Merge.jamie.vo.CompanyMemberVo;
import tw.idv.Seeker_Pool_Merge.jamie.vo.JobVo;

@WebServlet("/ShowCompanyInfoAndJoblist")
public class CompanyInfoForMemberServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private CompanyMemberService service = new CompanyMemberServiceImpl();
	private JobDao dao = new JobDaoImpl();
	CompanyMemberVo testCompanyMember = new CompanyMemberVo();
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// 測試用程式碼
//		testCompanyMember.setComMemId(51);
//		req.getSession().setAttribute("companyMember", testCompanyMember);
//		int id = ((CompanyMemberVo) req.getSession().getAttribute("companyMember")).getComMemId();
		
		// 正式用程式碼
		// 解析參數
		StringBuilder sb = new StringBuilder();
	    BufferedReader reader = req.getReader();
	    String str;
	    
	    while ((str = reader.readLine()) != null) {
	    	sb.append(str);
	    }	    
	    String requestBody = sb.toString();	    
		JSONObject jObj = new JSONObject(requestBody);
		
		int id = jObj.getInt("id");
//		System.out.println("要顯示的企業會員ID = " + id);
		
		CompanyMemberVo companyInfo = service.getInfoForApplicants(id);
		List<JobVo> jobList = dao.findJobsByComMemId(id);
//		System.out.println(companyInfo);
//		System.out.println(jobList);
		
		// 創建結果物件
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("companyInfo", companyInfo);
		resultMap.put("jobList", jobList);
			    
		String jsonStr =  new JSONObject(resultMap).toString();
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
