package tw.idv.Seeker_Pool_Merge.Administrator.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Data
@Entity
@Table(name = "administrator")
public class AdministratorVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 使用自增策略
	@Column(name = "adm_id")
	private Integer admId;
	@Column(name = "adm_name")
	private String admName;
	@Column(name = "adm_position")
	private Integer admPosition;
	@Column(name = "adm_account")
	private String admAccount;
	@Column(name = "adm_password")
	private String admPassword;
	@Column(name = "adm_status")
	private Integer admStatus;
	@Column(name="adm_photo")
	private String admPhoto;
	
	
	
	
	
	@Override
	public String toString() {
		return "AdministratorVO [admId=" + admId + ", admName=" + admName + ", admPosition=" + admPosition
				+ ", admAccount=" + admAccount + ", admPassword=" + admPassword + ", admStatus=" + admStatus
				+ ", admPhoto=" + admPhoto + "]";
	}
	public Integer getAdmId() {
		return admId;
	}
	public void setAdmId(Integer admId) {
		this.admId = admId;
	}
	public String getAdmName() {
		return admName;
	}
	public void setAdmName(String admName) {
		this.admName = admName;
	}
	public Integer getAdmPosition() {
		return admPosition;
	}
	public void setAdmPosition(Integer admPosition) {
		this.admPosition = admPosition;
	}
	public String getAdmAccount() {
		return admAccount;
	}
	public void setAdmAccount(String admAccount) {
		this.admAccount = admAccount;
	}
	public String getAdmPassword() {
		return admPassword;
	}
	public void setAdmPassword(String admPassword) {
		this.admPassword = admPassword;
	}
	public Integer getAdmStatus() {
		return admStatus;
	}
	public void setAdmStatus(Integer admStatus) {
		this.admStatus = admStatus;
	}
	public String getAdmPhoto() {
		return admPhoto;
	}
	public void setAdmPhoto(String admPhoto) {
		this.admPhoto = admPhoto;
	}
	
}
