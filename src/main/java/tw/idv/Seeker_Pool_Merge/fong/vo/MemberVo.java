package tw.idv.Seeker_Pool_Merge.fong.vo;


public class MemberVo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer memId;
	private String memAddress; //欲工作區域
	private String memName;
	private String memGender;
	private String memPic;
	private String memEmail;
	private String memMobile;
	private String memCollege;
	private String memDepartment;
	private String memLang;
	private String memBio;
	private String skNo;
	private String memAccount;
	private String memPassword;
	private Integer nlSub;
	private Integer memStatus;
	private Integer cvStatus;
	
	@Override
	public String toString() {
		return "MemberVo [memId=" + memId + ", memAddress=" + memAddress + ", memName=" + memName + ", memGender="
				+ memGender + ", memPic=" + memPic + ", memEmail=" + memEmail + ", memMobile=" + memMobile
				+ ", memCollege=" + memCollege + ", memDepartment=" + memDepartment + ", memLang=" + memLang
				+ ", memBio=" + memBio + ", skNo=" + skNo + ", memAccount=" + memAccount + ", memPassword="
				+ memPassword + ", nlSub=" + nlSub + ", memStatus=" + memStatus + ", cvStatus=" + cvStatus + "]";
	}
	
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public String getMemAddress() {
		return memAddress;
	}
	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemGender() {
		return memGender;
	}
	public void setMemGender(String memGender) {
		this.memGender = memGender;
	}
	public String getMemPic() {
		return memPic;
	}
	public void setMemPic(String memPic) {
		this.memPic = memPic;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemMobile() {
		return memMobile;
	}
	public void setMemMobile(String memMobile) {
		this.memMobile = memMobile;
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
	public String getMemLang() {
		return memLang;
	}
	public void setMemLang(String memLang) {
		this.memLang = memLang;
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
	public String getMemAccount() {
		return memAccount;
	}
	public void setMemAccount(String memAccount) {
		this.memAccount = memAccount;
	}
	public String getMemPassword() {
		return memPassword;
	}
	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}
	public Integer getNlSub() {
		return nlSub;
	}
	public void setNlSub(Integer nlSub) {
		this.nlSub = nlSub;
	}
	public Integer getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(Integer memStatus) {
		this.memStatus = memStatus;
	}
	public Integer getCvStatus() {
		return cvStatus;
	}
	public void setCvStatus(Integer cvStatus) {
		this.cvStatus = cvStatus;
	}
}
