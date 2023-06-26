package tw.idv.Seeker_Pool_Merge.article.model;

import java.util.List;

public interface ArticleDao {

	// 新增文章
	public void insert(ArticleVo articleVO);

	// 修改文章
	public void update(ArticleVo articleVO);

	// 找到所有文章
	public List<ArticleVo> getAllArticles();

	// 根據文章編號找到文章
	public ArticleVo getArticleById(Integer arNo);

	// 紀錄收藏文章次數
	public ArticleVo getSaveCountByMemId(Integer memId);

}
