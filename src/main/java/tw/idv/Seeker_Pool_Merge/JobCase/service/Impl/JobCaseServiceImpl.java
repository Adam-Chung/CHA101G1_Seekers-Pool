package tw.idv.Seeker_Pool_Merge.JobCase.service.Impl;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.JobCase.dao.JobCaseDao;
import tw.idv.Seeker_Pool_Merge.JobCase.dao.Impl.JobCaseDaoImpl;
import tw.idv.Seeker_Pool_Merge.JobCase.service.JobCaseService;
import tw.idv.Seeker_Pool_Merge.JobCase.vo.JobCaseVO;

public class JobCaseServiceImpl implements JobCaseService {
	private JobCaseDao JcDao;

	public JobCaseServiceImpl() {
		JcDao = new JobCaseDaoImpl();
	}

	@Override
	public JobCaseVO insert(JobCaseVO jc) {
		return null;
	}

	@Override
	public List<JobCaseVO> findAll() {
		return JcDao.selectAll();
	}

	@Override
	public boolean remove(JobCaseVO jc) {
//		int deleteCount = JcDao.delete(jc);
//	    return deleteCount > 0;
		return false;
	}

	@Override
	public boolean save(JobCaseVO jc) {
//		 int updateCount = JcDao.update(jc);
//		 return updateCount > 0;
		 return false;
	}

}
