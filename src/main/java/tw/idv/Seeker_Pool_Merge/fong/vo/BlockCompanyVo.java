package tw.idv.Seeker_Pool_Merge.fong.vo;

public class BlockCompanyVo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer blockComNo;
	private Integer memId;
	private Integer comMemId;
	
	
	
	@Override
	public String toString() {
		return "BlockCompanyVo [blockComNo=" + blockComNo + ", memId=" + memId + ", comMemId=" + comMemId + "]";
	}
	public Integer getBlockComNo() {
		return blockComNo;
	}
	public void setBlockComNo(Integer blockComNo) {
		this.blockComNo = blockComNo;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getComMemId() {
		return comMemId;
	}
	public void setComMemId(Integer comMemId) {
		this.comMemId = comMemId;
	}
	
	
}