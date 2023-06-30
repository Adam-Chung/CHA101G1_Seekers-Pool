package tw.idv.Seeker_Pool_Merge.sam.entity;


// 創建Result是為了回傳給前端統一是JSON格式(統一格式 方便閱讀)

import lombok.*;
import org.springframework.stereotype.Component;


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

    public Result() {
        super();
    }
    public Result(Integer code, String msg, Object data) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getData() {
        return data;
    }
    public void setData(Object data) {
        this.data = data;
    }

}
