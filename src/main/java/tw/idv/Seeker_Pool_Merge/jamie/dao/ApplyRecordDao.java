package tw.idv.Seeker_Pool_Merge.jamie.dao;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.jamie.vo.ApplyRecordVo;

public interface ApplyRecordDao {
	
	// 藉由企業id找應徵者
	public List<ApplyRecordVo> findApplicantsByComMemId (int comMemId);
	
	public ApplyRecordVo findMemberById (int memId);
	
//	public List<ApplyRecordVo> findJobsByComMemId (int comMemId);
	
	public int updateHireStatus(int memId, int comMemId, int jobNo);
	
	public int cancelInterview(int memId, int comMemId, int jobNo);

}
