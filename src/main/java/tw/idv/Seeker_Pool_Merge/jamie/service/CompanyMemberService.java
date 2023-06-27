package tw.idv.Seeker_Pool_Merge.jamie.service;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.jamie.vo.CompanyMemberVo;

public interface CompanyMemberService {

	/*
	 *  調用Dao, 根據用戶名稱(帳號)查詢用戶是否重複
	 *  ．帳號存在的話返回false
	 *  ．帳號不存在的話，調用Dao保存一個新的帳號資訊
	 */
	boolean registerCompanyMember(CompanyMemberVo companyMember);
	
	// 登入功能
	CompanyMemberVo login(CompanyMemberVo companyMember);
	
	CompanyMemberVo getCompanyMemberById(int id);
	
	String sendValidCode(CompanyMemberVo companyMember);
	
	boolean checkValidCode(String inputValidCode, String validCode);
	
	void updateCompanyMember(CompanyMemberVo companyMember);

	void updateCompanyStatus(CompanyMemberVo companyMember);
	
	public List<CompanyMemberVo> findAll();
	
	public CompanyMemberVo getInfoForApplicants(int id);
	
}
