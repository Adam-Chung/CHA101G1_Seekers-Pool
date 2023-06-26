package tw.idv.Seeker_Pool_Merge.fong.dao;

import tw.idv.Seeker_Pool_Merge.fong.vo.MemberVo;

public interface MemberDao {
	
	
	public MemberVo getMemberById(int id);
	
	/**
	 * 一般會員註冊
	 * @param member
	 * @return 註冊是否成功
	 */
	public boolean registerMember(MemberVo member);

	/**
	 * 是否存在此MemAccount
	 * @param memAccount
	 * @return
	 */
	public Boolean findByMemAccount(String memAccount);
	

	/**
	 * 透過帳號密碼找member
	 * @param memAccount
	 * @param memPassword
	 * @return
	 */
	public MemberVo findByAccountAndPassword(String memAccount, String memPassword);

	/**
	 * 更新會員資訊
	 * @param member
	 */
	public void updateMember(MemberVo member);
}
