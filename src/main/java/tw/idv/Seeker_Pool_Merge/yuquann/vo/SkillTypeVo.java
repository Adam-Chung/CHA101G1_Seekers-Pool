package tw.idv.Seeker_Pool_Merge.yuquann.vo;

public class SkillTypeVo {

	private int skNo;
	private String skType;
	private String skName;
	private int skSearchTimes;

	public SkillTypeVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SkillTypeVo(int skNo, String skType, String skName, int skSearchTimes) {
		super();
		this.skNo = skNo;
		this.skType = skType;
		this.skName = skName;
		this.skSearchTimes = skSearchTimes;
	}

	public int getSkNo() {
		return skNo;
	}

	public void setSkNo(int skNo) {
		this.skNo = skNo;
	}

	public String getSkType() {
		return skType;
	}

	public void setSkType(String skType) {
		this.skType = skType;
	}

	public String getSkName() {
		return skName;
	}

	public void setSkName(String skName) {
		this.skName = skName;
	}

	public int getSkSearchTimes() {
		return skSearchTimes;
	}

	public void setSkSearchTimes(int skSearchTimes) {
		this.skSearchTimes = skSearchTimes;
	}

	@Override
	public String toString() {
		return "SkillTypeVo [skNo=" + skNo + ", skType=" + skType + ", skName=" + skName + ", skSearchTimes="
				+ skSearchTimes + "]";
	}

}
