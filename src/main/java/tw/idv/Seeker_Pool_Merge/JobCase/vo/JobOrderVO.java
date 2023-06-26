package tw.idv.Seeker_Pool_Merge.JobCase.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="job_order")
public class JobOrderVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// 訂單編號
	@Column(name="JO_NO")
	private Integer joNo;
	// 企業編號
	@Column(name="COM_MEM_ID",insertable=false)
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
}
