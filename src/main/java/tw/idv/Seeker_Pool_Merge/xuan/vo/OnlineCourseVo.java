package tw.idv.Seeker_Pool_Merge.xuan.vo;

public class OnlineCourseVo {
	private Integer onNo;
    private String onTitle;
    private String onIndex;    
    private Integer onStatus;
    private String onPic;
    private String onVideo;
	
    @Override
	public String toString() {
		return "OnlineCourseVo [onNo=" + onNo + ", onTitle=" + onTitle + ", onIndex=" + onIndex + ", onStatus="
				+ onStatus + ", onPic=" + onPic + ", onVideo=" + onVideo + "]";
	}

	public Integer getOnNo() {
		return onNo;
	}

	public void setOnNo(Integer onNo) {
		this.onNo = onNo;
	}

	public String getOnTitle() {
		return onTitle;
	}

	public void setOnTitle(String onTitle) {
		this.onTitle = onTitle;
	}

	public String getOnIndex() {
		return onIndex;
	}

	public void setOnIndex(String onIndex) {
		this.onIndex = onIndex;
	}

	public Integer getOnStatus() {
		return onStatus;
	}

	public void setOnStatus(Integer onStatus) {
		this.onStatus = onStatus;
	}

	public String getOnPic() {
		return onPic;
	}

	public void setOnPic(String onPic) {
		this.onPic = onPic;
	}

	public String getOnVideo() {
		return onVideo;
	}

	public void setOnVideo(String onVideo) {
		this.onVideo = onVideo;
	}
 
    
   
    
}
