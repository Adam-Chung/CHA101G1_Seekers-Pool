package tw.idv.Seeker_Pool_Merge.fong.service;

import tw.idv.Seeker_Pool_Merge.fong.vo.MemberVo;

public interface MemberService {

	/**
	 * 註冊用戶
	 * @param member
	 * @return
	 */
	Boolean registerMember(MemberVo member);

	/**
	 * 用戶登入
	 * @param member
	 * @return
	 */
	MemberVo loginMember(MemberVo member);
	

	/**
	 * 取得member
	 * @param id
	 * @return
	 */
	MemberVo getMemberById(Integer id);

	/**
	 * 更新member訊息
	 * @param member
	 */
	void updateMember(MemberVo member);

	/**
	 * 寄信給註冊者
	 * @param member
	 * @param contextPath 
	 * @return
	 */
	String sendCheckCode(MemberVo member, String contextPath);

	
}
