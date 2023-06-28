package tw.idv.Seeker_Pool_Merge.sam.mapper;

import org.apache.ibatis.annotations.*;
import tw.idv.Seeker_Pool_Merge.sam.entity.Job;

import java.util.List;


@Mapper
public interface VacancyMapper {

//    新增職缺
    @Insert("insert into seeker_pool_schemas.job(PT_NO, COM_MEM_ID, JOB_NAME, JOB_CONTENT, JOB_SALARY, JOB_TYPE, JOB_ADDRESS, JOB_OTHER, CITY_NAME, DISTRICT_NAME)" +
            " values(#{ptNo},#{comMemId},#{jobName},#{jobContent},#{jobSalary},#{jobType},#{jobAddress},#{jobOther},#{cityName},#{districtName})")
    void insert(Job job);

//    更新職缺
    @Update("update seeker_pool_schemas.job set CITY_NAME =#{cityName}, DISTRICT_NAME =#{districtName}, JOB_NAME =#{jobName}, JOB_TYPE =#{jobType}, PT_NO =#{ptNo}," +
            " JOB_CONTENT =#{jobContent}, JOB_OTHER =#{jobOther}, JOB_ADDRESS =#{jobAddress}, JOB_SALARY =#{jobSalary} where JOB_NO = #{jobNo};")
    void update(Job job);

//    查詢全部職缺(有包含了分頁查詢)
    @Select("select * from seeker_pool_schemas.job")
    List<Job> list();

//    查詢單筆職缺明細來編輯
    @Select("select * from seeker_pool_schemas.job where JOB_NO = #{id}")
    List<Job> findVacancyById(Integer id);

//    刪除職缺
    @Delete("DELETE FROM seeker_pool_schemas.skill_request WHERE JOB_NO = #{id}")
    void deleteSkillRequest(Integer id);
    @Delete("DELETE FROM seeker_pool_schemas.report_enterprise WHERE JOB_NO = #{id}")
    void deleteReportEnterprise(Integer id);

    @Delete("DELETE FROM seeker_pool_schemas.collect_job WHERE JOB_NO = #{id}")
    void deleteCollectJob(Integer id);

    @Delete("DELETE FROM seeker_pool_schemas.job WHERE JOB_NO = #{id}")
    void deleteJob(Integer id);

//    @Delete("delete from seeker_pool_schemas.job where JOB_NO = #{id}")
//    void delete(Integer id);

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
