package tw.idv.Seeker_Pool_Merge.jamie.dao;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.jamie.vo.JobVo;

public interface JobDao {
	
	public List<JobVo> findJobsByComMemId (int comMemId);
	
	public JobVo findJobName (int jobNo, int comMemId);

}
