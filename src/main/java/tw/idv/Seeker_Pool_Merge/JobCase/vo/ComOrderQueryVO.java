package tw.idv.Seeker_Pool_Merge.JobCase.vo;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;
//@Data
@Entity
public class ComOrderQueryVO {
	@Id
	private Integer joNo;
	private Integer jcNo;
    private String jcName;
    private Integer jcAvailableNum;
    private Integer jcExpTime;
    private Integer jcTop;
    private Integer jcPrice;
    private Integer jcStatus;
    private Timestamp jcDeadline;
    private Integer comMemId;
    private String comName;
    private Integer jobPublishedNum;
    private Integer jobPublishedTopNum;
    private Integer jobNo;
	private Integer jobUpload;
	private Integer jobTop;
	public Integer getJoNo() {
		return joNo;
	}
	public void setJoNo(Integer joNo) {
		this.joNo = joNo;
	}
	public Integer getJcNo() {
		return jcNo;
	}
	public void setJcNo(Integer jcNo) {
		this.jcNo = jcNo;
	}
	public String getJcName() {
		return jcName;
	}
	public void setJcName(String jcName) {
		this.jcName = jcName;
	}
	public Integer getJcAvailableNum() {
		return jcAvailableNum;
	}
	public void setJcAvailableNum(Integer jcAvailableNum) {
		this.jcAvailableNum = jcAvailableNum;
	}
	public Integer getJcExpTime() {
		return jcExpTime;
	}
	public void setJcExpTime(Integer jcExpTime) {
		this.jcExpTime = jcExpTime;
	}
	public Integer getJcTop() {
		return jcTop;
	}
	public void setJcTop(Integer jcTop) {
		this.jcTop = jcTop;
	}
	public Integer getJcPrice() {
		return jcPrice;
	}
	public void setJcPrice(Integer jcPrice) {
		this.jcPrice = jcPrice;
	}
	public Integer getJcStatus() {
		return jcStatus;
	}
	public void setJcStatus(Integer jcStatus) {
		this.jcStatus = jcStatus;
	}
	public Timestamp getJcDeadline() {
		return jcDeadline;
	}
	public void setJcDeadline(Timestamp jcDeadline) {
		this.jcDeadline = jcDeadline;
	}
	public Integer getComMemId() {
		return comMemId;
	}
	public void setComMemId(Integer comMemId) {
		this.comMemId = comMemId;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public Integer getJobPublishedNum() {
		return jobPublishedNum;
	}
	public void setJobPublishedNum(Integer jobPublishedNum) {
		this.jobPublishedNum = jobPublishedNum;
	}
	public Integer getJobPublishedTopNum() {
		return jobPublishedTopNum;
	}
	public void setJobPublishedTopNum(Integer jobPublishedTopNum) {
		this.jobPublishedTopNum = jobPublishedTopNum;
	}
	public Integer getJobNo() {
		return jobNo;
	}
	public void setJobNo(Integer jobNo) {
		this.jobNo = jobNo;
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
	
	
}
