package tw.idv.Seeker_Pool_Merge.sam.service;

import java.util.List;

import tw.idv.Seeker_Pool_Merge.sam.entity.Tb_dept;


public interface DeptService {


//    查詢全部系所數據
    List<Tb_dept> list();

//    刪除系所
//    @param id
    void delete(Integer id);

//    新增系所
//    @param tbDept
    void add(Tb_dept tbDept);
}
