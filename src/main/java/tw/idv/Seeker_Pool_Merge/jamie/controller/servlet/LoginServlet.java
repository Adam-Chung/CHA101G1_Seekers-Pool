package tw.idv.Seeker_Pool_Merge.jamie.controller.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;

import tw.idv.Seeker_Pool_Merge.jamie.service.CompanyMemberService;
import tw.idv.Seeker_Pool_Merge.jamie.service.impl.CompanyMemberServiceImpl;
import tw.idv.Seeker_Pool_Merge.jamie.vo.CompanyMemberVo;
import tw.idv.Seeker_Pool_Merge.jamie.vo.ResultInfo;

@WebServlet("/CompanyLogin")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private CompanyMemberService service = new CompanyMemberServiceImpl();
	
//	@Autowired
//	private CompanyMemberService service;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		// 1. 取得帳號跟密碼資訊
		Map<String, String[]> map = req.getParameterMap();
		
		// 2. 封裝 companyMember 物件
		CompanyMemberVo companyMember = new CompanyMemberVo();
		try {
			BeanUtils.populate(companyMember, map);
		} catch (Exception e) {
//			System.out.println("登入 - 裝資料失敗");
			e.printStackTrace();
		}
		
		// 3. 調用Service來查詢
		CompanyMemberVo loginComMem = service.login(companyMember);
		ResultInfo resultInfo = new ResultInfo();
		
		// 4. 判斷帳號是否為null
		if (loginComMem == null) {
			// 帳號或密碼錯誤
//			System.out.println("沒有這個帳號");
			resultInfo.setFlag(false);
			resultInfo.setErrorMsg("您輸入的帳號或密碼錯誤，請重新確認");
//			System.out.println("登入失敗");
		} else {
			// 登入成功
			resultInfo.setFlag(true);
			int comMemId = loginComMem.getComMemId();
			req.getSession().setAttribute("companyMember", comMemId);
//			System.out.println("登入成功");
		}
		
		// 回應數據
//		System.out.println("Response: " + new JSONObject(resultInfo).toString()); // 加入這行來檢查後端返回的數據
		String jsonStr =  new JSONObject(resultInfo).toString();
		resp.setContentType("application/json; charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.write(jsonStr);
    	out.flush();
		
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

}
