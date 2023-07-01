package tw.idv.Seeker_Pool_Merge.jamie.service.impl;

import tw.idv.Seeker_Pool_Merge.common.util.SendEmailUtil;
import tw.idv.Seeker_Pool_Merge.jamie.dao.ApplyRecordDao;
import tw.idv.Seeker_Pool_Merge.jamie.dao.CompanyMemberDao;
import tw.idv.Seeker_Pool_Merge.jamie.dao.JobDao;
import tw.idv.Seeker_Pool_Merge.jamie.dao.impl.ApplyRecordDaoImpl;
import tw.idv.Seeker_Pool_Merge.jamie.dao.impl.CompanyMemberDaoImpl;
import tw.idv.Seeker_Pool_Merge.jamie.dao.impl.JobDaoImpl;
import tw.idv.Seeker_Pool_Merge.jamie.service.ApplyRecordService;

public class ApplyRecordServiceImpl implements ApplyRecordService {
	
	private CompanyMemberDao companyMemberDao = new CompanyMemberDaoImpl();
	private ApplyRecordDao applyRecordDao = new ApplyRecordDaoImpl();
	private JobDao jobDao = new JobDaoImpl();
	
//	@Autowired
//    public ApplyRecordServiceImpl(CompanyMemberDao companyMemberDao) {
//        this.companyMemberDao = companyMemberDao;
//    }

	@Override
	public void cancelInterview(int memId, int comMemId, int jobNo) {
		
		String memName = applyRecordDao.findMemberById(memId).getMemName();
		String email = applyRecordDao.findMemberById(memId).getMemEmail();
		String comName = companyMemberDao.getComMemById(comMemId).getComName();
		String jobName = jobDao.findJobName(jobNo, comMemId).getJobName();
		String subject = "【Seeker's Pool】取消面試通知";
		
		String emailText = "親愛的 " + memName + "，您好：\n\n您所應徵的職缺 " + comName + " " + jobName +
				"\n該面試已取消，\n請至 Seeker's Pool 網站確認，謝謝您！";
		
		SendEmailUtil.sendMail(email, subject, emailText);
		
	}

}
