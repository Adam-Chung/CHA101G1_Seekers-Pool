package tw.idv.Seeker_Pool_Merge.fong.controller.filter;

import java.io.IOException; 
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebFilter("/*")
//@WebFilter(value={"/fongpage/*", "XXX"}) 或是  @WebFilter(urlPatterns={"/fongpage/*", "XXX"}) 
@WebFilter(urlPatterns={"/front-end/member/member/*"}) // 要從專案名稱後開始寫，只有在此資料中的需要被驗證 member是否登入
public class MemberLoginCheckFilter extends HttpFilter {
	//可以改成用繼承 extends HttpFilter ， 不用 implements Filter，就可以直接使用HttpServletRequest，不用將ServletRequest 進行強轉成HttpServletRequest
	private static final long serialVersionUID = 6197057707314002538L;

	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		
		// 獲取url路徑(請求來源)
		String uri = request.getRequestURI();
		// 從session中獲取memberLogin 判定是否登入
		Object memId = request.getSession().getAttribute("memberLogin");

		if (memId != null) {
			// 判定已登入，又到登入頁面，就回主頁面
			if (uri.contains("/memberlogin.html")) {
				response.sendRedirect("memberinfo.html");
			} else { // 其他已登入，就不重定向
				// 不要緩存該網頁，防止已登出時該網頁還正常顯示
				response.setHeader("Cache-Control", "no-store");
				response.setHeader("Pragma", "no-cache");
				response.setDateHeader("Expires", 0);

				chain.doFilter(request, response);
			}
		} else {
			// 判斷是否包含登入、註冊相關路徑，css/js/圖片等資源也要排除
			if (uri.contains("/memberregister.html") || uri.contains("/memberlogin.html")|| uri.contains("/css/") || uri.contains("/js/") || uri.contains("/fonts/") || uri.contains("/buildPage/") || uri.contains("/images/") || uri.contains("/checkinterviewtime.html")) {
				chain.doFilter(request, response);
			} else {
				// 沒有登入，重定向到登入頁面
				// 不要緩存該網頁，防止已登入時該網頁還正常顯示
				response.setHeader("Cache-Control", "no-store");
				response.setHeader("Pragma", "no-cache");
				response.setDateHeader("Expires", 0);
				
				response.sendRedirect("memberlogin.html");
			}
		}
		
//=====舊版@WebFilter("/*")寫法=========
//		// 獲取url路徑(請求來源)
//		String uri = request.getRequestURI();
//		// 從session中獲取memberLogin 判定是否登入
//		Object memId = request.getSession().getAttribute("memberLogin");
//
//		if (memId != null) {
//			// 判定已登入，又到登入頁面，就回主頁面
//			if (uri.contains("/memberlogin.html") || uri.contains("/MemberLoginServlet")) {
//				response.sendRedirect("memberinfo.html");
//			} else { // 其他已登入，就不重定向
//				// 不要緩存該網頁，防止已登出時該網頁還正常顯示
//				response.setHeader("Cache-Control", "no-store");
//				response.setHeader("Pragma", "no-cache");
//				response.setDateHeader("Expires", 0);
//
//				chain.doFilter(request, response);
//			}
//		} else {
//			// 判斷是否包含登入、註冊相關路徑，css/js/圖片等資源也要排除
//			if (uri.contains("/memberregister.html") || uri.contains("/MemberRegisterServlet")
//					|| uri.contains("/memberlogin.html") || uri.contains("/MemberLoginServlet") || uri.contains("/css/")
//					|| uri.contains("/js/") || uri.contains("/fonts/") || uri.contains("/buildPage/")
//					|| uri.contains("/images/") || uri.contains("/headerServlet")
//					|| uri.contains("/checkinterviewtime.html")) {
//				chain.doFilter(request, response);
//			} else {
//				// 沒有登入，重定向到登入頁面
//				// 不要緩存該網頁，防止已登入時該網頁還正常顯示
//				response.setHeader("Cache-Control", "no-store");
//				response.setHeader("Pragma", "no-cache");
//				response.setDateHeader("Expires", 0);
//				
//				response.sendRedirect("memberlogin.html");
//			}
//		}
	}
}
