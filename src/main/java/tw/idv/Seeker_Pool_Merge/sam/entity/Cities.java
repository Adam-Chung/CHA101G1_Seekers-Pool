package tw.idv.Seeker_Pool_Merge.sam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cities")
public class Cities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; //auto_increment
    private String cityName; //city name



}
