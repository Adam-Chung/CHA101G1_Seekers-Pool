package tw.idv.Seeker_Pool_Merge.customer_service.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import tw.idv.Seeker_Pool_Merge.customer_service.model.ChatMessage;
import tw.idv.Seeker_Pool_Merge.customer_service.model.JedisHandleMessage;
import tw.idv.Seeker_Pool_Merge.customer_service.model.States;
import tw.idv.Seeker_Pool_Merge.fong.service.impl.MemberServiceImpl;
import tw.idv.Seeker_Pool_Merge.song.dao.impl.AdministratorDaoImpl;
import tw.idv.Seeker_Pool_Merge.song.vo.AdministratorVo;

@ServerEndpoint("/CustomerService/{userName}")
@Component
public class CustomerServiceNew {
	private static Map<String, Session> sessionsMap = new ConcurrentHashMap<>(); // P357 並行
	Gson gson = new Gson();

	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		sessionsMap.put(userName, userSession);
		if ("employee".equals(userName)) {
			sendToAllForEmpOnline();
		}else {
			if(!isEmpOnline()) {
				// 如果不再線上, 返回不再線上訊息
				ChatMessage message = new ChatMessage();
				message.setSender("employee");
				message.setMessage("offline");
				sendMsg(userSession, message, 4);
			}else {
				ChatMessage message = new ChatMessage();
				message.setSender("employee");
				message.setMessage("online");
				sendMsg(userSession, message, 3);
			}
		}
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		System.out.println(message);
		ChatMessage chatMessage = gson.fromJson(message, ChatMessage.class);
		String sender = chatMessage.getSender();
		String receiver = chatMessage.getReceiver();
//		JedisHandleMessage.saveChatMessage(sender, receiver, message);

		// 訊息型態為會員清單時
		if ("userList".equals(chatMessage.getType())) {
			// 獲取會員清單
			List<String> chatRooms = JedisHandleMessage.getChatRoomList();
			List<Map<String, Object>> userList = new ArrayList<>();
			// 上標籤-> 線上會員
			chatRooms.forEach(userName -> {
				Map<String, Object> user = new HashMap<>();
				user.put("userName", userName);

				// 判斷是否上線
				if (sessionsMap.get(userName) != null) {
					user.put("isOnline", "true");
				} else {
					user.put("isOnline", "false");
				}

				// 獲取最後條訊息, 判斷是否已讀
				ChatMessage lastMsg = JedisHandleMessage.getEmpMemeberLastMsg(userName);
				if (lastMsg != null && "unread".equals(lastMsg.getStatus())) {
					// 未讀且有最後條訊息
					user.put("lastMessage", lastMsg);
					user.put("status", "false");
				} else if (lastMsg != null && "read".equals(lastMsg.getStatus())) {
					// 已讀且有最後條訊息
					user.put("lastMessage", lastMsg);
					user.put("status", "true");
				} else {
					// 無最後條訊息
					user.put("lastMessage", "");
					user.put("status", "false");
				}

				// 放進會員清單集合
				userList.add(user);

			});

			// 返回至前台(客服)
			sendUserListMsg(userSession, userList);
		} else if ("openChatRoom".equals(chatMessage.getType())) {
			// 訊息型態為開啟聊天室
			List<String> msgs = new ArrayList<>();
			if ("employee".equals(chatMessage.getSender())) {
				// 是客服的話
				msgs = JedisHandleMessage.getEmployeeMemberMsg(chatMessage.getReceiver());
			} else {
				// 是會員的話
				msgs = JedisHandleMessage.getEmployeeMemberMsg(chatMessage.getSender());
			}

			if (msgs != null && msgs.size() > 0) {
				// 有歷史訊息的話
				sendHistoryMsg(userSession, msgs);
			} else {
				// 沒有歷史訊息的話, 回傳空陣列
				sendHistoryMsg(userSession, new ArrayList<>());
			}
		} else if ("message".equals(chatMessage.getType())) {

			if ("employee".equals(chatMessage.getSender())) {
				// 是客服的話
				chatMessage.setStatus("read");
			} else {
				// 是會員的話
				chatMessage.setStatus("unread");
			}

			// 儲存聊天訊息
			JedisHandleMessage.saveChatMessage(chatMessage);
			// 找出目標會話
			Session targetSession = sessionsMap.get(chatMessage.getReceiver());
			// 傳送訊息
			sendMessage(targetSession, chatMessage);
		}

	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		e.printStackTrace();
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		String userNameClose = null;
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if (sessionsMap.get(userName).equals(userSession)) {
				userNameClose = userName;
				sessionsMap.remove(userName);
				if ("employee".equals(userName)) {
					sendToAllForEmpOffline();
				}
				break;
			}
		}

	}

	/**
	 * 傳送一般訊息
	 * 
	 * @param userSession
	 * @param message
	 */
	private void sendMessage(Session userSession, Object message) {
		sendMsg(userSession, message, 0);
	}

	/**
	 * 傳送聊天室清單
	 * 
	 * @param userSession
	 * @param message
	 */
	private void sendUserListMsg(Session userSession, Object message) {
		sendMsg(userSession, message, 1);
	}

	/*
	 * 傳送歷史訊息
	 */
	private void sendHistoryMsg(Session userSession, Object message) {
		sendMsg(userSession, message, 2);
	}

	private void sendToAllForEmpOnline() {
		Set<String> userNames = sessionsMap.keySet();
		ChatMessage message = new ChatMessage();
		message.setSender("employee");
		message.setMessage("online");
		for (String userName : userNames) {
			if (!"employee".equals(userName)) {
				Session userS = sessionsMap.get(userName);
				sendMsg(userS, message, 3);
			}
		}
	}

	private void sendToAllForEmpOffline() {
		Set<String> userNames = sessionsMap.keySet();
		ChatMessage message = new ChatMessage();
		message.setSender("employee");
		message.setMessage("offline");
		for (String userName : userNames) {
			if (!"employee".equals(userName)) {
				Session userS = sessionsMap.get(userName);
				sendMsg(userS, message, 4);
			}
		}

	}

	/**
	 * 傳送訊息
	 * 
	 * @param userSession
	 * @param message
	 * @param type
	 */
	private void sendMsg(Session userSession, Object message, int type) {
		if (userSession != null && userSession.isOpen()) {
			System.out.println(message);

			// 組件返回訊息, 添加訊息型態(供前台判斷)
			// 0: 一般訊息, 1:聊天室清單, 2:會員獲取歷史訊息:
			Map<String, Object> returnMsg = new HashMap<>();
			returnMsg.put("type", type);
			returnMsg.put("data", message);
			userSession.getAsyncRemote().sendText(gson.toJson(returnMsg));
		}
	}
	
	private boolean isEmpOnline() {
		Set<String> userNames = sessionsMap.keySet();
		for (String userName : userNames) {
			if ("employee".equals(userName)) {
				return true;
			}
		}
		return false;
	}
}