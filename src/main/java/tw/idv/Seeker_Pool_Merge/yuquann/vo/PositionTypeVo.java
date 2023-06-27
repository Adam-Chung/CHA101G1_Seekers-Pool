package tw.idv.Seeker_Pool_Merge.yuquann.vo;

public class PositionTypeVo {

	private int ptNO ;
	private String ptType ;
	private String ptName ;
	private int ptSearchTimes ;
	
	
	public PositionTypeVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public PositionTypeVo(int ptNO, String ptType, String ptName, int ptSearchTimes) {
		super();
		this.ptNO = ptNO;
		this.ptType = ptType;
		this.ptName = ptName;
		this.ptSearchTimes = ptSearchTimes;
	}




	public int getPtNO() {
		return ptNO;
	}


	public void setPtNO(int ptNO) {
		this.ptNO = ptNO;
	}

	public String getPtType() {
		return ptType;
	}


	public void setPtType(String ptType) {
		this.ptType = ptType;
	}


	public String getPtName() {
		return ptName;
	}


	public void setPtName(String ptName) {
		this.ptName = ptName;
	}


	public int getPtSearchTimes() {
		return ptSearchTimes;
	}


	public void setPtSearchTimes(int ptSearchTimes) {
		this.ptSearchTimes = ptSearchTimes;
	}


	@Override
	public String toString() {
		return "PositionTypeVo [ptType=" + ptType + ", ptName=" + ptName + ", ptSearchTimes=" + ptSearchTimes + "]";
	}
	
	
	
}
