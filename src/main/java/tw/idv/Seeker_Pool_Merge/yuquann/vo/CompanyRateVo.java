package tw.idv.Seeker_Pool_Merge.yuquann.vo;

public class CompanyRateVo {

	private int csNo;
	private int memId;
	private int comMemId;
	private int csScore;
	private String csContent;
	
	public CompanyRateVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompanyRateVo(int csNo, int memId, int comMemId, int csScore, String csContent) {
		super();
		this.csNo = csNo;
		this.memId = memId;
		this.comMemId = comMemId;
		this.csScore = csScore;
		this.csContent = csContent;
	}

	public int getCsNo() {
		return csNo;
	}

	public void setCsNo(int csNo) {
		this.csNo = csNo;
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

	public int getCsScore() {
		return csScore;
	}

	public void setCsScore(int csScore) {
		this.csScore = csScore;
	}

	public String getCsContent() {
		return csContent;
	}

	public void setCsContent(String csContent) {
		this.csContent = csContent;
	}

	@Override
	public String toString() {
		return "CompanyRateVo [csNo=" + csNo + ", memId=" + memId + ", comMemId=" + comMemId + ", csScore=" + csScore
				+ ", csContent=" + csContent + "]";
	}
	
	
	
}
