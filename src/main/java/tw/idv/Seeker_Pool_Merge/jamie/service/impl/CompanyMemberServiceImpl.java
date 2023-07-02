package tw.idv.Seeker_Pool_Merge.jamie.service.impl;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.common.util.RandCodeUtil;
import tw.idv.Seeker_Pool_Merge.common.util.SendEmailUtil;
import tw.idv.Seeker_Pool_Merge.jamie.dao.CompanyMemberDao;
import tw.idv.Seeker_Pool_Merge.jamie.dao.impl.CompanyMemberDaoImpl;
import tw.idv.Seeker_Pool_Merge.jamie.service.CompanyMemberService;
import tw.idv.Seeker_Pool_Merge.jamie.vo.CompanyMemberVo;

public class CompanyMemberServiceImpl implements CompanyMemberService {
	
//	@Autowired
//	private CompanyMemberDao dao;
//	
//	@Autowired
//	public CompanyMemberServiceImpl(CompanyMemberDao dao) {
//        this.dao = dao;
//    }
	
	private CompanyMemberDao dao = new CompanyMemberDaoImpl();

	@Override
	public boolean registerCompanyMember(CompanyMemberVo companyMember) {
		// 1. 根據帳號名稱來查詢會員
		boolean flag =
				dao.findComMemByAccount(companyMember.getComMemAccount());		
		// 2. 判斷 comMem 是否為null
		if (flag) {
			// 代表該帳號已存在，因此註冊失敗
			return false;
		}		
		// 3. 沒有重複，即儲存這個會員資訊
		return dao.registerCompanyMember(companyMember);
	}

	// 登入功能
	@Override
	public CompanyMemberVo login(CompanyMemberVo companyMember) {
		CompanyMemberVo loginComMem =
				dao.findByAccountAndPassword(companyMember.getComMemAccount(), companyMember.getComMemPassword());
		
		return loginComMem;
	}

	// 企業會員在前台修改會員資料需要
	@Override
	public CompanyMemberVo getCompanyMemberById(int id) {
		return dao.getComMemById(id);
	}

	@Override
	public String sendValidCode(CompanyMemberVo companyMember) {
		// 取得隨機驗證碼
		String randomCode = RandCodeUtil.getRandomCode(6);
		// 寄信給使用者
		String email = companyMember.getComEmail();
		String subject = "【Seeker's Pool】驗證碼通知";
		String name = companyMember.getComName();
		String emailText = name + "，您好！\n您的驗證碼是：" + randomCode + "\n請於5分鐘內驗證完成！";
		
//		SendEmailUtil.sendMail(email, subject, emailText);
		// 開立多執行緒寄信(邊跳轉頁面邊寄信 效率更快)
		Thread t1 = new Thread(() -> SendEmailUtil.sendMail(email, subject, emailText));
		t1.start();
		
		return randomCode;
	}
	
	@Override
	public boolean checkValidCode(String inputValidCode, String validCode) {
		if (inputValidCode != null && inputValidCode.equals(validCode)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public void updateCompanyMember(CompanyMemberVo companyMember) {
		dao.updateCompanyMember(companyMember);
	}
	
	@Override
	public void updateCompanyStatus(CompanyMemberVo companyMember) {
		dao.updateCompanyStatus(companyMember);
	}
	
	@Override
	public List<CompanyMemberVo> findAll() {
		return dao.findAll();
	}

	@Override
	public CompanyMemberVo getInfoForApplicants(int id) {
		return dao.getInfoForApplicants(id);
	}

}
