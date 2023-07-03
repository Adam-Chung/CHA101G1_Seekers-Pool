package tw.idv.Seeker_Pool_Merge.customer_service.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class States {
	private String type; // 狀態的類型
	// the user changing the state
	private String user; // 正在更改狀態的使用者
	// total users
	private List<Map<String, String>> userList; // 使用者清單

	public States(String type, String user, List<Map<String, String>> userList) {
		super();
		this.type = type;
		this.user = user;
		this.userList = userList;
	}

	public String getType() { // 獲取和設定狀態的類型
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUser() { // 獲取和設定正在更改狀態的使用者
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<Map<String, String>> getUserList() { // 獲取和設定使用者清單
		return userList;
	}

	public void setUserList(List<Map<String, String>> userList) {
		this.userList = userList;
	}
}
