package tw.idv.Seeker_Pool_Merge.fong.service.impl;

import tw.idv.Seeker_Pool_Merge.common.util.RandCodeUtil;
import tw.idv.Seeker_Pool_Merge.common.util.SendEmailUtil;
import tw.idv.Seeker_Pool_Merge.fong.dao.MemberDao;
import tw.idv.Seeker_Pool_Merge.fong.dao.impl.MemberDaoImpl;
import tw.idv.Seeker_Pool_Merge.fong.service.MemberService;
import tw.idv.Seeker_Pool_Merge.fong.vo.MemberVo;


public class MemberServiceImpl implements MemberService{
	
	private MemberDao memberDao = new MemberDaoImpl();

	@Override
	public Boolean registerMember(MemberVo member) {

		Boolean flag =  memberDao.findByMemAccount(member.getMemAccount());
		if(flag) {
			//有重複帳號
			return false;
		}
		//無重複帳號
		return memberDao.registerMember(member);
	}
	
	@Override
	public MemberVo loginMember(MemberVo member) {

		MemberVo memberLogin =  memberDao.findByAccountAndPassword(member.getMemAccount(), member.getMemPassword());
		return memberLogin;
	}

	@Override
	public MemberVo getMemberById(Integer id) {
		// TODO Auto-generated method stub
		return memberDao.getMemberById(id);
	}

	@Override
	public void updateMember(MemberVo member) {
		memberDao.updateMember(member);
		
	}

	@Override
	public String sendCheckCode(MemberVo member , String contextPath) {
		
		//得到隨機變數
		String randomCode = RandCodeUtil.getRandomCode(4);
		
		String emailTo = member.getMemEmail();
		String emailSubject = "【Seeker's Pool】 驗證碼"; 
		String name = member.getMemName();
		String messageText = name + " 您好，您的驗證碼為: " + randomCode + " \n 請立即返回驗證頁面進行帳號驗證，驗證碼有效時間為 20秒 \n http://localhost:8080"+ contextPath +"/front-end/member/member/checkcode.html "; 
		
		
		//寄出郵件
//		SendEmailUtil.sendMail(emailTo, emailSubject, messageText);
		//開立多執行續寄信(邊跳轉頁面邊寄信 效率更快)
		Thread t1 = new Thread(() -> SendEmailUtil.sendMail(emailTo, emailSubject, messageText));
		t1.start();
//		t1.stop();
		return randomCode;
	}

	
}
