package tw.idv.Seeker_Pool_Merge.jamie.vo;

public class JobVo {
	
	private Integer jobNo;
	private String jobName;
	private Integer memId;
	private Integer comMemId;
//	private String comName;
//	private String comAddress;
//	private String comPicture;
	private Integer jobStatus;
	
	public Integer getJobNo() {
		return jobNo;
	}
	public void setJobNo(Integer jobNo) {
		this.jobNo = jobNo;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getComMemId() {
		return comMemId;
	}
	public void setComMemId(Integer comMemId) {
		this.comMemId = comMemId;
	}
//	public String getComName() {
//		return comName;
//	}
//	public void setComName(String comName) {
//		this.comName = comName;
//	}
//	public String getComAddress() {
//		return comAddress;
//	}
//	public void setComAddress(String comAddress) {
//		this.comAddress = comAddress;
//	}
//	public String getComPicture() {
//		return comPicture;
//	}
//	public void setComPicture(String comPicture) {
//		this.comPicture = comPicture;
//	}
	public Integer getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(Integer jobStatus) {
		this.jobStatus = jobStatus;
	}
	
	@Override
	public String toString() {
		return "JobVo [jobNo=" + jobNo + ", jobName=" + jobName + ", memId=" + memId + ", comMemId=" + comMemId
				+ ", jobStatus=" + jobStatus + "]";
	}

}
