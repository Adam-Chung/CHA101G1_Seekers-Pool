package tw.idv.Seeker_Pool_Merge.JobCase.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

//@Data
@Entity
@Table(name="job")
public class JobVO implements java.io.Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 職缺編號
	@Column(name="JOB_NO")
	private Integer jobNo;
	// 企業編號
	@Column(name="COM_MEM_ID")
	private Integer comMemId;
	// 職務編號
	@Column(name="PT_NO")
	private Integer ptNo;
	// 訂單編號
	@Column(name="JO_NO")
	private Integer joNo;
	// 職缺名稱
	@Column(name="JOB_NAME")
	private String jobName;
	// 職缺狀態
	@Column(name="JOB_STATUS")
	private Integer jobStatus;
	// 是否為上架職缺
	@Column(name="JOB_UPLOAD")
	private Integer jobUpload;
	// 是否為置頂職缺
	@Column(name="JOB_TOP")
	private Integer jobTop;
	public Integer getJobNo() {
		return jobNo;
	}
	public void setJobNo(Integer jobNo) {
		this.jobNo = jobNo;
	}
	public Integer getComMemId() {
		return comMemId;
	}
	public void setComMemId(Integer comMemId) {
		this.comMemId = comMemId;
	}
	public Integer getPtNo() {
		return ptNo;
	}
	public void setPtNo(Integer ptNo) {
		this.ptNo = ptNo;
	}
	public Integer getJoNo() {
		return joNo;
	}
	public void setJoNo(Integer joNo) {
		this.joNo = joNo;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Integer getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(Integer jobStatus) {
		this.jobStatus = jobStatus;
	}
	public Integer getJobUpload() {
		return jobUpload;
	}
	public void setJobUpload(Integer jobUpload) {
		this.jobUpload = jobUpload;
	}
	public Integer getJobTop() {
		return jobTop;
	}
	public void setJobTop(Integer jobTop) {
		this.jobTop = jobTop;
	}

}
