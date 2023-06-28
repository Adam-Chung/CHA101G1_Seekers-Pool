package tw.idv.Seeker_Pool_Merge.sam.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tw.idv.Seeker_Pool_Merge.sam.entity.Member;

import java.util.List;

@Mapper
public interface TalentSearchMapper {
public String areas = "";
//    mybatis 用CONCAT連接 '%KEYWORD%'
    @Select("select * from seeker_pool_schemas.member where SK_NO like CONCAT('%', #{keyword}, '%') or MEM_ADDRESS like CONCAT('%', #{keyword}, '%') or MEM_LANG like CONCAT('%', #{keyword}, '%')")
     List<Member> findTalentByKeyword(String keyword);

    List<Member> findByTalentByArea(@Param("areas") String[] areas);  // 寫在xml文件裡

    @Select("select * from seeker_pool_schemas.member where MEM_ID = #{memId}")
    List<Member> findTalentById(Integer memId);
}
