package tw.idv.Seeker_Pool_Merge.song.dao;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.song.vo.SignupRecordVo;

public interface SignupRecordDao {
          public boolean insert(SignupRecordVo  signupRecordVO );
          public void update(SignupRecordVo  signupRecordVO );
          public void delete(Integer srNo,Integer srStatus);
          public SignupRecordVo  findByPrimaryKey(Integer signupRecord);
          public List<SignupRecordVo > getAll();
          
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
