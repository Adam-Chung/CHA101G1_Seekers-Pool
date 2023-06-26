package tw.idv.Seeker_Pool_Merge.fong.vo;

public class CompanyMemberShowVo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer comMemId;
	private String comName;
	
	
	
	@Override
	public String toString() {
		return "CompanyMemberVo [comMemId=" + comMemId + ", comName=" + comName + "]";
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
	


}
