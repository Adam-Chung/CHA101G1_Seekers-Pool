package tw.idv.Seeker_Pool_Merge.song.vo;

import java.sql.Date;

public class JobFairVo {
	private Integer jfNo;
	private Integer admId;
	private String jfName;
	private Date jfStartTime;
	private Date jfEndTime;
	private Date regStartTime;
	private Date regEndTime;
	private String jfActivity;
	public Integer getJrStatus() {
		return jrStatus;
	}
	public void setJrStatus(Integer jrStatus) {
		this.jrStatus = jrStatus;
	}
	private String jrArImg;
	private Integer jrStatus;
	public String getJrArImg() {
		return jrArImg;
	}
	public void setJrArImg(String jrArImg) {
		this.jrArImg = jrArImg;
	}
	public Integer getJfNo() {
		return jfNo;
	}
	public void setJfNo(Integer jfNo) {
		this.jfNo = jfNo;
	}
	public Integer getAdmId() {
		return admId;
	}
	public void setAdmId(Integer admId) {
		this.admId = admId;
	}
	public String getJfName() {
		return jfName;
	}
	public void setJfName(String jfName) {
		this.jfName = jfName;
	}
	public Date getJfStartTime() {
		return jfStartTime;
	}
	public void setJfStartTime(Date jfStartTime) {
		this.jfStartTime = jfStartTime;
	}
	public Date getJfEndTime() {
		return jfEndTime;
	}
	public void setJfEndTime(Date jfEndTime) {
		this.jfEndTime = jfEndTime;
	}
	public Date getRegStartTime() {
		return regStartTime;
	}
	public void setRegStartTime(Date regStartTime) {
		this.regStartTime = regStartTime;
	}
	public Date getRegEndTime() {
		return regEndTime;
	}
	public void setRegEndTime(Date regEndTime) {
		this.regEndTime = regEndTime;
	}
	public String getJfActivity() {
		return jfActivity;
	}
	public void setJfActivity(String jfActivity) {
		this.jfActivity = jfActivity;
	}

}
