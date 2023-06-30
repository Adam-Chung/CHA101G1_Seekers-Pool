package tw.idv.Seeker_Pool_Merge.JobCase.dao;

import java.util.List;

import org.hibernate.Session;

import tw.idv.Seeker_Pool_Merge.JobCase.util.HibernateUtil;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.ComJobQueryVO;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.JobVO;

public interface JobDao {
	
	// 新增
	public boolean insert(JobVO job);
	
	// 更新
	public void update(JobVO job);
	
	// 更新上架狀態
	public boolean updateJoStatus(JobVO job);

	// 更新置頂狀態
	public void updateJobUploadStatus(JobVO JobVo);
	
	public void updateJobTopStatus(JobVO JobVo);
	
	// 刪除
	public void delete(JobVO job);

	// 查詢
	public JobVO selectByNo(Integer jobNo);

	// 查詢全部
	public List<JobVO> selectAll();
	
	// 查企業訂單
	public JobVO selectByJobStatus(Integer jobStatus);
	
	// 查職缺狀態
	public List<ComJobQueryVO> selectJobStatus(ComJobQueryVO vo);
	
	
	
	default Session getSession() {
		return HibernateUtil.getSessionFactory().getCurrentSession();
	}


}
