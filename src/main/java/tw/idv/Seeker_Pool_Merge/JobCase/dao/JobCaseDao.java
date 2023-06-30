package tw.idv.Seeker_Pool_Merge.JobCase.dao;

import java.util.List;

import org.hibernate.Session;

import tw.idv.Seeker_Pool_Merge.JobCase.util.HibernateUtil;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.ComOrderQueryVO;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.JobCaseVO;

public interface JobCaseDao {
	
		// 新增
		public boolean insert(JobCaseVO jc);
		
		// 更新
		public void update(JobCaseVO jc);

		// 刪除
		public void delete(JobCaseVO jc);

		// 查詢
		public JobCaseVO selectByNo(Integer jcNo);

		// 查詢全部
		public List<JobCaseVO> selectAll();
		
		// 查姓名
		public JobCaseVO selectByCaseName(String jcName);
		
		
		
		default Session getSession() {
			return HibernateUtil.getSessionFactory().getCurrentSession();
		}

		public List<ComOrderQueryVO> selectOrderCase(ComOrderQueryVO covo);
		
		public List<ComOrderQueryVO> selectOrderNo(Integer joNo);
		
}
