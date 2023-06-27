package tw.idv.Seeker_Pool_Merge.sam.entity;


// 創建Result是為了回傳給前端統一是JSON格式(統一格式 方便閱讀)

import lombok.*;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Component
public class Result {
    private Integer code; // 1 成功, 0 失敗
    private String msg; // 提示訊息
    private Object data; // 真正要回傳的資料

    public static Result success(Object data){
        return new Result(1, "success", data);
    }
    public static Result success(){
        return new Result(1, "success", null);
    }
    public static Result error(String msg){
        return new Result(0, msg, null);
    }

}
