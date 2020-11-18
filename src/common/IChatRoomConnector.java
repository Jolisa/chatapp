package common;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * A convenient wrapper interface to encapsulate any chat room details
 * that can help a ChatApp user connect to a chat room. One possible implementation
 * of this is to create a concrete version of this interface to store the details
 * of a chat room. That concrete object can then be serialized and sent to any
 * receiver that wants to join that room.
 * @author Group C
 *
 */
public interface IChatRoomConnector extends Serializable {
	
	/**
	 * Returns the unique identifier for this room
	 * @return UUID for this room connector
	 */
	public UUID getID();

	/**
	 * Get the name of the chat room. Assume a chat room name is
	 * an invariant feature of a chat room.
	 * @return the name of this chat room
	 */
	public String getChatRoomName();
	
	/**
	 * Get all of the stubs of the receivers in this chat room. 
	 * @return list of stubs of all receivers/participants in this chat room.
	 */
	public List<IChatAppReceiver> getAllReceivers();
	
}
