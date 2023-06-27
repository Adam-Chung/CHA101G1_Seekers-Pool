package tw.idv.Seeker_Pool_Merge.jamie.vo;

import java.sql.Date;

public class ApplyRecordVo {
	
	private Integer memId;
	private String memName;
	private String memPic;
	private String memCollege;
	private String memDepartment;
	private String memAddress;  // 欲工作地點
	private String memEmail;
	private String memBio;
	private String skNo;
	private String memLang;
	private Integer jobNo;
	private String jobName;
	private Date applyDate;
	private String interDate;
	private Integer hireStatus;
	
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemPic() {
		return memPic;
	}
	public void setMemPic(String memPic) {
		this.memPic = memPic;
	}
	public String getMemCollege() {
		return memCollege;
	}
	public void setMemCollege(String memCollege) {
		this.memCollege = memCollege;
	}
	public String getMemDepartment() {
		return memDepartment;
	}
	public void setMemDepartment(String memDepartment) {
		this.memDepartment = memDepartment;
	}
	public String getMemAddress() {
		return memAddress;
	}
	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemBio() {
		return memBio;
	}
	public void setMemBio(String memBio) {
		this.memBio = memBio;
	}
	public String getSkNo() {
		return skNo;
	}
	public void setSkNo(String skNo) {
		this.skNo = skNo;
	}
	public String getMemLang() {
		return memLang;
	}
	public void setMemLang(String memLang) {
		this.memLang = memLang;
	}
	public Integer getJobNo() {
		return jobNo;
	}
	public void setJobNo(Integer jobNo) {
		this.jobNo = jobNo;
	}
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public Date getApplyDate() {
		return applyDate;
	}
	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}
	public String getInterDate() {
		return interDate;
	}
	public void setInterDate(String interDate) {
		this.interDate = interDate;
	}
	public Integer getHireStatus() {
		return hireStatus;
	}
	public void setHireStatus(Integer hireStatus) {
		this.hireStatus = hireStatus;
	}
	public String getHireStatusText() {
	    // 在管理頁將應徵者的狀態改成中文字顯示
	    switch (hireStatus) {
	        case 0:
	            return "已投履歷，未有面試";
	        case 1:
	            return "已安排面試";
	        case 2:
	            return "面試已完成，等待通知";
	        case 3:
	            return "企業發面試，待回覆";
	        case 4:
	            return "取消面試";
	        case 8:
	            return "未錄取";
	        case 9:
	            return "錄取成功";
	        default:
	            return "未知狀態"; // 這是為了應對可能的未知狀態，可根據實際情況修改
	    }
	}
	
	@Override
	public String toString() {
		return "ApplyRecordVo [memId=" + memId + ", memName=" + memName + ", memPic=" + memPic + ", memCollege="
				+ memCollege + ", memDepartment=" + memDepartment + ", memAddress=" + memAddress + ", memEmail="
				+ memEmail + ", memBio=" + memBio + ", skNo=" + skNo + ", memLang=" + memLang + ", jobNo=" + jobNo
				+ ", jobName=" + jobName + ", applyDate=" + applyDate + ", interDate=" + interDate + ", hireStatus="
				+ hireStatus + "]";
	}


}
