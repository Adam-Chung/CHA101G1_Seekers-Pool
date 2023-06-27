package tw.idv.Seeker_Pool_Merge.song.vo;

import java.sql.Date;

public class JobRecruitmentArticleVo {
	private Integer jrArNo;
	private Integer admId;
	private String jrArTitle;
	private String jrArContent;
	private String jrArImg;
	private String jrArCom;
	private Integer jrArStatus;
	private Date jrPubTime;
	private Date jrArStartTime;
	private Date jrArEndTime;
	public String getJrArCom() {
		return jrArCom;
	}
	public void setJrArCom(String jrArCom) {
		this.jrArCom = jrArCom;
	}
	public Integer getJrArStatus() {
		return jrArStatus;
	}
	public void setJrArStatus(Integer jrArStatus) {
		this.jrArStatus = jrArStatus;
	}
	public Date getJrArStartTime() {
		return jrArStartTime;
	}
	public void setJrArStartTime(Date jrArStartTime) {
		this.jrArStartTime = jrArStartTime;
	}
	public Date getJrArEndTime() {
		return jrArEndTime;
	}
	public void setJrArEndTime(Date jrArEndTime) {
		this.jrArEndTime = jrArEndTime;
	}
	public Integer getJrArNo() {
		return jrArNo;
	}
	public void setJrArNo(Integer jrArNo) {
		this.jrArNo = jrArNo;
	}

	public Integer getAdmId() {
		return admId;
	}
	public void setAdmId(Integer admId) {
		this.admId = admId;
	}
	public String getJrArTitle() {
		return jrArTitle;
	}
	public void setJrArTitle(String jrArTitle) {
		this.jrArTitle = jrArTitle;
	}
	public String getJrArContent() {
		return jrArContent;
	}
	public void setJrArContent(String jrArContent) {
		this.jrArContent = jrArContent;
	}
	public String getJrArImg() {
		return jrArImg;
	}
	public void setJrArImg(String jrArImg) {
		this.jrArImg = jrArImg;
	}
	public Date getJrPubTime() {
		return jrPubTime;
	}
	public void setJrPubTime(Date jrPubTime) {
		this.jrPubTime = jrPubTime;
	}

}
