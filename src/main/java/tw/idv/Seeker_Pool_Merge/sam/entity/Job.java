package tw.idv.Seeker_Pool_Merge.sam.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "JOB_NO") // 職缺編號
    private Integer jobNo;

    @Column(name = "COM_MEM_ID") // 企業編號
    private Integer comMemId;

    @Column(name = "PT_NO") // 職務編號
    private Integer ptNo;

    @Column(name = "JO_NO") // 訂單編號
    private Integer joNo;

    @Column(name = "JOB_NAME") // 職缺名稱
    private String jobName;

    @Column(name = "JOB_CONTENT") // 工作內容
    private String jobContent;

    @Column(name = "JOB_SALARY") // 工作待遇
    private Integer jobSalary;

    @Column(name = "JOB_TYPE") // 工作性質 (0:全職, 1:兼職) (default: 1)
    private Integer jobType;

    @Column(name = "CITY_NAME") // 工作縣市id
    private String cityName;

    @Column(name = "DISTRICT_NAME") // 工作鄉鎮市區
    private String districtName;

    @Column(name = "JOB_ADDRESS") // 工作地址
    private String jobAddress;

    @Column(name = "JOB_OTHER") // 其他條件
    private String jobOther;

    @Column(name = "JOB_STATUS") // 職缺狀態 (0:未刊登, 1:刊登中, 2:被檢舉已下架)(沒有在用)
    private Integer jobStatus;

    @Column(name = "JOB_UPLOAD") // 是否上架職缺 (0 : 已上架 1: 未上架) (default: 1)
    private Integer JobUpload;

    @Column(name = "JOB_TOP") // 是否置頂職缺 (0 : 已置頂 1: 未置頂) (default: 1)
    private Integer jobTop;
}
