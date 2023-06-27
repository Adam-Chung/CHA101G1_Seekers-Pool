package tw.idv.Seeker_Pool_Merge.sam.mapper;
import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import tw.idv.Seeker_Pool_Merge.sam.entity.Emp;
import tw.idv.Seeker_Pool_Merge.sam.entity.Tb_emp;

@Mapper
public interface EmpMapper {

    // 根據id刪除資料
//    @Delete("delete from tb_emp where id = #{id} ")
//    public void delete(Integer id);


    // 新增員工
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into tb_emp( username, emp_name, gender, image, job_title, onboard_date, create_time, update_time, dept_id)" +
            "values (#{username},#{empName},#{gender},#{image},#{jobTitle},#{onboardDate},#{createTime},#{updateTime},#{deptId})")
    public void insert(Tb_emp tbEmp);

    // 編輯員工資料
//    @Update("update tb_emp set username = #{username}, emp_name= #{empName}, gender = #{gender}, image = #{image}," +
//    "job_title = #{jobTitle}, onboard_date = #{onboardDate}, update_time = #{updateTime}, dept_id = #{deptId} where id = #{id}")
//    public void update(Tb_emp tbEmp);

    // 根據id 查詢員工
    @Select("select id, username, password, emp_name empName, gender, image, job_title jobTitle, onboard_date onboardDate, " +
            " create_time createTime, update_time updateTime, dept_id deptId from tb_emp where id = #{id}")
    public Tb_emp getById(Integer id);

    // 條件查詢員工資料(姓名模糊比對、姓名精確比對、入職日期是範圍搜尋、 依入職日期做降冪排序)
//    @Select("select * from tb_emp where emp_name like concat('%',#{empName},'%') and gender  = #{gender} and" +
//            " onboard_date between #{begin} and #{end} order by update_time desc ;")
//    public List<Tb_emp> list(String empName, Short gender, LocalDate begin, LocalDate end);

    //動態條件查詢
    public List<Tb_emp> list(String empName, Short gender, LocalDate begin, LocalDate end);

    //編輯員工資料
    public void update(Emp emp);

    //批量刪除員工資料
    public void deleteByIds(List<Integer> ids);
}
