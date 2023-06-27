package tw.idv.Seeker_Pool_Merge.yuquann.vo;

import java.sql.Date;

public class ReportEnterpriseVo {

	private int reNo;
	private int rjtNo;
	private int memId;
	private int comMemId;
	private int jobNo;
	private Date reStartTime;
	private String reContent;
	private Date reEndTime;
	private int reStatus;
	private int reResult;
	private String reNote;
	private String reUpload;
	private String memAccount;
	private String comName;

	public ReportEnterpriseVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReportEnterpriseVo(int reNo, int reStatus, int reResult) {
		super();
		this.reNo = reNo;
		this.reStatus = reStatus;
		this.reResult = reResult;
	}

	public ReportEnterpriseVo(int rjtNo, String reContent, Date reEndTime, int reStatus, int reResult) {
		super();
		this.rjtNo = rjtNo;
		this.reContent = reContent;
		this.reEndTime = reEndTime;
		this.reStatus = reStatus;
		this.reResult = reResult;
	}

	public ReportEnterpriseVo(int rjtNo, String reContent, Date reEndTime, int reStatus, int reResult,
			String reUpload) {
		super();
		this.rjtNo = rjtNo;
		this.reContent = reContent;
		this.reEndTime = reEndTime;
		this.reStatus = reStatus;
		this.reResult = reResult;
		this.reUpload = reUpload;
	}

	

	public ReportEnterpriseVo(int reNo, int rjtNo, int memId, int comMemId, int jobNo, Date reStartTime,
			String reContent, Date reEndTime, int reStatus, int reResult, String reNote, String reUpload,
			String memAccount, String comName) {
		super();
		this.reNo = reNo;
		this.rjtNo = rjtNo;
		this.memId = memId;
		this.comMemId = comMemId;
		this.jobNo = jobNo;
		this.reStartTime = reStartTime;
		this.reContent = reContent;
		this.reEndTime = reEndTime;
		this.reStatus = reStatus;
		this.reResult = reResult;
		this.reNote = reNote;
		this.reUpload = reUpload;
		this.memAccount = memAccount;
		this.comName = comName;
	}

	public int getReNo() {
		return reNo;
	}

	public void setReNo(int reNo) {
		this.reNo = reNo;
	}

	public int getRjtNo() {
		return rjtNo;
	}

	public void setRjtNo(int rjtNo) {
		this.rjtNo = rjtNo;
	}

	public int getMemId() {
		return memId;
	}

	public void setMemId(int memId) {
		this.memId = memId;
	}

	public int getComMemId() {
		return comMemId;
	}

	public void setComMemId(int comMemId) {
		this.comMemId = comMemId;
	}

	public int getJobNo() {
		return jobNo;
	}

	public void setJobNo(int jobNo) {
		this.jobNo = jobNo;
	}

	public Date getReStartTime() {
		return reStartTime;
	}

	public void setReStartTime(Date reStartTime) {
		this.reStartTime = reStartTime;
	}

	public String getReContent() {
		return reContent;
	}

	public void setReContent(String reContent) {
		this.reContent = reContent;
	}

	public Date getReEndTime() {
		return reEndTime;
	}

	public void setReEndTime(Date reEndTime) {
		this.reEndTime = reEndTime;
	}

	public int getReStatus() {
		return reStatus;
	}

	public void setReStatus(int reStatus) {
		this.reStatus = reStatus;
	}

	public int getReResult() {
		return reResult;
	}

	public void setReResult(int reResult) {
		this.reResult = reResult;
	}

	public String getReNote() {
		return reNote;
	}

	public void setReNote(String reNote) {
		this.reNote = reNote;
	}

	public String getReUpload() {
		return reUpload;
	}

	public void setReUpload(String reUpload) {
		this.reUpload = reUpload;
	}
	
	

	public String getMemAccount() {
		return memAccount;
	}

	public void setMemAccount(String memAccount) {
		this.memAccount = memAccount;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}

	@Override
	public String toString() {
		return "ReportEnterpriseVo [reNo=" + reNo + ", rjtNo=" + rjtNo + ", memId=" + memId + ", comMemId=" + comMemId
				+ ", jobNo=" + jobNo + ", reStartTime=" + reStartTime + ", reContent=" + reContent + ", reEndTime="
				+ reEndTime + ", reStatus=" + reStatus + ", reResult=" + reResult + ", reNote=" + reNote + ", reUpload="
				+ reUpload + ", memAccount=" + memAccount + ", comName=" + comName + "]";
	}

	

}
