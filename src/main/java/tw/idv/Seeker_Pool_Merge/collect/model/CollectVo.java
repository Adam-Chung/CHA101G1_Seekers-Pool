package tw.idv.Seeker_Pool_Merge.collect.model;

import java.sql.Date;

public class CollectVo implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer colArNo;
	private Integer memId;
	private Integer arNo;
	private Date colTime;
	
	
	@Override
	public String toString() {
		return "CollectVo [colArNo=" + colArNo + ", memId=" + memId + ", arNo=" + arNo + ", colTime=" + colTime + "]";
	}
	
	
	public Integer getColArNo() {
		return colArNo;
	}
	public void setColArNo(Integer colArNo) {
		this.colArNo = colArNo;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Integer getArNo() {
		return arNo;
	}
	public void setArNo(Integer arNo) {
		this.arNo = arNo;
	}
	public Date getColTime() {
		return colTime;
	}
	public void setColTime(Date colTime) {
		this.colTime = colTime;
	}

	
	
	
	
	
	
	


}
