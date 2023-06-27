package tw.idv.Seeker_Pool_Merge.sam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tb_emp {
    private Integer id;
    private String username;
    private String password;
    private String empName;
    private String gender;
    private String image;
    private Short jobTitle;
    private LocalDate onboardDate;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer deptId;

}
