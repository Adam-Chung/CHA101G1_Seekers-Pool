package tw.idv.Seeker_Pool_Merge.JobCase.service;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.JobCase.vo.JobCaseVO;

public interface JobCaseService {
	
	JobCaseVO insert(JobCaseVO jc);
	
	List<JobCaseVO> findAll();
	
	boolean remove(JobCaseVO jc);
	
	boolean save(JobCaseVO jc); 

}
