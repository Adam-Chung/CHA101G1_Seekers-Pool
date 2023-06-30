package tw.idv.Seeker_Pool_Merge.JobCase.vo;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

//@Data
@Entity
@Table(name="job_order")
public class JobOrderVO implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 訂單編號
	@Column(name="JO_NO")
	private Integer joNo;
	// 企業編號
	@Column(name="COM_MEM_ID")
	private Integer comMemId;
	// 職缺刊登方案編號
	@Column(name="JC_NO")
	private Integer jcNo;
	// 已上架數量
	@Column(name="JOB_PUBLISHED_NUM",insertable=false)
	private Integer jobPublishedNum;
	// 置頂已上架數量
	@Column(name="JOB_PUBLISHED_TOP_NUM",insertable=false)
	private Integer jobPublishedTopNum;
	// 方案截止日
	@Column(name="JC_DEADLINE")
	private Timestamp jcDeadline;
	public Integer getJoNo() {
		return joNo;
	}
	public void setJoNo(Integer joNo) {
		this.joNo = joNo;
	}
	public Integer getComMemId() {
		return comMemId;
	}
	public void setComMemId(Integer comMemId) {
		this.comMemId = comMemId;
	}
	public Integer getJcNo() {
		return jcNo;
	}
	public void setJcNo(Integer jcNo) {
		this.jcNo = jcNo;
	}
	public Integer getJobPublishedNum() {
		return jobPublishedNum;
	}
	public void setJobPublishedNum(Integer jobPublishedNum) {
		this.jobPublishedNum = jobPublishedNum;
	}
	public Integer getJobPublishedTopNum() {
		return jobPublishedTopNum;
	}
	public void setJobPublishedTopNum(Integer jobPublishedTopNum) {
		this.jobPublishedTopNum = jobPublishedTopNum;
	}
	public Timestamp getJcDeadline() {
		return jcDeadline;
	}
	public void setJcDeadline(Timestamp jcDeadline) {
		this.jcDeadline = jcDeadline;
	}
	
}
