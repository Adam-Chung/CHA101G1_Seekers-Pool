package tw.idv.Seeker_Pool_Merge.article.controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tw.idv.Seeker_Pool_Merge.article.model.ArticleService;
import tw.idv.Seeker_Pool_Merge.article.model.ArticleVo;

@WebServlet("/ArticlePageServlet")
public class ArticlePageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ArticleService articleService = new ArticleService();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		resp.setContentType("application/json;charset=utf-8");
		
		// 根據arNo找到單一文章的閱讀更多
			
		// 接收arNo
		String arNo = req.getParameter("arNo");
		
		// 調用service查詢ArticleVo對象
		ArticleVo page = articleService.getArticleById(arNo);
	
		// 轉回json物件傳回前端
		  if (page != null) {
		        byte[] imageData = page.getArImg();

		        String arImg = Base64.getEncoder().encodeToString(imageData);
		        page.setArImgBase64(arImg);
		        page.setArImg(null); // 避免將圖片的原始 byte 陣列包含在 JSON 中

		        resp.getWriter().write(new Gson().toJson(page));
		    }
		}

}
