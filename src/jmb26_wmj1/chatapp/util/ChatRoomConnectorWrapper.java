package jmb26_wmj1.chatapp.util;

import common.IChatRoomConnector;

/**
 * @author jolisabrown
 *
 */
public class ChatRoomConnectorWrapper {
	/**
	 * The room wrapped in
	 */
	private IChatRoomConnector chatroom;

	/**
	 * Constructor
	 * @param room Chat room
	 */
	public ChatRoomConnectorWrapper(IChatRoomConnector room) {
		chatroom = room;
	}

	/**
	 * Gets the chat room
	 * @return the Chat room
	 */
	public IChatRoomConnector getRoom() {
		return chatroom;
	}

	@Override
	public String toString() {
		String name = "unknown user";
		try {
			name = chatroom.getChatRoomName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}

}
