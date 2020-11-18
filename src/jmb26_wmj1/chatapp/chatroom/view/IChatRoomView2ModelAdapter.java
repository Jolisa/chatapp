package jmb26_wmj1.chatapp.chatroom.view;

/**
 * @author jolisabrown
 *
 */
public interface IChatRoomView2ModelAdapter {

	/**
	 * allows user to leave a room
	 */
	public void leaveRoom();

	/**
	 * allows user to send a text communication
	 * @param string text to send
	 */
	public void sendText(String string);

	/**
	 * allows user to a user from list of room members
	 */
	public void removeRoomfromList();

	/**
	 * allows a user to send a an IPoemMessage
	 * @param text label for component
	 */
	public void sendPoem(String text);

}
