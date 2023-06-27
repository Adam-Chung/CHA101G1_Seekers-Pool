package tw.idv.Seeker_Pool_Merge.jamie.vo;

import java.io.Serializable;

// 封裝後端返回到前端的數據物件
public class ResultInfo implements Serializable {

	private boolean flag;  // 後端返回結果正常為true, 發生異常則返回false
	private Object data; // 後端返回結果數據物件
	private String errorMsg; // 發生異常的錯誤訊息
	
    public ResultInfo() {
    }
    
    public ResultInfo(boolean flag) {
        this.flag = flag;
    }

    public ResultInfo(boolean flag, String errorMsg) {
        this.flag = flag;
        this.errorMsg = errorMsg;
    }

    public ResultInfo(boolean flag, Object data, String errorMsg) {
        this.flag = flag;
        this.data = data;
        this.errorMsg = errorMsg;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
	
}
