package tw.idv.Seeker_Pool_Merge.fong.controller.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;

import tw.idv.Seeker_Pool_Merge.fong.service.MemberService;
import tw.idv.Seeker_Pool_Merge.fong.service.impl.MemberServiceImpl;
import tw.idv.Seeker_Pool_Merge.fong.vo.MemberVo;
import tw.idv.Seeker_Pool_Merge.fong.vo.ResultInfo;

@WebServlet("/MemberLoginServlet")
public class MemberLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberServiceImpl();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		
		Map<String, String[]> map = req.getParameterMap();
		
		// 封裝對象，導入工具類BeanUtils
		MemberVo member = new MemberVo();
		try {
			BeanUtils.populate(member, map); // 表單name要跟成員名稱一樣
		} catch (Exception e) {
			System.out.println("Login 裝資料失敗");
			e.printStackTrace();
		}
		
		MemberVo memberLogin = memberService.loginMember(member);
		
		
		ResultInfo info = new ResultInfo(); // 用於封裝資訊回傳前端
		if(memberLogin != null) {
			//登入成功
			info.setFlag(true); 
			Integer memId = memberLogin.getMemId();
			req.getSession().setAttribute("memberLogin", memId);
		}else {
			//登入失敗
			info.setFlag(false);
			info.setMsg("帳號或密碼錯誤");
		}
		
		//轉為json寫回客戶端
    	String jsonStr =  new JSONObject(info).toString();  
    	resp.getWriter().write(jsonStr);
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}