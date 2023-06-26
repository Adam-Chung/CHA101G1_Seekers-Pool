package tw.idv.Seeker_Pool_Merge.article.model;

import java.sql.Timestamp;
import java.util.Arrays;

@SuppressWarnings("serial")
public class ArticleVo implements java.io.Serializable {

	private Integer arNo;
	private Integer memId;
	private Timestamp arPubTime;
	private String arTitle;
	private String arContent;
	private byte[] arImg;
	private String arImgBase64;
	
	public ArticleVo() {
		super();
	}
	

	@Override
	public String toString() {
		return "ArticleVo [arNo=" + arNo + ", memId=" + memId + ", arPubTime=" + arPubTime + ", arTitle=" + arTitle
				+ ", arContent=" + arContent + ", arImg=" + Arrays.toString(arImg) + ", arImgBase64=" + arImgBase64
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


	public Timestamp getArPubTime() {
		return arPubTime;
	}


	public void setArPubTime(Timestamp arPubTime) {
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


	public byte[] getArImg() {
		return arImg;
	}


	public void setArImg(byte[] arImg) {
		this.arImg = arImg;
	}


	public String getArImgBase64() {
		return arImgBase64;
	}

	public void setArImgBase64(String arImgBase64) {
		this.arImgBase64 = arImgBase64;
	}



	

	}