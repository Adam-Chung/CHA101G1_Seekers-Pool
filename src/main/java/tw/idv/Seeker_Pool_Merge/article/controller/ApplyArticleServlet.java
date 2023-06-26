package tw.idv.Seeker_Pool_Merge.article.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import tw.idv.Seeker_Pool_Merge.article.model.ArticleService;
import tw.idv.Seeker_Pool_Merge.article.model.ArticleVo;

@WebServlet("/ApplyArticleServlet")
public class ApplyArticleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArticleService articleService = new ArticleService();

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		List<ArticleVo> list = articleService.getAllArticles();

		if (list != null && !list.isEmpty()) {
			for (ArticleVo article : list) {
				byte[] imageData = article.getArImg();

				String arImg = Base64.getEncoder().encodeToString(imageData);
				article.setArImgBase64(arImg);
				article.setArImg(null); // 避免將圖片的原始 byte 陣列包含在 JSON 中
			}

			resp.getWriter().write(new Gson().toJson(list));
		}
	}
}
