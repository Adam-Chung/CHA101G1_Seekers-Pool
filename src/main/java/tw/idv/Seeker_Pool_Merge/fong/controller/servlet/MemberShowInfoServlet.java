package tw.idv.Seeker_Pool_Merge.fong.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import tw.idv.Seeker_Pool_Merge.fong.service.MemberService;
import tw.idv.Seeker_Pool_Merge.fong.service.impl.MemberServiceImpl;
import tw.idv.Seeker_Pool_Merge.fong.vo.MemberVo;

@WebServlet("/MemberShowInfoServlet")
public class MemberShowInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Integer memId = (Integer) req.getSession().getAttribute("memberLogin");

		MemberVo member = memberService.getMemberById(memId);

		// 轉為json寫回客戶端
		String jsonStr = new JSONObject(member).toString();
//    	System.out.println(member);
		resp.getWriter().write(jsonStr);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}