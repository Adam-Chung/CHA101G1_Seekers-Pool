package tw.idv.Seeker_Pool_Merge.sam.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import tw.idv.Seeker_Pool_Merge.sam.entity.Districts;
@Mapper
public interface DistrictsMapper {

//        根據資料庫 cities的 id 查詢對應的 districts資料
    @Select("select * from project.districts where city_id = #{id}")
    List<Districts> findDistrictById(Integer id);

    @Select("select c.id , c.CITY_NAME , D.DISTRICT_NAME FROM project.districts AS d left outer join cities c on d.city_id = c.id\n" +
            "where c.CITY_NAME like #{cityName}")
    List<Districts> findDistrictByCityName(String cityName);

    @Select("select * from project.districts")
    List<Districts> findAllDistricts();
}
