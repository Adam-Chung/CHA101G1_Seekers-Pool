package tw.idv.Seeker_Pool_Merge.jamie.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.idv.Seeker_Pool_Merge.jamie.dao.ApplyRecordDao;
import tw.idv.Seeker_Pool_Merge.jamie.dao.impl.ApplyRecordDaoImpl;
import tw.idv.Seeker_Pool_Merge.jamie.service.ApplyRecordService;
import tw.idv.Seeker_Pool_Merge.jamie.service.impl.ApplyRecordServiceImpl;
import tw.idv.Seeker_Pool_Merge.jamie.vo.ResultInfo;

@WebServlet("/CompanyCancelInterview")
public class CancelInterviewServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ApplyRecordDao dao = new ApplyRecordDaoImpl();
	private ApplyRecordService service = new ApplyRecordServiceImpl();
	ResultInfo resultInfo = new ResultInfo();
//	CompanyMemberVo testCompanyMember = new CompanyMemberVo();
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String memIdStr = req.getParameter("memId");
		String jobNoStr = req.getParameter("jobNo");
		int memId = Integer.parseInt(memIdStr);
		int jobNo = Integer.parseInt(jobNoStr);
		// 測試用程式碼
//		testCompanyMember.setComMemId(51);
//		req.getSession().setAttribute("companyMember", testCompanyMember);
//		int comMemId = ((CompanyMemberVo) req.getSession().getAttribute("companyMember")).getComMemId();
		
		// 正式用
		int comMemId = (int) req.getSession().getAttribute("companyMember");
		
		int updateRows = dao.cancelInterview(memId, comMemId, jobNo);
		// 寄信通知
		service.cancelInterview(memId, comMemId, jobNo);
		
		if (updateRows == 1) {
			resultInfo.setFlag(true);
//			System.out.println("取消面試成功");
		} else {
			resultInfo.setFlag(false);
//			System.out.println("取消面試失敗");
		}
		
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
