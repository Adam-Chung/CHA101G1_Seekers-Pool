package tw.idv.Seeker_Pool_Merge.fong.dao;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.fong.vo.JobVo;

public interface JobDao {

	/**
	 * 透過會員id找到總收藏數量
	 * @param memId
	 * @return
	 */
	int findTotalCount(int memId);

	/**
	 * 透過頁數找到資料
	 * @param memId
	 * @param start
	 * @param pageSize
	 * @return
	 */
	List<JobVo> findByPage(int memId, int start, int pageSize);

	/**
	 * 刪除收藏文章
	 * @param arNo
	 * @param memId
	 */
	void deletColJobByMemIdAndArNo(Integer jobNo, Integer memId);

	/**
	 * 透過jobNO找到jobName
	 * @param jobNo
	 * @return
	 */
	String getJobNameByJobNo(Integer jobNo);

}
