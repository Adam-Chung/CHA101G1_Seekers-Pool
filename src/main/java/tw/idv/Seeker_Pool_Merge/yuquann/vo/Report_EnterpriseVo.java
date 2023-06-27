package tw.idv.Seeker_Pool_Merge.yuquann.vo;

import java.sql.Date;

import javax.servlet.http.Part;

public class Report_EnterpriseVo {

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
	private Part reUpload;
	
	public Report_EnterpriseVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Report_EnterpriseVo(int rjtNo, String reContent, Date reEndTime, int reStatus, int reResult) {
		super();
		this.rjtNo = rjtNo;
		this.reContent = reContent;
		this.reEndTime = reEndTime;
		this.reStatus = reStatus;
		this.reResult = reResult;
	}



	public Report_EnterpriseVo(int rjtNo, String reContent, Date reEndTime, int reStatus, int reResult, Part reUpload) {
		super();
		this.rjtNo = rjtNo;
		this.reContent = reContent;
		this.reEndTime = reEndTime;
		this.reStatus = reStatus;
		this.reResult = reResult;
		this.reUpload = reUpload;
	}



	public Report_EnterpriseVo(int reNo, int rjtNo, int memId, int comMemId, int jobNo, Date reStartTime,
			String reContent, Date reEndTime, int reStatus, int reResult, String reNote, Part reUpload) {
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

	public Part getReUpload() {
		return reUpload;
	}

	public void setReUpload(Part reUpload) {
		this.reUpload = reUpload;
	}

	@Override
	public String toString() {
		return "Report_EnterpriseVo [reNo=" + reNo + ", rjtNo=" + rjtNo + ", memId=" + memId + ", comMemId=" + comMemId
				+ ", jobNo=" + jobNo + ", reStartTime=" + reStartTime + ", reContent=" + reContent + ", reEndTime="
				+ reEndTime + ", reStatus=" + reStatus + ", reResult=" + reResult + ", reNote=" + reNote + ", reUpload="
				+ reUpload + "]";
	}
	
	
	
	
}
