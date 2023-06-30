package tw.idv.Seeker_Pool_Merge.JobCase.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Data
@Entity
@Table(name="job_case")
public class JobCaseVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="JC_NO")
	private Integer jcNo;
	@Column(name="JC_Name")
	private String jcName;
	@Column(name="JOB_AVAILABLE_NUM")
	private Integer jcAvailableNum;
	@Column(name="JC_EXP_TIME")
	private Integer jcExpTime;
	@Column(name="JC_TOP")
	private Integer jcTop;
	@Column(name="JC_PRICE")
	private Integer jcPrice;
	@Column(name="JC_STATUS")
	private Integer jcStatus;
	
	
	
	
	@Override
	public String toString() {
		return "JobCaseVO [jcNo=" + jcNo + ", jcName=" + jcName + ", jcAvailableNum=" + jcAvailableNum + ", jcExpTime="
				+ jcExpTime + ", jcTop=" + jcTop + ", jcPrice=" + jcPrice + ", jcStatus=" + jcStatus + "]";
	}
	public Integer getJcNo() {
		return jcNo;
	}
	public void setJcNo(Integer jcNo) {
		this.jcNo = jcNo;
	}
	public String getJcName() {
		return jcName;
	}
	public void setJcName(String jcName) {
		this.jcName = jcName;
	}
	public Integer getJcAvailableNum() {
		return jcAvailableNum;
	}
	public void setJcAvailableNum(Integer jcAvailableNum) {
		this.jcAvailableNum = jcAvailableNum;
	}
	public Integer getJcExpTime() {
		return jcExpTime;
	}
	public void setJcExpTime(Integer jcExpTime) {
		this.jcExpTime = jcExpTime;
	}
	public Integer getJcTop() {
		return jcTop;
	}
	public void setJcTop(Integer jcTop) {
		this.jcTop = jcTop;
	}
	public Integer getJcPrice() {
		return jcPrice;
	}
	public void setJcPrice(Integer jcPrice) {
		this.jcPrice = jcPrice;
	}
	public Integer getJcStatus() {
		return jcStatus;
	}
	public void setJcStatus(Integer jcStatus) {
		this.jcStatus = jcStatus;
	}
	

}
