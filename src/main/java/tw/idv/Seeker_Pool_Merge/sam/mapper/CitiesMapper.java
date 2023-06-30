package tw.idv.Seeker_Pool_Merge.sam.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tw.idv.Seeker_Pool_Merge.sam.entity.Cities;


import java.util.List;

@Mapper
public interface CitiesMapper {
    // 查詢全部縣市
    @Select("select * from seeker_pool_schemas.cities order by id")
    List<Cities> list();
}
