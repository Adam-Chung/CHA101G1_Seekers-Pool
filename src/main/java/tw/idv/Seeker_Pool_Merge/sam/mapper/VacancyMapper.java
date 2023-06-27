package tw.idv.Seeker_Pool_Merge.sam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import tw.idv.Seeker_Pool_Merge.sam.entity.Job;


@Mapper
public interface VacancyMapper {

//    新增職缺
    @Insert("insert into project.job(PT_NO, COM_MEM_ID, JOB_NAME, JOB_CONTENT, JOB_SALARY, JOB_TYPE, JOB_ADDRESS, JOB_OTHER, CITY_NAME, DISTRICT_NAME)" +
            " values(#{ptNo},#{comMemId},#{jobName},#{jobContent},#{jobSalary},#{jobType},#{jobAddress},#{jobOther},#{cityName},#{districtName})")
    void insert(Job job);

//    更新職缺
    @Update("update project.job set CITY_NAME =#{cityName}, DISTRICT_NAME =#{districtName}, JOB_NAME =#{jobName}, JOB_TYPE =#{jobType}, PT_NO =#{ptNo}," +
            " JOB_CONTENT =#{jobContent}, JOB_OTHER =#{jobOther}, JOB_ADDRESS =#{jobAddress}, JOB_SALARY =#{jobSalary} where JOB_NO = #{jobNo};")
    void update(Job job);

//    查詢全部職缺
    @Select("select * from project.job")
    List<Job> list();

//    查詢單筆職缺明細來編輯
    @Select("select * from project.job where JOB_NO = #{id}")
    List<Job> findVacancyById(Integer id);

//    新增職缺
    @Delete("delete from project.job where JOB_NO = #{id}")
    void delete(Integer id);

////    查詢總紀錄數
//    @Select("select count(*) from web001.job")
//    Integer count();
//
////    分頁查詢，獲取列表資料
////    @param start
////    @param pageSize
//    @Select("select * from web001.job limit #{start},#{pageSize}")  // start => 起始索引 , pageSize => 一頁要幾筆
//    List<Job> page(Integer start, Integer pageSize);
}
