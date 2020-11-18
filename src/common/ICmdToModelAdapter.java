package common;

import java.util.function.Supplier;

import javax.swing.JComponent;

import common.message.IMessage;

/**
 * Interface to normalize how commands get processed in the model.
 * @author Group C
 */
public interface ICmdToModelAdapter {

	/**
	 * Null adapter that simply which essentially has no-ops for most functions
	 */
	public static final ICmdToModelAdapter NULL_ADAPTER = new ICmdToModelAdapter() {
		
		@Override
		public void sendMessage(IMessage message, IChatAppReceiver receiver) {
			// no-op
		}
		
		@Override
		public void sendMessage(IMessage message) {
			// no-op
		}
		
		@Override
		public String getLocalUserName() {
			return "Default local user name";
		}
		
		@Override
		public String getCurrentChatRoomName() {
			return "Default current chat room name";
		}
		
		@Override
		public void displayString(String text) { 
			// no-op
		}
		

		@Override
		public void displayComponent(Supplier<JComponent> compSupplier, String title) {
			// no-op
		}

		@Override
		public void displayScrollComponent(Supplier<JComponent> compSupplier, String title) {
			// no-op
		}
	};
	
	/**
	 * Displays a JComponent. Previously displayed components should NOT be overwritten or otherwise 
	 * made inaccessible by the displaying of a new component.
	 * @param compSupplier supplier for component
	 * @param title String identifier for this component. This could be a tab title for example.
	 */
	public void displayComponent(Supplier<JComponent> compSupplier, String title);
	
	/**
	 * Puts a component onto a shared display of scrolling components. In other words, you should
	 * use this method to put a component onto the one scrolling component for a room.
	 * Similar to displayComponent(), previously displayed components should 
	 * NOT be overwritten or otherwise made inaccessible by the displaying of a new component.
	 * @param compSupplier supplier for a scroll pane
	 * @param title String identifier for this component. This could be a tab title for example.
	 */
	public void displayScrollComponent(Supplier<JComponent> compSupplier, String title);
	
	/**
	 * Display a string.
	 * @param text string to display
	 */
	public void displayString(String text);
	
	/**
	 * Name of local user
	 * @return user name of local user
	 */
	public String getLocalUserName();
	
	/**
	 * Gets name of current chat room
	 * @return string representation of current chat room name
	 */
	public String getCurrentChatRoomName();
	
	/**
	 * Sends a message to an entire chat room
	 * @param message message that's sent to an entire chat room
	 */
	public void sendMessage(IMessage message);
	
	/**
	 * Sends a message to a particular receiver in a room
	 * @param message message that's sent to a receiver
	 * @param receiver recipient of that message
	 */
	public void sendMessage(IMessage message, IChatAppReceiver receiver);
	
}
