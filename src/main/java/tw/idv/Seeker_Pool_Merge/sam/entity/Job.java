package tw.idv.Seeker_Pool_Merge.sam.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer jobNo;

    private Integer comMemId;

    private Integer ptNo;

    private Integer joNo;

    private String jobName;

    private String jobContent;

    private Integer jobSalary;

    // 工作性質 (0:全職, 1:兼職) (default: 1)
    private Integer jobType;

     // 工作縣市id
    private String cityName;

     // 工作鄉鎮市區
    private String districtName;

     // 工作地址
    private String jobAddress;

     // 其他條件
    private String jobOther;

     // 職缺狀態 (0:未刊登, 1:刊登中, 2:被檢舉已下架)(沒有在用)
    private Integer jobStatus;

     // 是否上架職缺 (0 : 未上架 1: 已上架) (default: 0)
    private Integer JobUpload;

     // 是否置頂職缺 (0 : 未置頂 1: 已置頂) (default: 0)
    private Integer jobTop;

    public Integer getJobNo() {
        return jobNo;
    }

    public void setJobNo(Integer jobNo) {
        this.jobNo = jobNo;
    }

    public Integer getComMemId() {
        return comMemId;
    }

    public void setComMemId(Integer comMemId) {
        this.comMemId = comMemId;
    }

    public Integer getPtNo() {
        return ptNo;
    }

    public void setPtNo(Integer ptNo) {
        this.ptNo = ptNo;
    }

    public Integer getJoNo() {
        return joNo;
    }

    public void setJoNo(Integer joNo) {
        this.joNo = joNo;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobContent() {
        return jobContent;
    }

    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }

    public Integer getJobSalary() {
        return jobSalary;
    }

    public void setJobSalary(Integer jobSalary) {
        this.jobSalary = jobSalary;
    }

    public Integer getJobType() {
        return jobType;
    }

    public void setJobType(Integer jobType) {
        this.jobType = jobType;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getJobAddress() {
        return jobAddress;
    }

    public void setJobAddress(String jobAddress) {
        this.jobAddress = jobAddress;
    }

    public String getJobOther() {
        return jobOther;
    }

    public void setJobOther(String jobOther) {
        this.jobOther = jobOther;
    }

    public Integer getJobStatus() {
        return jobStatus;
    }

    public void setJobStatus(Integer jobStatus) {
        this.jobStatus = jobStatus;
    }

    public Integer getJobUpload() {
        return JobUpload;
    }

    public void setJobUpload(Integer jobUpload) {
        JobUpload = jobUpload;
    }

    public Integer getJobTop() {
        return jobTop;
    }

    public void setJobTop(Integer jobTop) {
        this.jobTop = jobTop;
    }
}
