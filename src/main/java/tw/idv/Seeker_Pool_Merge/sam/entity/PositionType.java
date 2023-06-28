package tw.idv.Seeker_Pool_Merge.sam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ptNo;
    private String ptType;
    private String ptName;
    private Integer ptSearchTimes;

    public Integer getPtNo() {
        return ptNo;
    }
    public void setPtNo(Integer ptNo) {
        this.ptNo = ptNo;
    }
    public String getPtType() {
        return ptType;
    }
    public void setPtType(String ptType) {
        this.ptType = ptType;
    }
    public String getPtName() {
        return ptName;
    }
    public void setPtName(String ptName) {
        this.ptName = ptName;
    }
    public Integer getPtSearchTimes() {
        return ptSearchTimes;
    }
    public void setPtSearchTimes(Integer ptSearchTimes) {
        this.ptSearchTimes = ptSearchTimes;
    }
}
