package tw.idv.Seeker_Pool_Merge.song.dao;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.song.vo.JobRecruitmentArticleVo;

public interface JobRecruitmentArticleDao {
          public boolean insert(JobRecruitmentArticleVo jobRecruitmentArticleVO);
          public void update(JobRecruitmentArticleVo jobRecruitmentArticleVO);
          public void delete(Integer jobRecruitmentArticle);
          public JobRecruitmentArticleVo findByPrimaryKey(Integer jobRecruitmentArticle);
          public List<JobRecruitmentArticleVo> getAll();
          
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
