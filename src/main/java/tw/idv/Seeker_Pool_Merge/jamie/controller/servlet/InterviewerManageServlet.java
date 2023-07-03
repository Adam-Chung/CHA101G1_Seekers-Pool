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
import org.json.JSONObject;

import tw.idv.Seeker_Pool_Merge.jamie.dao.ApplyRecordDao;
import tw.idv.Seeker_Pool_Merge.jamie.dao.impl.ApplyRecordDaoImpl;
import tw.idv.Seeker_Pool_Merge.jamie.service.CompanyMemberService;
import tw.idv.Seeker_Pool_Merge.jamie.service.impl.CompanyMemberServiceImpl;
import tw.idv.Seeker_Pool_Merge.jamie.vo.ApplyRecordVo;
import tw.idv.Seeker_Pool_Merge.jamie.vo.CompanyMemberVo;

@WebServlet("/FrontEnd/InterviewerManage")
public class InterviewerManageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private CompanyMemberService service = new CompanyMemberServiceImpl();
	private ApplyRecordDao dao = new ApplyRecordDaoImpl();
	CompanyMemberVo testCompanyMember = new CompanyMemberVo();
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// 測試用程式碼
//		testCompanyMember.setComMemId(51);
//		req.getSession().setAttribute("companyMember", testCompanyMember);
//		int comMemId = ((CompanyMemberVo) req.getSession().getAttribute("companyMember")).getComMemId();
		
		// 正式用
		int comMemId = (int) req.getSession().getAttribute("companyMember");
		
		String idStr = req.getParameter("detailId");  // 會員ID (顯示履歷詳情用)
		
		resp.setContentType("application/json; charset=utf-8");
		try (PrintWriter out = resp.getWriter()) {
			if (idStr == null) {
				CompanyMemberVo companyMember = service.getCompanyMemberById(comMemId);

				if (companyMember != null) {
					List<ApplyRecordVo> applyRecords = dao.findApplicantsByComMemId(comMemId);
//					System.out.println("印出應徵者列表: " + applyRecords);
					
					String jsonStr =  new JSONArray(applyRecords).toString();		
					out.write(jsonStr);
					out.flush();
				} else {
//					System.out.println("查無此企業會員");
				}

			} else {  // 點擊「詳情」並顯示履歷內容
				try {
					int memId = Integer.parseInt(idStr);
					ApplyRecordVo member = dao.findMemberById(memId);
//					System.out.println("印出該名會員: " + member);
					
//					String jobTitle = member.getJobName();
//					req.getSession().setAttribute("member", member);
//					req.getSession().setAttribute("job", jobTitle);
					
					out.write(new JSONObject(member).toString());
				} catch (NumberFormatException e) {
					e.printStackTrace();
//					System.out.println("id參數沒有成功轉換");
				}
			}		
		}
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
