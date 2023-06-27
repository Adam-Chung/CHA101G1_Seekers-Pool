package tw.idv.Seeker_Pool_Merge.jamie.dao;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.jamie.vo.CompanyMemberVo;

public interface CompanyMemberDao {
	
	// 註冊
	boolean registerCompanyMember(CompanyMemberVo companyMember);
	
	// 根據帳號獲取一個企業會員資訊
	// 如果返回null，代表沒有這個用戶
	boolean findComMemByAccount(String comMemAccount);
	
	// 更新一個企業會員的資訊
	void updateCompanyMember(CompanyMemberVo companyMember);

	CompanyMemberVo findByAccountAndPassword(String comMemAccount, String comMemPassword);
	
	// 根據企業ID獲取一個企業會員資訊
	CompanyMemberVo getComMemById(int id);

	void updateCompanyStatus(CompanyMemberVo companyMember);
	
	// 後台使用：查詢所有企業會員
	List<CompanyMemberVo> findAll();
	
	// 企業的對外資訊頁使用
	CompanyMemberVo getInfoForApplicants(int id);
	
}
