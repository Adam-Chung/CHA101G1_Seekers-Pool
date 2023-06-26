package tw.idv.Seeker_Pool_Merge.fong.controller.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class SetEncodingFilter extends HttpFilter implements Filter {
	private static final long serialVersionUID = 123089881400905542L;

	public void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		String method = req.getMethod();
		// 解决post中文亂碼問題
		if (method.equalsIgnoreCase("post")) {
			req.setCharacterEncoding("utf-8");
		}
		
		//為了讓其他非java檔能夠正常運作
		String uri = req.getRequestURI();
		res.setCharacterEncoding("utf-8");
		if (!(uri.contains("/css/") || uri.contains("/js/") || uri.contains("/images/") || uri.contains("/fonts/") )) {
			res.setContentType("text/html;charset=utf-8");
		}

		chain.doFilter(req, res);
	}

}
