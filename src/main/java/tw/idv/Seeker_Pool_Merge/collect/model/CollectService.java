package tw.idv.Seeker_Pool_Merge.collect.model;

import java.sql.Date;

public class CollectService {

	private CollectDaoImpl daoImpl;

	public CollectService() {
		daoImpl = new CollectDaoImpl();
	}

	// 新增一筆收藏文章的資料
	public CollectVo addCollect(CollectVo collectVo) {
		 Integer memId = collectVo.getMemId();
		    Integer arNo = collectVo.getArNo();

		    // 查詢會員收藏過的文章
		    CollectVo existingCollect = daoImpl.selectColArticleByMemId(memId, arNo);
		    if (existingCollect == null) {
		        // 會員尚未收藏過該文章，執行新增收藏的動作
		        return daoImpl.addCollect(collectVo);
		    } else {
		        // 會員已經收藏過該文章，直接返回null
		       daoImpl.deleteColArticle(memId, arNo);
		    	   return null;
		       
		    }
	}

	// 查詢會員收藏過的文章
	public CollectVo selectColArticleByMemId(Integer memId, Integer arNo) {
		return daoImpl.selectColArticleByMemId(memId, arNo);

	}
	
	// 會員取消收藏文章
		public void deleteColArticle(Integer memId, Integer arNo) {
			daoImpl.deleteColArticle(memId, arNo);
		}
		
		

}
