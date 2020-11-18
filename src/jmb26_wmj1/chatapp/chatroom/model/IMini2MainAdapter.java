package jmb26_wmj1.chatapp.chatroom.model;

import common.IChatRoomConnector;

/**
 * @author jolisabrown
 *
 */
public interface IMini2MainAdapter {

	/**
	 * Installs prior created room view in tabbed Pane when pane is initially clicked
	 * 
	 **/
	public void setRoomView();

	/**
	 * Updates view of the currently selected pane
	 */
	public void updateRoomView();

	//////////////////////////////////////////////////////

	/**
	 * removes room from main view upon leaving
	 * @param room room to be removed from view
	 */
	public void removeRoomFromMainView(IChatRoomConnector room);

	/**
	 * adds a room to users lists of rooms
	 * @param room room to be added
	 */
	public void addRoom2User(IChatRoomConnector room);

	/**
	 * removes a room from the users list of rooms
	 * @param room room to be removed
	 */
	public void removeRoom4User(IChatRoomConnector room);

	/**
	 * updates main view to remove room from user's list of rooms
	 * @param room room to be removed
	 */
	public void removeRoomFromMainList(IChatRoomConnector room);

	/**
	 * displays a string to main view
	 * @param text string to be displayed
	 */
	public void displayString(String text);

}
