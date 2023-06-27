package tw.idv.Seeker_Pool_Merge.sam.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tb_dept {
  private Integer id;
  private String name; //系所名稱
  private LocalDateTime createTime;
  private LocalDateTime updateTime;
}
