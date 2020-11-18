package common;

import common.message.IMessage;
import provided.datapacket.DataPacket;

/**
 * Data packet that should be used for this assignment.
 * This class is defined for type narrowing purposes.
 * @author Group C
 *
 * @param <T> generic type that extends IMesssage
 */
public class ChatAppDataPacket<T extends IMessage> extends DataPacket<T, IChatAppReceiver> {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 620854224658823775L;

	/**
	 * Constructor for an AChatAppDataPacket object
	 * @param data generic data type which extends IMessage
	 * @param sender the sender of this message which is an IChatAppReceiver
	 */
	public ChatAppDataPacket(T data, IChatAppReceiver sender) {
		super(data, sender);
	}

}
