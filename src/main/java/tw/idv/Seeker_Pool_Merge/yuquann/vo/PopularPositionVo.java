package tw.idv.Seeker_Pool_Merge.yuquann.vo;

import java.sql.Date;

public class PopularPositionVo {

	private int ptNo;
	private Date ppTimeRange;
	private int ppSearchTimes;
	
	public PopularPositionVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "PopularPositionVo [ptNo=" + ptNo + ", ppTimeRange=" + ppTimeRange + ", ppSearchTimes=" + ppSearchTimes
				+ "]";
	}

	public int getPtNo() {
		return ptNo;
	}

	public void setPtNo(int ptNo) {
		this.ptNo = ptNo;
	}

	public Date getPpTimeRange() {
		return ppTimeRange;
	}

	public void setPpTimeRange(Date ppTimeRange) {
		this.ppTimeRange = ppTimeRange;
	}

	public int getPpSearchTimes() {
		return ppSearchTimes;
	}

	public void setPpSearchTimes(int ppSearchTimes) {
		this.ppSearchTimes = ppSearchTimes;
	}

	public PopularPositionVo(int ptNo, Date ppTimeRange, int ppSearchTimes) {
		super();
		this.ptNo = ptNo;
		this.ppTimeRange = ppTimeRange;
		this.ppSearchTimes = ppSearchTimes;
	}
	
	
}
