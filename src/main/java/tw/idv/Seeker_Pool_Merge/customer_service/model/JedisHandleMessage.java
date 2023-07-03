package tw.idv.Seeker_Pool_Merge.customer_service.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import tw.idv.Seeker_Pool_Merge.common.util.JedisUtil;

public class JedisHandleMessage {
	// 此範例key的設計為(發送者名稱:接收者名稱)，實際應採用(發送者會員編號:接收者會員編號)
	// 處理使用 Jedis 庫進行聊天訊息存儲和讀取的相關操作

	static Jedis jedis;

	static Gson gson = new Gson();

	static {
		jedis = JedisUtil.getJedis();
	}

	// 從 Redis 中獲取聊天訊息的歷史記錄
	public static List<String> getHistoryMsg(String sender, String receiver) {
		String key = new StringBuilder(sender).append(":").append(receiver).toString();
//		jedis = jedisPool.getResource();
		List<String> historyData = jedis.lrange(key, 0, -1);
		// jedis.lrange(key, 0, -1) 是 Jedis 客戶端庫中的一個方法，用於從 Redis 的列表（List）中獲取一個範圍的元素。
		jedis.close();
		return historyData;
	}

	// 將指定發送者和接收者之間的聊天訊息標記為已讀
	public static List<String> getAllKey() {
//		J jedis = JedisUtil.getJedis().getResource();
		List<String> allKey = new ArrayList<String>();
		for (String key : jedis.keys("employee*")) {
			if (jedis.type(key).equals("list")) {
				allKey.add(key);
			}
		}
		return allKey;
	}

	// 將聊天訊息存儲到 Redis 中
	public static void readAll(String sender, String receiver) {
//		Jedis jedis = JedisPoolUtil.getJedisPool().getResource();
		Gson gson = new Gson();
		List<String> historyData = JedisHandleMessage.getHistoryMsg(sender, receiver);
		String key = new StringBuilder(sender).append(":").append(receiver).toString();
		jedis.del(key);
		for (String oneChat : historyData) {
			ChatMessage cm = gson.fromJson(oneChat, ChatMessage.class);
			if (cm.getStatus() != null) {
				cm.setStatus("read");
			}
			jedis.rpush(key, gson.toJson(cm));
		}
	}

//	public static void saveChatMessage(String sender, String receiver, String message) {
//		// 對雙方來說，都要各存著歷史聊天記錄
//		String senderKey = new StringBuilder(sender).append(":").append(receiver).toString();
//		String receiverKey = new StringBuilder(receiver).append(":").append(sender).toString();
////		Jedis jedis = JedisPoolUtil.getJedisPool().getResource();
//		jedis.rpush(senderKey, message);
//		jedis.rpush(receiverKey, message);
//		jedis.close();
//	}

	/**
	 * 
	 * @param message
	 */
	public static void saveChatMessage(ChatMessage message) {
		// 對雙方來說，都要各存著歷史聊天記錄
		if ("employee".equals(message.getReceiver())) {
			jedis.rpush("employee:" + message.getSender(), gson.toJson(message));
			jedis.rpush("member:" + message.getSender(), gson.toJson(message));
		} else {
			jedis.rpush("employee:" + message.getReceiver(), gson.toJson(message));
			jedis.rpush("member:" + message.getReceiver(), gson.toJson(message));
		}
		jedis.close();
	}

	/**
	 * 獲得聊天室清單
	 * 
	 * @return 聊天室清單
	 */
	public static List<String> getChatRoomList() {
		List<String> allKey = new ArrayList<String>();
		for (String key : jedis.keys("employee*")) {
			if (jedis.type(key).equals("list")) {
				// 將 "employee:"移除, 僅保留會員名稱
				allKey.add(key.replaceAll("employee:", ""));
			}
		}
		return allKey;
	}

	/**
	 * 會員獲取聊天訊息
	 * 
	 * @param member 會員名稱
	 * @return 歷史訊息
	 */
	public static List<String> getMemberMemberMsg(String member) {
		String key = "member:" + member;
		List<String> historyData = jedis.lrange(key, 0, -1);
		// jedis.lrange(key, 0, -1) 是 Jedis 客戶端庫中的一個方法，用於從 Redis 的列表（List）中獲取一個範圍的元素。
		jedis.close();
		return historyData;
	}

	/**
	 * 客服獲取會員聊天訊息
	 * 
	 * @param member 會員名稱
	 * @return 歷史訊息
	 */
	public static List<String> getEmployeeMemberMsg(String member) {
		String key = "employee:" + member;
		List<String> historyData = jedis.lrange(key, 0, -1);
		// jedis.lrange(key, 0, -1) 是 Jedis 客戶端庫中的一個方法，用於從 Redis 的列表（List）中獲取一個範圍的元素。
		jedis.close();
		return historyData;
	}

	/**
	 * 客服獲取會員最後聊天訊息
	 * 
	 * @param member 會員名稱
	 * @return 最後一條訊息
	 */
	public static ChatMessage getEmpMemeberLastMsg(String member) {
		String key = "employee:" + member;
		List<String> lastRow = jedis.lrange(key, 0, 0);
		ChatMessage msg = null;
		if(lastRow != null && lastRow.size()>0) {
			msg = gson.fromJson(lastRow.get(0), ChatMessage.class);
		}
		return msg;
	}

}