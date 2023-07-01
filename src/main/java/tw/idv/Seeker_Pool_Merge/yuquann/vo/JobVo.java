package tw.idv.Seeker_Pool_Merge.yuquann.vo;

public class JobVo {

	private int jobNo;
	private int comMemId;
	private int ptNo;
	private String cityName;
	private String districtName;
	private int joNo;
	private String jobName;
	private String jobContent;
	private int jobSalary;
	private int jobType;
	private String jobAddress;
	private String jobOther;
	private int jobStatus;
	private int jobUpload;
	private int jobTop;
	private String comName;
	private String comPicture;

	public JobVo() {
		super();
		// TODO Auto-generated constructor stub
	}


	public JobVo(int jobNo, int comMemId, int ptNo, String cityName, String districtName, int joNo, String jobName,
			String jobContent, int jobSalary, int jobType, String jobAddress, String jobOther, int jobStatus,
			int jobUpload, int jobTop, String comName, String comPicture) {
		super();
		this.jobNo = jobNo;
		this.comMemId = comMemId;
		this.ptNo = ptNo;
		this.cityName = cityName;
		this.districtName = districtName;
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
		this.comName = comName;
		this.comPicture = comPicture;
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

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
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

	

	public int getJobUpload() {
		return jobUpload;
	}

	public void setJobUpload(int jobUpload) {
		this.jobUpload = jobUpload;
	}

	public int getJobTop() {
		return jobTop;
	}

	public void setJobTop(int jobTop) {
		this.jobTop = jobTop;
	}

	public String getComName() {
		return comName;
	}

	public void setComName(String comName) {
		this.comName = comName;
	}
	
	

	public String getComPicture() {
		return comPicture;
	}


	public void setComPicture(String comPicture) {
		this.comPicture = comPicture;
	}


	@Override
	public String toString() {
		return "JobVo [jobNo=" + jobNo + ", comMemId=" + comMemId + ", ptNo=" + ptNo + ", cityName=" + cityName
				+ ", districtName=" + districtName + ", joNo=" + joNo + ", jobName=" + jobName + ", jobContent="
				+ jobContent + ", jobSalary=" + jobSalary + ", jobType=" + jobType + ", jobAddress=" + jobAddress
				+ ", jobOther=" + jobOther + ", jobStatus=" + jobStatus + ", jobUpload=" + jobUpload + ", jobTop="
				+ jobTop + ", comName=" + comName + ", comPicture=" + comPicture + "]";
	}


	

}
