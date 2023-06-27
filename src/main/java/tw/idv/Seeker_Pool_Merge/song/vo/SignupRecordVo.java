package tw.idv.Seeker_Pool_Merge.song.vo;

import java.sql.Date;

public class SignupRecordVo {
	private Integer srNo;
	private Integer comMemId;
	private Integer admId;
	private Date srTime;
	
	public Date getSrTime() {
		return srTime;
	}
	public void setSrTime(Date srTime) {
		this.srTime = srTime;
	}
	public Integer getAdmId() {
		return admId;
	}
	public void setAdmId(Integer admId) {
		this.admId = admId;
	}
	private Integer jfNo;
	private String jfName;
	public String getJfActivity() {
		return jfActivity;
	}
	public void setJfActivity(String jfActivity) {
		this.jfActivity = jfActivity;
	}
	private String jrArImg;
	private String jfActivity;
	private Date regStartTime;
	private Date regEndTime;
	private Date jfStartTime;
	private Date jfEndTime;
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
	private Integer srStatus;
	private String comName;
	private String taxNum;
	private String comEmail;
	private String comTel;
	private String comAddress;
	public String getJfName() {
		return jfName;
	}
	public void setJfName(String jfName) {
		this.jfName = jfName;
	}
	public String getJrArImg() {
		return jrArImg;
	}
	public void setJrArImg(String jrArImg) {
		this.jrArImg = jrArImg;
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

	public String getComTel() {
		return comTel;
	}
	public void setComTel(String comTel) {
		this.comTel = comTel;
	}
	
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getTaxNum() {
		return taxNum;
	}
	public void setTaxNum(String taxNum) {
		this.taxNum = taxNum;
	}
	public String getComEmail() {
		return comEmail;
	}
	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}
	public String getComAddress() {
		return comAddress;
	}
	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}
	public Integer getSrNo() {
		return srNo;
	}
	public void setSrNo(Integer srNo) {
		this.srNo = srNo;
	}
	public Integer getComMemId() {
		return comMemId;
	}
	public void setComMemId(Integer comMemId) {
		this.comMemId = comMemId;
	}
	public Integer getJfNo() {
		return jfNo;
	}
	public void setJfNo(Integer jfNo) {
		this.jfNo = jfNo;
	}
	public Integer getSrStatus() {
		return srStatus;
	}
	public void setSrStatus(Integer srStatus) {
		this.srStatus = srStatus;
	}

}
