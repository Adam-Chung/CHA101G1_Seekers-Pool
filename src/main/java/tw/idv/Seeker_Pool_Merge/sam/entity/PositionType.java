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
}
