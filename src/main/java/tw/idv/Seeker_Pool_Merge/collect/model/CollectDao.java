package tw.idv.Seeker_Pool_Merge.collect.model;

public interface CollectDao {
	
	// 新增一筆收藏文章的資料
	 public CollectVo addCollect(CollectVo collectVo); 
	 	
	// 查詢會員收藏過的文章	 
	 public CollectVo selectColArticleByMemId(Integer memId, Integer arNo);
	 
	// 會員取消收藏文章
	 public void deleteColArticle(Integer memId, Integer arNo);
}
