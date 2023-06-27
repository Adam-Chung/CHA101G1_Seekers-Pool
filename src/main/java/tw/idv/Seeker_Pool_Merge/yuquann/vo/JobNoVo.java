package tw.idv.Seeker_Pool_Merge.yuquann.vo;

public class JobNoVo {

	private int jobNo;
	private int comMemId;
	private int ptNo;
	private int secNo;
	private int joNo;
	private String jobName;
	private String jobContent;
	private int jobSalary;
	private int jobType;
	private String jobAddress;
	private String jobOther;
	private int jobStatus;
	private boolean jobUpload;
	private boolean jobTop;
	
	public JobNoVo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JobNoVo(int jobNo, int comMemId, int ptNo, int secNo, int joNo, String jobName, String jobContent,
			int jobSalary, int jobType, String jobAddress, String jobOther, int jobStatus, boolean jobUpload,
			boolean jobTop) {
		super();
		this.jobNo = jobNo;
		this.comMemId = comMemId;
		this.ptNo = ptNo;
		this.secNo = secNo;
		this.joNo = joNo;
		this.jobName = jobName;
		this.jobContent = jobContent;
		this.jobSalary = jobSalary;
		this.jobType = jobType;
		this.jobAddress = jobAddress;
		this.jobOther = jobOther;
		this.jobStatus = jobStatus;
		this.jobUpload = jobUpload;
		this.jobTop = jobTop;
	}

	public int getJobNo() {
		return jobNo;
	}

	public void setJobNo(int jobNo) {
		this.jobNo = jobNo;
	}

	public int getComMemId() {
		return comMemId;
	}

	public void setComMemId(int comMemId) {
		this.comMemId = comMemId;
	}

	public int getPtNo() {
		return ptNo;
	}

	public void setPtNo(int ptNo) {
		this.ptNo = ptNo;
	}

	public int getSecNo() {
		return secNo;
	}

	public void setSecNo(int secNo) {
		this.secNo = secNo;
	}

	public int getJoNo() {
		return joNo;
	}

	public void setJoNo(int joNo) {
		this.joNo = joNo;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobContent() {
		return jobContent;
	}

	public void setJobContent(String jobContent) {
		this.jobContent = jobContent;
	}

	public int getJobSalary() {
		return jobSalary;
	}

	public void setJobSalary(int jobSalary) {
		this.jobSalary = jobSalary;
	}

	public int getJobType() {
		return jobType;
	}

	public void setJobType(int jobType) {
		this.jobType = jobType;
	}

	public String getJobAddress() {
		return jobAddress;
	}

	public void setJobAddress(String jobAddress) {
		this.jobAddress = jobAddress;
	}

	public String getJobOther() {
		return jobOther;
	}

	public void setJobOther(String jobOther) {
		this.jobOther = jobOther;
	}

	public int getJobStatus() {
		return jobStatus;
	}

	public void setJobStatus(int jobStatus) {
		this.jobStatus = jobStatus;
	}

	public boolean isJobUpload() {
		return jobUpload;
	}

	public void setJobUpload(boolean jobUpload) {
		this.jobUpload = jobUpload;
	}

	public boolean isJobTop() {
		return jobTop;
	}

	public void setJobTop(boolean jobTop) {
		this.jobTop = jobTop;
	}

	@Override
	public String toString() {
		return "JobNoVo [jobNo=" + jobNo + ", comMemId=" + comMemId + ", ptNo=" + ptNo + ", secNo=" + secNo + ", joNo="
				+ joNo + ", jobName=" + jobName + ", jobContent=" + jobContent + ", jobSalary=" + jobSalary
				+ ", jobType=" + jobType + ", jobAddress=" + jobAddress + ", jobOther=" + jobOther + ", jobStatus="
				+ jobStatus + ", jobUpload=" + jobUpload + ", jobTop=" + jobTop + "]";
	}
	
}

