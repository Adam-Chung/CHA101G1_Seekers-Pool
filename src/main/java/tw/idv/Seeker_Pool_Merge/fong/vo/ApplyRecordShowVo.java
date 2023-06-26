package tw.idv.Seeker_Pool_Merge.fong.vo;

import java.sql.Date;

public class ApplyRecordShowVo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String comName;
	private String jobName;
	private Date applyDate;
	private String interDate;
	private String hireStatus;
	
	@Override
	public String toString() {
		return "ApplyRecordVo [comName=" + comName + ", jobName=" + jobName + ", applyDate=" + applyDate
				+ ", interDate=" + interDate + ", hireStatus=" + hireStatus + "]";
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
