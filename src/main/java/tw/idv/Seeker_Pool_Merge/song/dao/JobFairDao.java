package tw.idv.Seeker_Pool_Merge.song.dao;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.song.vo.JobFairVo;

public interface JobFairDao {
          public boolean insert(JobFairVo jobFairVO);
          public void update(JobFairVo jobFairVO);
          public void delete(Integer jfNo,Integer jrStatus);
          public List<JobFairVo> findBySeason(String season);
          public List<JobFairVo> getAll();
          
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
