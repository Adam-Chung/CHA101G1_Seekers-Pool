package tw.idv.Seeker_Pool_Merge.fong.service;

import tw.idv.Seeker_Pool_Merge.fong.vo.JobVo;
import tw.idv.Seeker_Pool_Merge.fong.vo.PageBean;

public interface JobService {

	/**
	 * 透過頁數回傳資料
	 * @param memId
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageBean<JobVo> pageQuery(int memId, int currentPage, int pageSize);

	/**
	 * 刪除收藏文章
	 * @param arNo
	 * @param memId
	 */
	void deletColJobByMemIdAndArNo(Integer jobNo, Integer memId);

}
