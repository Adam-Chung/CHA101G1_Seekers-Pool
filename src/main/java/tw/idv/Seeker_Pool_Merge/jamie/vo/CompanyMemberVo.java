package tw.idv.Seeker_Pool_Merge.jamie.vo;

import java.sql.Timestamp;
import java.time.LocalDate;

public class CompanyMemberVo {

	private Integer comMemId;
	private String comMemAccount;
	private String comMemPassword;
	private String comName;
	private String taxNum;
	private String comEmail;
	private String comTel;
	private String comAddress;
	private LocalDate comRegDate;
	private String comPicture;
	private String comRate;
	private String dataUpload;
	private Integer comStatus;
	private Timestamp endSuspendedTime;
	private Integer comRepNum;
	private String formattedTime;
	private String comPictureUrl;
	
	public Integer getComMemId() {
		return comMemId;
	}
	public void setComMemId(Integer comMemId) {
		this.comMemId = comMemId;
	}
	public String getComMemAccount() {
		return comMemAccount;
	}
	public void setComMemAccount(String comMemAccount) {
		this.comMemAccount = comMemAccount;
	}
	public String getComMemPassword() {
		return comMemPassword;
	}
	public void setComMemPassword(String comMemPassword) {
		this.comMemPassword = comMemPassword;
	}
	public String getComName() {
		return comName;
	}
	public void setComName(String comName) {
		this.comName = comName;
	}
	public String getTaxNum() {
		return taxNum;
	}
	public void setTaxNum(String taxNum) {
		this.taxNum = taxNum;
	}
	public String getComEmail() {
		return comEmail;
	}
	public void setComEmail(String comEmail) {
		this.comEmail = comEmail;
	}
	public String getComTel() {
		return comTel;
	}
	public void setComTel(String comTel) {
		this.comTel = comTel;
	}
	public String getComAddress() {
		return comAddress;
	}
	public void setComAddress(String comAddress) {
		this.comAddress = comAddress;
	}
	public LocalDate getComRegDate() {
		return comRegDate;
	}
	public void setComRegDate(LocalDate comRegDate) {
		this.comRegDate = comRegDate;
	}
	public String getComPicture() {
		return comPicture;
	}
	public void setComPicture(String comPicture) {
		this.comPicture = comPicture;
	}
	public String getComRate() {
		return comRate;
	}
	public void setComRate(String comRate) {
		this.comRate = comRate;
	}
	public String getDataUpload() {
		return dataUpload;
	}
	public void setDataUpload(String dataUpload) {
		this.dataUpload = dataUpload;
	}
	public Integer getComStatus() {
		return comStatus;
	}
	public void setComStatus(Integer comStatus) {
		this.comStatus = comStatus;
	}
	public Timestamp getEndSuspendedTime() {
		return endSuspendedTime;
	}
	public void setEndSuspendedTime(Timestamp endSuspendedTime) {
		this.endSuspendedTime = endSuspendedTime;
	}
	public Integer getComRepNum() {
		return comRepNum;
	}
	public void setComRepNum(Integer comRepNum) {
		this.comRepNum = comRepNum;
	}
	public String getFormattedTime() {
		return formattedTime;
	}
	public void setFormattedTime(String formattedTime) {
		this.formattedTime = formattedTime;
	}
    public String getComStatusText() {
    	// 在管理頁將企業帳號狀態改成中文字顯示
        switch (comStatus) {
            case 0:
                return "未驗證";
            case 1:
                return "已驗證";
            case 2:
                return "檢舉停權";
            default:
                return "未知狀態"; // 這是為了應對可能的未知狀態，可根據實際情況修改
        }
    }
    public String getComPictureUrl() {
        return comPictureUrl;
    }

    public void setComPictureUrl(String comPictureUrl) {
        this.comPictureUrl = comPictureUrl;
    }
	
	@Override
	public String toString() {
		return "CompanyMemberVO [comMemId=" + comMemId + ", comMemAccount=" + comMemAccount + ", comMemPassword="
				+ comMemPassword + ", comName=" + comName + ", taxNum=" + taxNum + ", comEmail=" + comEmail
				+ ", comTel=" + comTel + ", comAddress=" + comAddress + ", comRegDate=" + comRegDate + ", comPicture="
				+ comPicture + ", comRate=" + comRate + ", dataUpload=" + dataUpload + ", comStatus=" + comStatus + ", endSuspendedTime="
				+ endSuspendedTime + ", comRepNum=" + comRepNum + ", formattedTime=" + formattedTime
				+ ", comPictureUrl=" + comPictureUrl + "]";
	}
	
}
