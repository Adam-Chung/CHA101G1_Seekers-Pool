package tw.idv.Seeker_Pool_Merge.fong.dao;

import tw.idv.Seeker_Pool_Merge.fong.vo.CompanyMemberShowVo;

public interface ComApplyRecordDao {

	/**
	 * 將邀請資訊加入資料庫
	 * @param memId
	 * @param company
	 */
	void addInterviewInvite(Integer memId, CompanyMemberShowVo company, Integer jobNo);

}
