package tw.idv.Seeker_Pool_Merge.fong.dao;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.fong.vo.ApplyRecordShowVo;

public interface ApplyRecordDao {

	/**
	 * 透過條件得到總資料數量
	 * @param memId
	 * @param keyWord
	 * @param filterNum
	 * @return
	 */
	int findTotalCount(int memId, String keyWord, int filterNum);

	/**
	 * 用頁數、篩選條件獲得資料
	 * @param memId
	 * @param start
	 * @param pageSize
	 * @param keyWord
	 * @param filterNum
	 * @return
	 */
	List<ApplyRecordShowVo> findByPage(int memId, int start, int pageSize, String keyWord, int filterNum);

	/**
	 * 取消面試
	 * @param arNo
	 * @param memId
	 */
	void cancelInterview(String comName, String jobName, Integer memId);

	/**
	 *用公司名稱獲得公司信箱
	 * @param comName
	 * @return
	 */
	String getComEmailByComName(String comName);

	/**
	 * 加入面試時間
	 * @param jobId
	 * @param memId
	 * @param dateTime
	 */
	void updateInterviewTime(Integer jobId, Integer memId, String dateTime);

}
