package tw.idv.Seeker_Pool_Merge.sam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer memId;
    private String memAddress;
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
}
