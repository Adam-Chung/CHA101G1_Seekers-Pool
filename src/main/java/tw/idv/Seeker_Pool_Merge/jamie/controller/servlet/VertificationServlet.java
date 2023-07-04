package tw.idv.Seeker_Pool_Merge.jamie.controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import redis.clients.jedis.Jedis;
import tw.idv.Seeker_Pool_Merge.common.util.JedisUtil;
import tw.idv.Seeker_Pool_Merge.jamie.service.CompanyMemberService;
import tw.idv.Seeker_Pool_Merge.jamie.service.impl.CompanyMemberServiceImpl;
import tw.idv.Seeker_Pool_Merge.jamie.vo.CompanyMemberVo;
import tw.idv.Seeker_Pool_Merge.jamie.vo.ResultInfo;

@WebServlet("/RegisterVertification")
public class VertificationServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;	
	private CompanyMemberService service = new CompanyMemberServiceImpl();
	Jedis jedis = JedisUtil.getJedis();
	
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = (int) req.getSession().getAttribute("companyMember");
		CompanyMemberVo companyMember = service.getCompanyMemberById(id);
		
		String inputValidCode = req.getParameter("validCode");
		ResultInfo resultInfo = new ResultInfo();
		// 命名Redis中的key
		String key = companyMember.getComMemAccount() + "-validCode";
		String validCode = null;
		
		try {
			if (inputValidCode == null) {
				// 寄信前先刪除之前的驗證碼
				jedis.del(key);
				
				// 寄信
//				System.out.println("寄信囉");
				validCode = service.sendValidCode(companyMember);
				// 開啟Redis來儲存key
				jedis.setex(key, 30, validCode);
//				System.out.println("驗證碼為: " + key + ", 郵件寄出驗證碼: " + jedis.get(key));
				
			} else {
				// 從Redis中取出驗證碼
			    validCode = jedis.get(key);
			    
				// 調用Service檢查輸入的驗證碼是否一致
				boolean isCodeValid = service.checkValidCode(inputValidCode, validCode);
//				System.out.println("isCodeValid: " + isCodeValid);  // 測試用資訊
				
				if (isCodeValid) {
					// 驗證成功
					resultInfo.setFlag(true);
					companyMember.setComStatus(1);
					service.updateCompanyStatus(companyMember);
					jedis.del(key);
//					System.out.println("驗證成功!");
				} else {
					// 驗證錯誤
					resultInfo.setFlag(false);
					resultInfo.setErrorMsg("請重新確認一次");
//					System.out.println("驗證失敗!");
				}
				
				String jsonStr =  new JSONObject(resultInfo).toString();
				resp.setContentType("application/json; charset=UTF-8");
				PrintWriter out = resp.getWriter();
				out.write(jsonStr);
		    	out.flush();
			}
			
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

}
