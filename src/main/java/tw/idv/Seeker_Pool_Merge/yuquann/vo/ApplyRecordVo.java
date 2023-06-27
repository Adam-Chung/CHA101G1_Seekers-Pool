package tw.idv.Seeker_Pool_Merge.yuquann.vo;

import java.sql.Date;

public class ApplyRecordVo {

	private int applyRecNo;
	private int comMemId;
	private int memId;
	private int jobNo;
	private Date applyDate;
	private String interDate;
	private int hireStatus;
	
	public ApplyRecordVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ApplyRecordVo(int applyRecNo, int comMemId, int memId, int jobNo, Date applyDate, String interDate,
			int hireStatus) {
		super();
		this.applyRecNo = applyRecNo;
		this.comMemId = comMemId;
		this.memId = memId;
		this.jobNo = jobNo;
		this.applyDate = applyDate;
		this.interDate = interDate;
		this.hireStatus = hireStatus;
	}

	public int getApplyRecNo() {
		return applyRecNo;
	}

	public void setApplyRecNo(int applyRecNo) {
		this.applyRecNo = applyRecNo;
	}

	public int getComMemId() {
		return comMemId;
	}

	public void setComMemId(int comMemId) {
		this.comMemId = comMemId;
	}

	public int getMemId() {
		return memId;
	}

	public void setMemId(int memId) {
		this.memId = memId;
	}

	public int getJobNo() {
		return jobNo;
	}

	public void setJobNo(int jobNo) {
		this.jobNo = jobNo;
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

	public int getHireStatus() {
		return hireStatus;
	}

	public void setHireStatus(int hireStatus) {
		this.hireStatus = hireStatus;
	}

	@Override
	public String toString() {
		return "ApplyRecordVo [applyRecNo=" + applyRecNo + ", comMemId=" + comMemId + ", memId=" + memId + ", jobNo="
				+ jobNo + ", applyDate=" + applyDate + ", interDate=" + interDate + ", hireStatus=" + hireStatus + "]";
	}
	
	
	
	
	
	
}
