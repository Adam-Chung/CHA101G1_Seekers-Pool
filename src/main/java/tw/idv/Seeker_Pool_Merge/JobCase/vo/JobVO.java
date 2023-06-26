package tw.idv.Seeker_Pool_Merge.JobCase.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="job")
public class JobVO {
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

}
