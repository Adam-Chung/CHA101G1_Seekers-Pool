package tw.idv.Seeker_Pool_Merge.fong.dao;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.fong.vo.CompanyMemberShowVo;

public interface CompanyDao {

	/**
	 * 透過memId找到所有屏蔽公司
	 * @param memId
	 * @return
	 */
	List<CompanyMemberShowVo> findBlockComsByMemId(Integer memId);

	/**
	 * 透過公司名稱，找是否有該公司
	 * @param addCompanyName
	 * @return
	 */
	CompanyMemberShowVo findComByName(String addCompanyName);

	/**
	 * 透過memId、comId確認是否已存在於屏蔽名單中
	 * @param memId
	 * @param comMemId
	 * @return
	 */
	Boolean findBlockComByMemIdAndComMemId(Integer memId, Integer comMemId);

	/**
	 * 加入屏蔽名單
	 * @param memId
	 * @param comMemId
	 */
	void addBlockComBy(Integer memId, Integer comMemId);

	/**
	 * 刪除該屏蔽企業
	 * @param deleteBlockCom
	 */
	void deletBlockComByName(Integer memId, String deleteBlockCom);

	/**
	 * 用企業id找企業名稱
	 * @param deleteBlockCom
	 */
	String getComNameBycomId(Integer comId);
}
