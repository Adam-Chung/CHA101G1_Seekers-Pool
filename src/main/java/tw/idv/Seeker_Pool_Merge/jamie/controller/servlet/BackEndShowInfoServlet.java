package tw.idv.Seeker_Pool_Merge.jamie.controller.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import tw.idv.Seeker_Pool_Merge.jamie.service.CompanyMemberService;
import tw.idv.Seeker_Pool_Merge.jamie.service.impl.CompanyMemberServiceImpl;
import tw.idv.Seeker_Pool_Merge.jamie.vo.CompanyMemberVo;

@WebServlet("/BackEnd/ShowCompanyMemberInfo")
public class BackEndShowInfoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private CompanyMemberService service = new CompanyMemberServiceImpl();

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/*
		 * axios預設會把POST請求的參數轉換為 JSON 格式放在請求的 body 中，
		 * 所以需要使用 getReader() 方法來讀取請求的 body，並解析 JSON 格式的參數
		 */
		StringBuilder sb = new StringBuilder();
	    BufferedReader reader = req.getReader();
	    String str;
	    
	    while ((str = reader.readLine()) != null) {
	        sb.append(str);
	    }	    
	    String requestBody = sb.toString();
//	    System.out.println("收到的請求參數ID是: " + requestBody);
	    
	    if (requestBody == null || !requestBody.startsWith("{") || !requestBody.endsWith("}")) {
	        throw new ServletException("Invalid JSON request");
	    } else {
		    JSONObject jObj = new JSONObject(requestBody);
		    int id = jObj.getInt("id");  // 取得id參數
		    
			CompanyMemberVo companyMember = service.getCompanyMemberById(id);
				
			String jsonStr =  new JSONObject(companyMember).toString();
			resp.setContentType("application/json; charset=UTF-8");
			PrintWriter out = resp.getWriter();
			out.write(jsonStr);
		    out.flush();
	    }
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
}
