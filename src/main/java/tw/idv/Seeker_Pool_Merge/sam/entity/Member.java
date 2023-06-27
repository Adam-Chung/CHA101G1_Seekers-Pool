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
}
