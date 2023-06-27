package tw.idv.Seeker_Pool_Merge.yuquann.vo;

import java.sql.Date;

public class CollectJobVo {

	private int memId;
	private	int jobNo;
	private Date collectDate;
	
	public CollectJobVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CollectJobVo(int memId, int jobNo, Date collectDate) {
		super();
		this.memId = memId;
		this.jobNo = jobNo;
		this.collectDate = collectDate;
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

	public Date getCollectDate() {
		return collectDate;
	}

	public void setCollectDate(Date collectDate) {
		this.collectDate = collectDate;
	}

	@Override
	public String toString() {
		return "CollectJob [memId=" + memId + ", jobNo=" + jobNo + ", collectDate=" + collectDate + "]";
	}
	
	
	
	
}
