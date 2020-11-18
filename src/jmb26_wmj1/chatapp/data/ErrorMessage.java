/**
 * 
 */
package jmb26_wmj1.chatapp.data;

import common.ChatAppDataPacket;
import common.IChatAppReceiver;
import common.message.IMessage;
import common.message.type.error.IErrorMessage;
import provided.datapacket.IDataPacketID;

/**
 * @author jolisabrown
 *
 */
public class ErrorMessage implements IErrorMessage {

	/**
	 * ID
	 */
	private static final long serialVersionUID = -3676235849136616557L;

	/**
	 * Packet causing error 
	 *
	 */
	ChatAppDataPacket<? extends IMessage> packet;

	/**
	* Sender of the message
	*/
	IChatAppReceiver sender;
	/**
	 * ID of the message causing the error
	 */
	IDataPacketID id;

	/**
	 * Constructor
	 * @param <T> -- T type
	 * @param sender Member that asks for a request 
	 * @param id ID of the well known type
	 * @param datapacket packet to be sent
	 */
	public <T> ErrorMessage(IChatAppReceiver sender, IDataPacketID id,
			ChatAppDataPacket<? extends IMessage> datapacket) {
		this.sender = sender;
		this.id = id;
		this.packet = datapacket;
	}

	@Override
	public ChatAppDataPacket<? extends IMessage> getErrorMessage() {
		// TODO Auto-generated method stub
		return packet;
	}

	@Override
	public String getErrorString() {
		// TODO Auto-generated method stub
		return sender.toString() + " has issues issues receiving the message of " + id.toString();
	}

}
