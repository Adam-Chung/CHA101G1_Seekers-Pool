package tw.idv.Seeker_Pool_Merge.customer_service.model;

public class ChatMessage {
	private String type; // type（類型）：表示訊息的類型，可以是文字、圖片或其他類型
	private String sender; // sender（發送者）：表示發送訊息的人或實體
	private String receiver; // receiver（接收者）：表示接收訊息的人或實體
	private String message; // message（訊息）：表示實際的訊息內容
	private String time; // time（時間）：表示訊息的發送或接收時間
	private String status; // status（狀態）：表示訊息的狀態，例如已讀或未讀
	
	public ChatMessage() {}

	public ChatMessage(String type, String sender, String receiver, String message, String time, String status) {
		this.type = type;
		this.sender = sender;
		this.receiver = receiver;
		this.message = message;
		this.time = time;
		this.status = status;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/*
	 * 因為訊息的相關資訊（例如類型、發送者、接收者、訊息內容、時間和狀態）通常是以文字的形式呈現。
	 * 
	 * 使用 String 型別的好處是它具有廣泛的應用範圍，可以容納各種不同的字元序列。
	 * 
	 * 將任何文本數據存儲在 String 變數中，並對其進行操作，例如連接、比較或格式化。
	 */

}