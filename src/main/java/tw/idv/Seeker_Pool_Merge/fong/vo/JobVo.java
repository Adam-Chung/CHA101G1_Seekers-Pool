package tw.idv.Seeker_Pool_Merge.fong.vo;

import java.sql.Date;

public class JobVo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer jobNo;
	private Integer comMemId;
	private Integer ptNo;
	private String cityName; 
	private String districtName; 
	private Integer joNo;
	private String jobName;
	private String jobContent;
	private Integer jobSalary;
	private Integer jobType;
	private String jobAddress;
	private String jobOther;
	private Integer jobStatus;
	private Boolean jobUpload;
	private Boolean jobTop;
	private Date collectDate;
	
	
	
	@Override
	public String toString() {
		return "JobVo [jobNo=" + jobNo + ", comMemId=" + comMemId + ", ptNo=" + ptNo + ", cityName=" + cityName
				+ ", districtName=" + districtName + ", joNo=" + joNo + ", jobName=" + jobName + ", jobContent="
				+ jobContent + ", jobSalary=" + jobSalary + ", jobType=" + jobType + ", jobAddress=" + jobAddress
				+ ", jobOther=" + jobOther + ", jobStatus=" + jobStatus + ", jobUpload=" + jobUpload + ", jobTop="
				+ jobTop + ", collectDate=" + collectDate + "]";
	}
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
	public Boolean getJobUpload() {
		return jobUpload;
	}
	public void setJobUpload(Boolean jobUpload) {
		this.jobUpload = jobUpload;
	}
	public Boolean getJobTop() {
		return jobTop;
	}
	public void setJobTop(Boolean jobTop) {
		this.jobTop = jobTop;
	}
	public Date getCollectDate() {
		return collectDate;
	}
	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}
	
	
	
	
	
}