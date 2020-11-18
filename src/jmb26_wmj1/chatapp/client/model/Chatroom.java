package jmb26_wmj1.chatapp.client.model;

import java.util.List;
import java.util.UUID;

import common.IChatAppReceiver;
import common.IChatRoomConnector;
import jmb26_wmj1.chatapp.chatroom.model.IMini2MainAdapter;

/**
 * @author jolisabrown
 *
 */
public class Chatroom implements IChatRoomConnector {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4719773411950426202L;
	/**
	 * String correlating to roomname
	 */
	String roomname;
	/**
	 * List of members in a given room
	 */
	List<IChatAppReceiver> members;
	/**
	 * adapter from room mvc to main mvc
	 */
	public IMini2MainAdapter mini2MainAdapter;
	/**
	 * adapter from main mvc to room mvc
	 */
	public IMain2MiniAdapter main2MiniAdapter;

	/**
	 * constructor for chatroom
	 * @param roomname name of room
	 * @param members receivers in room
	 */
	//public 

	public Chatroom(String roomname, List<IChatAppReceiver> members) {

		this.roomname = roomname;
		this.members = members;

	}

	@Override
	public String getChatRoomName() {
		// TODO Auto-generated method stub
		return this.roomname;
	}

	@Override
	public List<IChatAppReceiver> getAllReceivers() {
		// TODO Auto-generated method stub
		return this.members;
	}

	/**
	 * setter for main2miniAdapter
	 * @param miniAdapter adapter to be assigned
	 */
	public void setMain2MiniAdapter(IMain2MiniAdapter miniAdapter) {
		this.main2MiniAdapter = miniAdapter;
	}

	/**
	 * setter for mini2mainAdapter 
	 * @param miniAdapter adapter to be assigned
	 */
	public void setMini2MainAdapter(IMini2MainAdapter miniAdapter) {
		this.mini2MainAdapter = miniAdapter;
	}

	@Override
	public UUID getID() {
		// TODO Auto-generated method stub
		return null;
	}

}
