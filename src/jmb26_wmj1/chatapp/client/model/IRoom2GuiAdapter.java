/**
 * 
 */
package jmb26_wmj1.chatapp.client.model;

import javax.swing.JPanel;

/**
 * @author jolisabrown
 *
 */
public interface IRoom2GuiAdapter {

	/**
	 * Creates a corresponding panel occurence in the gui when a new chatroom is created
	 * @return JPanel - jpanel upon which all new messages will display
	 */
	JPanel createView();

}
