/**
 * 
 */
package jmb26_wmj1.chatapp.client.model;

import common.IChatAppConnector;
import common.IChatRoomConnector;

/**
 * @author jolisabrown
 *
 */
public interface IModel2ViewAdapter {

	/**
	 * Send message about connection acceptance
	 * @param s string to be sent
	 */
	public void accept(String s);

	/**
	 * starts discovery server
	 */
	public void startDiscoveryPanel();

	/**
	 * creates mini Controller instance
	 */
	public void createMiniController();

	/**
	 * adds a chatroom instance
	 * @param chatRoom room to be added
	 */
	public void addChatroom(IChatRoomConnector chatRoom);

	/**
	 * adds a user
	 * @param connector connector stub of user
	 */
	public void addUser(IChatAppConnector connector);

	/**
	 * removes presence from all rooms
	 */
	public void removeAllRooms();

	/**
	 * removes a room from view
	 * @param room room to be removed
	 */
	public void removeRoomFromView(IChatRoomConnector room);

	/**
	 * creates a mini mvc instance
	 * @param room room paired with mvc
	 * @param user local user of room
	 * @return an adapter from the main model into mini mvc
	 */
	public IMain2MiniAdapter makeMiniMVC(IChatRoomConnector room, IChatAppConnector user);

}
