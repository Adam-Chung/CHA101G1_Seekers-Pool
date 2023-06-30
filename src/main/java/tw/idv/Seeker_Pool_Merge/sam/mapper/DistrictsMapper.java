package tw.idv.Seeker_Pool_Merge.sam.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import tw.idv.Seeker_Pool_Merge.sam.entity.Districts;

import java.util.List;
@Mapper
public interface DistrictsMapper {

//        根據資料庫 cities的 id 查詢對應的 districts資料
    @Select("select * from seeker_pool_schemas.districts where city_id = #{id}")
    List<Districts> findDistrictById(Integer id);

    @Select("select c.id , c.CITY_NAME , D.DISTRICT_NAME FROM seeker_pool_schemas.districts AS d left outer join cities c on d.city_id = c.id\n" +
            "where c.CITY_NAME like #{cityName}")
    List<Districts> findDistrictByCityName(String cityName);

    @Select("select * from seeker_pool_schemas.districts")
    List<Districts> findAllDistricts();
}
