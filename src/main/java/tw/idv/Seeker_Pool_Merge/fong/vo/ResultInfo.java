package tw.idv.Seeker_Pool_Merge.fong.vo;


public class ResultInfo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private boolean flag;//後端返回結果正常為true，發生異常返回false
    private Object data;//返回结果 對象
    private String msg;//異常消息


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

    public String getMsg() {
        return msg;
    }

    public void setMsg(String errorMsg) {
        this.msg = errorMsg;
    }
}
