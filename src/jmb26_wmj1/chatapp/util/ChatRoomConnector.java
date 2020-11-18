package jmb26_wmj1.chatapp.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import common.IChatAppReceiver;
import common.IChatRoomConnector;

/**
 * @author jolisabrown
 *
 */
public class ChatRoomConnector implements IChatRoomConnector {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7654929088373865441L;

	/**
	 * Room's name
	 */
	private String name;

	/**
	 * Room's unique ID 
	 */
	private UUID ID;

	/**
	 * List of members in this room
	 */
	private List<IChatAppReceiver> members;
	/**
	 * string name of room
	 */
	String roomname;

	/**
	 * constructor for chatroomconnector
	 * @param roomname
	 */
	public ChatRoomConnector(String roomname) {
		this(roomname, UUID.randomUUID());
	}

	/**
	 * Constructor
	 * @param roomname Name of the room
	 * @param id ID of the room
	 */
	public ChatRoomConnector(String roomname, UUID id) {
		this.name = roomname;
		this.ID = id;
		this.members = new ArrayList<>();
	}

	@Override
	public String getChatRoomName() {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public List<IChatAppReceiver> getAllReceivers() {
		// TODO Auto-generated method stub
		return members;
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public UUID getID() {
		// TODO Auto-generated method stub
		return ID;
	}

}
