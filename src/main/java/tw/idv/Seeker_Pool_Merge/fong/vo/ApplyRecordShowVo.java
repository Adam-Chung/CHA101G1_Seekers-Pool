package tw.idv.Seeker_Pool_Merge.fong.vo;

import java.sql.Date;

public class ApplyRecordShowVo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String comName;
	private Integer jobNo;
	private String jobName;
	private Date applyDate;
	private String interDate;
	private String hireStatus;
	
	
	public Integer getJobNo() {
		return jobNo;
	}
	public void setJobNo(Integer jobNo) {
		this.jobNo = jobNo;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getInterDate() {
		return interDate;
	}
	public void setInterDate(String interDate) {
		this.interDate = interDate;
	}
	public String getHireStatus() {
		return hireStatus;
	}
	public void setHireStatus(String hireStatus) {
		this.hireStatus = hireStatus;
	}
	
	
}
