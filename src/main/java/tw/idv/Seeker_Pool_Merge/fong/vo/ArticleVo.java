package tw.idv.Seeker_Pool_Merge.fong.vo;

import java.sql.Date;

public class ArticleVo implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer arNo;
	private Integer memId;
	private Date arPubTime;
	private String arTitle;
	private String arContent;
	private String arImg;
	private Integer arStatus;
	private Integer arHits;
	
	
	
	@Override
	public String toString() {
		return "ArticleVo [arNo=" + arNo + ", memId=" + memId + ", arPubTime=" + arPubTime + ", arTitle=" + arTitle
				+ ", arContent=" + arContent + ", arImg=" + arImg + ", arStatus=" + arStatus + ", arHits=" + arHits
				+ "]";
	}
	public Integer getArNo() {
		return arNo;
	}
	public void setArNo(Integer arNo) {
		this.arNo = arNo;
	}
	public Integer getMemId() {
		return memId;
	}
	public void setMemId(Integer memId) {
		this.memId = memId;
	}
	public Date getArPubTime() {
		return arPubTime;
	}
	public void setArPubTime(Date arPubTime) {
		this.arPubTime = arPubTime;
	}
	public String getArTitle() {
		return arTitle;
	}
	public void setArTitle(String arTitle) {
		this.arTitle = arTitle;
	}
	public String getArContent() {
		return arContent;
	}
	public void setArContent(String arContent) {
		this.arContent = arContent;
	}
	public String getArImg() {
		return arImg;
	}
	public void setArImg(String arImg) {
		this.arImg = arImg;
	}
	public Integer getArStatus() {
		return arStatus;
	}
	public void setArStatus(Integer arStatus) {
		this.arStatus = arStatus;
	}
	public Integer getArHits() {
		return arHits;
	}
	public void setArHits(Integer arHits) {
		this.arHits = arHits;
	}
	
	

}
