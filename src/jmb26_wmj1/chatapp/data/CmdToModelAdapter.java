/**
 * 
 */
package jmb26_wmj1.chatapp.data;

import java.util.function.Supplier;

import javax.swing.JComponent;
import common.IChatAppReceiver;
import common.IChatRoomConnector;
import common.ICmdToModelAdapter;
import common.message.IMessage;
import jmb26_wmj1.chatapp.chatroom.model.IMini2MainAdapter;

/**
 * @author jolisabrown
 *
 */
public class CmdToModelAdapter implements ICmdToModelAdapter {

	/**
	 * adapter from mini to main mvc
	 */
	IMini2MainAdapter miniAdapter;
	/**
	 * room instance
	 */
	IChatRoomConnector chatroom;

	/**
	 * constructor for adapter
	 * @param room room instance
	 */
	public CmdToModelAdapter(IChatRoomConnector room) {
		chatroom = room;
	}

	@Override
	public void displayString(String text) {
		// TODO Auto-generated method stub
		miniAdapter.displayString(text);
	}

	@Override
	public String getLocalUserName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCurrentChatRoomName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void sendMessage(IMessage message) {
		// TODO Auto-generated method stub

	}

	@Override
	public void sendMessage(IMessage message, IChatAppReceiver receiver) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayComponent(Supplier<JComponent> compSupplier, String title) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayScrollComponent(Supplier<JComponent> compSupplier, String title) {
		// TODO Auto-generated method stub

	}

}
