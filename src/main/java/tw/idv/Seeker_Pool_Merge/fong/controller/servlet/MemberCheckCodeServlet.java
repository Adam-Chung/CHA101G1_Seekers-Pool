package tw.idv.Seeker_Pool_Merge.fong.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import redis.clients.jedis.Jedis;
import tw.idv.Seeker_Pool_Merge.common.util.JedisUtil;
import tw.idv.Seeker_Pool_Merge.fong.service.MemberService;
import tw.idv.Seeker_Pool_Merge.fong.service.impl.MemberServiceImpl;
import tw.idv.Seeker_Pool_Merge.fong.vo.MemberVo;
import tw.idv.Seeker_Pool_Merge.fong.vo.ResultInfo;

@WebServlet("/MemberCheckCodeServlet")
public class MemberCheckCodeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberServiceImpl();
	Jedis jedis = JedisUtil.getJedis();//獲取jedis

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		System.out.println("coming MemberCheckCodeServlet");
		
		Integer memId = (Integer) req.getSession().getAttribute("memberLogin");
		MemberVo member = memberService.getMemberById(memId);		
		
		String inputCheckCode = req.getParameter("checkCode");
		
		//命名Redis中的key 
		String key = member.getMemAccount() + "-checkCode";
		
		if(inputCheckCode == null) {
			//寄信前先刪除之前的驗證碼
//			req.getSession().removeAttribute("checkCode");
			jedis.del(key);
			
			//寄信
			String contextPath = req.getContextPath(); //獲取專案名稱 /SeekerPool
			String checkCode =  memberService.sendCheckCode(member, contextPath);
			
//			// 不要緩存該網頁，防止已登出時該網頁還正常顯示
//			resp.setHeader("Cache-Control", "no-store");
//			resp.setHeader("Pragma", "no-cache");
//			resp.setDateHeader("Expires", 0);
			
			
//			req.getSession().setAttribute("checkCode", checkCode);
			//儲存信箱驗證碼到Redis中，定時20秒銷毀，記得先開啟redis_startup.bat - 捷徑
			jedis.setex(key, 60L, checkCode);
//			jedis.expire(key, 20L);

			System.out.println(key + "郵件驗證--" + jedis.get(key));
			
		}else {
			ResultInfo info = new ResultInfo(); // 用於封裝資訊回傳前端
			
//			String checkCode = (String) req.getSession().getAttribute("checkCode");
			String checkCode = jedis.get(key);
			
			
			if(inputCheckCode.trim().equals(checkCode)) {
				//驗證碼正確
				info.setFlag(true);
				member.setMemStatus(1);
				memberService.updateMember(member);
				
//				req.getSession().removeAttribute("checkCode");
				jedis.del(key);
				
			}else {
				// 驗證錯誤
				info.setFlag(false);
				info.setMsg("驗證碼錯誤或過時，請確認");
			}
			
			//轉為json寫回客戶端
	    	String jsonStr =  new JSONObject(info).toString();  //Json.jar
	    	resp.getWriter().write(jsonStr);
	    	jedis.close();
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}