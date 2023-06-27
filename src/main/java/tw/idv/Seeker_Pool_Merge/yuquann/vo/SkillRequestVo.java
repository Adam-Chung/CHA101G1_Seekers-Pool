package tw.idv.Seeker_Pool_Merge.yuquann.vo;

public class SkillRequestVo {

	private int jobNo;
	private int skNo;
	
	public SkillRequestVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SkillRequestVo(int jobNo, int skNo) {
		super();
		this.jobNo = jobNo;
		this.skNo = skNo;
	}

	public int getJobNo() {
		return jobNo;
	}

	public void setJobNo(int jobNo) {
		this.jobNo = jobNo;
	}

	public int getSkNo() {
		return skNo;
	}

	public void setSkNo(int skNo) {
		this.skNo = skNo;
	}

	@Override
	public String toString() {
		return "SkillRequestVo [jobNo=" + jobNo + ", skNo=" + skNo + "]";
	}
	
}
