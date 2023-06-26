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
import tw.idv.Seeker_Pool_Merge.fong.vo.ResultInfo;

@WebServlet("/headerServlet")
public class headerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		if(req.getParameter("logout") != null) { //要登出
			req.getSession().removeAttribute("memberLogin");
			
		}else {
			Object memId = req.getSession().getAttribute("memberLogin");
			
			ResultInfo info = new ResultInfo(); // 用於封裝資訊回傳前端
			if(memId != null) {
				//有登入
				Integer id = (Integer) memId;
				MemberVo member = memberService.getMemberById(id);
				
				info.setFlag(true); 
				info.setData(member);
			}else {
				//沒登入
				info.setFlag(false);
			}
			
			//轉為json寫回客戶端
	    	String jsonStr =  new JSONObject(info).toString();  
	    	resp.getWriter().write(jsonStr);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}