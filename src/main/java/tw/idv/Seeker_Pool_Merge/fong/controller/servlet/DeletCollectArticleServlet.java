package tw.idv.Seeker_Pool_Merge.fong.controller.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tw.idv.Seeker_Pool_Merge.fong.service.ArticleService;
import tw.idv.Seeker_Pool_Merge.fong.service.impl.ArticleServiceImpl;

@WebServlet("/DeletCollectArticleServlet")
public class DeletCollectArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleService articleService = new ArticleServiceImpl();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Integer arNo = Integer.valueOf(req.getParameter("arNo").trim()) ;
		Integer memId = (Integer) req.getSession().getAttribute("memberLogin");
		
		articleService.deletColArtByMemIdAndArNo(arNo, memId);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
