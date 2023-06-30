package tw.idv.Seeker_Pool_Merge.JobCase.vo;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

//@Data
@Entity
public class ComJobQueryVO {

	@Id
	private Integer jobNo;
	private String jobName;
	private Integer comMemId;
	private Integer joNo;
	private Integer jobUpload;
	private Integer jobTop;
	private Timestamp jcDeadline;
	private Integer jcNo;
	private Integer jcAvailableNum;
	private Integer jcTop;
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
	public Integer getComMemId() {
		return comMemId;
	}
	public void setComMemId(Integer comMemId) {
		this.comMemId = comMemId;
	}
	public Integer getJoNo() {
		return joNo;
	}
	public void setJoNo(Integer joNo) {
		this.joNo = joNo;
	}
	public Integer getJobUpload() {
		return jobUpload;
	}
	public void setJobUpload(Integer jobUpload) {
		this.jobUpload = jobUpload;
	}
	public Integer getJobTop() {
		return jobTop;
	}
	public void setJobTop(Integer jobTop) {
		this.jobTop = jobTop;
	}
	public Timestamp getJcDeadline() {
		return jcDeadline;
	}
	public void setJcDeadline(Timestamp jcDeadline) {
		this.jcDeadline = jcDeadline;
	}
	public Integer getJcNo() {
		return jcNo;
	}
	public void setJcNo(Integer jcNo) {
		this.jcNo = jcNo;
	}
	public Integer getJcAvailableNum() {
		return jcAvailableNum;
	}
	public void setJcAvailableNum(Integer jcAvailableNum) {
		this.jcAvailableNum = jcAvailableNum;
	}
	public Integer getJcTop() {
		return jcTop;
	}
	public void setJcTop(Integer jcTop) {
		this.jcTop = jcTop;
	}
	
	
}
