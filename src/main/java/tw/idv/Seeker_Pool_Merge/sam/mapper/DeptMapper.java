package tw.idv.Seeker_Pool_Merge.sam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import tw.idv.Seeker_Pool_Merge.sam.entity.Tb_dept;

@Mapper
public interface DeptMapper {
//    查詢全部系所
    @Select("select * from heima.tb_dept")
    List<Tb_dept> list();

//    根據id刪除系所
//    @param id
    @Delete("delete from heima.tb_dept where id = #{id}")
    void deleteById(Integer id);

//    新增系所
//    @param tbDept
    @Insert("insert into heima.tb_dept(name, create_time, update_time) values (#{name},#{createTime},#{updateTime})")
    void insert(Tb_dept tbDept);
}
