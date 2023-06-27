package tw.idv.Seeker_Pool_Merge.sam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import tw.idv.Seeker_Pool_Merge.sam.entity.Cities;

@Mapper
public interface CitiesMapper {
    // 查詢全部縣市
    @Select("select * from project.cities order by id")
    List<Cities> list();
}
