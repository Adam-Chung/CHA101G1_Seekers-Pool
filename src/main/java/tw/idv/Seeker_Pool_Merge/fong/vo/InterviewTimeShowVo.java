package tw.idv.Seeker_Pool_Merge.fong.vo;

import java.util.List;

public class InterviewTimeShowVo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String dateTime;
	private CompanyMemberShowVo company;
	private Integer jobId;
	private MemberVo member;
	private String checkTimeKey;
	private List<String> CheckTimeKeys;
	
	
	
	@Override
	public String toString() {
		return "InterviewTimeVo [dateTime=" + dateTime + ", company=" + company + ", jobId=" + jobId + ", member="
				+ member + ", checkTimeKey=" + checkTimeKey + ", CheckTimeKeys=" + CheckTimeKeys + "]";
	}
	
	public String getDateTime() {
		return dateTime;
	}
	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}
	public CompanyMemberShowVo getCompany() {
		return company;
	}
	public void setCompany(CompanyMemberShowVo company) {
		this.company = company;
	}
	public Integer getJobId() {
		return jobId;
	}
	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}
	public MemberVo getMember() {
		return member;
	}
	public void setMember(MemberVo member) {
		this.member = member;
	}
	public String getCheckTimeKey() {
		return checkTimeKey;
	}
	public void setCheckTimeKey(String checkTimeKey) {
		this.checkTimeKey = checkTimeKey;
	}
	public List<String> getCheckTimeKeys() {
		return CheckTimeKeys;
	}
	public void setCheckTimeKeys(List<String> checkTimeKeys) {
		CheckTimeKeys = checkTimeKeys;
	}
	
	
	
	
}