package tw.idv.Seeker_Pool_Merge.article.model;

import java.sql.Timestamp;
import java.util.List;

public class ArticleService {

	private ArticleDaoImpl daoImpl;

	public ArticleService() {
		daoImpl = new ArticleDaoImpl();
	}

	// 新增文章
	public ArticleVo insertArticle(int arNo, int memId, int arHits, Timestamp arPubTime, String arTitle, String arContent,
			byte[] arImg, byte arStatus, byte arMailStatus) {
		ArticleVo articleVO = new ArticleVo();
		articleVO.setArNo(arNo);
		articleVO.setMemId(memId);
		articleVO.setArPubTime(arPubTime);
		articleVO.setArTitle(arTitle);
		articleVO.setArContent(arContent);
		articleVO.setArImg(arImg);
		daoImpl.insert(articleVO);

		return articleVO;
	}

	// 修改文章
	public ArticleVo updateArticle(int arNo, int memId, int arHits, Timestamp arPubTime, String arTitle, String arContent,
			byte[] arImg, byte arStatus, byte arMailStatus) {
		ArticleVo articleVO = new ArticleVo();
		articleVO.setArNo(arNo);
		articleVO.setMemId(memId);
		articleVO.setArPubTime(arPubTime);
		articleVO.setArTitle(arTitle);
		articleVO.setArContent(arContent);
		articleVO.setArImg(arImg);
		daoImpl.update(articleVO);

		return articleVO;
	}


	// 找到所有文章
	public List<ArticleVo> getAllArticles() {
		return daoImpl.getAllArticles();
	}

	// 根據文章編號找到文章
	public ArticleVo getArticleById(String arNo) {
		return daoImpl.getArticleById(Integer.valueOf(arNo));
	}

	

}
