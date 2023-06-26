package tw.idv.Seeker_Pool_Merge.fong.service.impl;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.common.util.SendEmailUtil;
import tw.idv.Seeker_Pool_Merge.fong.dao.ApplyRecordDao;
import tw.idv.Seeker_Pool_Merge.fong.dao.impl.ApplyRecordDaoImpl;
import tw.idv.Seeker_Pool_Merge.fong.service.ApplyRecordService;
import tw.idv.Seeker_Pool_Merge.fong.vo.ApplyRecordShowVo;
import tw.idv.Seeker_Pool_Merge.fong.vo.PageBean;

public class ApplyRecordServiceImpl implements ApplyRecordService {
	private ApplyRecordDao applyRecordDao = new ApplyRecordDaoImpl();

	@Override
	public PageBean<ApplyRecordShowVo> pageQuery(int memId, int currentPage, int pageSize, String keyWord, int filterNum) {
		// 封裝pageBean
		PageBean<ApplyRecordShowVo> pb = new PageBean<ApplyRecordShowVo>();
		pb.setCurrentPage(currentPage);
		pb.setPageSize(pageSize);

		// 設置總記錄數
		int totalCount = applyRecordDao.findTotalCount(memId, keyWord, filterNum);
		pb.setTotalCount(totalCount);

		// 設置當前頁數據集合
		int start = (currentPage - 1) * pageSize;// 開始的紀錄數
		List<ApplyRecordShowVo> list = applyRecordDao.findByPage(memId, start, pageSize, keyWord, filterNum);
		pb.setList(list);

		// 設置總頁數
		int totalPage = (totalCount % pageSize) == 0 ? (totalCount / pageSize) : (totalCount / pageSize) + 1;
		pb.setTotalPage(totalPage);

		return pb;
	}

	@Override
	public void cancelInterview(String comName, String jobName, Integer memId, String memName) {
		
		applyRecordDao.cancelInterview(comName, jobName, memId);

		// 發信通知寄企業
		String comEmail = applyRecordDao.getComEmailByComName(comName);

		String emailTo = comEmail;
		String emailSubject = "【Seeker's Pool】 應徵者取消面試通知";
		String name = comName;
		String messageText = name + " 您好 \n 應徵者: " + memName + " ，應徵職缺為 " + jobName
				+ "  \n 取消面試，再請到 Seeker's Pool 網站確認，謝謝";

		// 寄出郵件
		SendEmailUtil.sendMail(emailTo, emailSubject, messageText);

	}

	@Override
	public void updateInterviewTime(Integer jobId, Integer memId, String dateTime) {
		applyRecordDao.updateInterviewTime(jobId, memId, dateTime);
	}
}
