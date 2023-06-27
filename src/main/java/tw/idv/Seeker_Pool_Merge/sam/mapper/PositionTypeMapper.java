package tw.idv.Seeker_Pool_Merge.sam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import tw.idv.Seeker_Pool_Merge.sam.entity.PositionType;

@Mapper
public interface PositionTypeMapper {

    // 根據資料庫 position_type 查詢對應的 name 資料
    @Select("SELECT * FROM project.position_type where PT_TYPE like #{type}")
    List<PositionType> findNameByType(String type);

//    查詢全部資料
    @Select("select * from project.position_type")
    List<PositionType> list();
}
