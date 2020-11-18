package jmb26_wmj1.chatapp.chatroom.model;

import java.awt.Component;
import javax.swing.JPanel;

/**
 * @author jolisabrown
 *
 */
public interface IChatRoomModel2ViewAdapter {

	/**
	 * creates a panel
	 * @param roomName name of room
	 * @return panel created
	 */
	JPanel createPanel(String roomName);

	/**
	 * updates a panel
	 * @param roomName name of room
	 * @return panel to be updated
	 */
	JPanel updatePanel(String roomName);

	/**
	 * appends a string to view
	 * @param t string to be appended
	 */
	////////////////////

	public void appendString(String t);

	/**
	 * adds a component to view
	 * @param title title of component
	 * @param comp component to be added
	 */

	void addComponent(String title, Component comp);

}
