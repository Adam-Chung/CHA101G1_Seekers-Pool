package tw.idv.Seeker_Pool_Merge.song.dao;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.song.vo.AdministratorVo;

public interface AdministratorDao {
          public void insert(AdministratorVo administratorVO);
          public void update(AdministratorVo administratorVO);
          public void delete(Integer administrator);
          public AdministratorVo findByPrimaryKey(Integer administrator);
          public List<AdministratorVo> getAll();
//        public List<EmpVO> getAll(Map<String, String[]> map); 
}
