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
@Table(name="job_case")
public class JobCaseVO {
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
	
}
