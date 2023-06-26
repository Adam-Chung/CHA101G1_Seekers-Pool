package tw.idv.Seeker_Pool_Merge.fong.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.idv.Seeker_Pool_Merge.fong.service.ApplyRecordService;
import tw.idv.Seeker_Pool_Merge.fong.service.MemberService;
import tw.idv.Seeker_Pool_Merge.fong.service.impl.ApplyRecordServiceImpl;
import tw.idv.Seeker_Pool_Merge.fong.service.impl.MemberServiceImpl;
import tw.idv.Seeker_Pool_Merge.fong.vo.MemberVo;

@WebServlet("/CancelInterviewServlet")
public class CancelInterviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ApplyRecordService applyRecordService = new ApplyRecordServiceImpl();
	private MemberService memberService = new MemberServiceImpl();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String comName = req.getParameter("comName").trim() ;
		String jobName = req.getParameter("jobName").trim() ;
		Integer memId = (Integer) req.getSession().getAttribute("memberLogin");

		//因為MEMBER資訊可能會修改，所以不能直接拿原先登入的member物件用，要再去資料庫抓新的
		MemberVo member = memberService.getMemberById(memId);
		applyRecordService.cancelInterview(comName, jobName, memId, member.getMemName());
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}