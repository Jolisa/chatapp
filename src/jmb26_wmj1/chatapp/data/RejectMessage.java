package jmb26_wmj1.chatapp.data;

import common.AMessageAlgoCmd;
import common.ChatAppDataPacket;
import common.IChatAppReceiver;
import common.message.IMessage;
import common.message.type.error.IRejectMessage;

/**
 * @author jolisabrown
 *
 */
public class RejectMessage implements IRejectMessage {

	/**
	 * ID
	 */
	private static final long serialVersionUID = 4389790120296152169L;

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
	 * Well known message type that caused error 
	 */
	AMessageAlgoCmd<?> msg;

	/**
	 * Constructor
	 * @param <T> -- T type
	 * @param sender Member that asks for a request 
	 * @param message message that failed to send
	 * @param datapacket 
	 * @param id ID of the well known type
	 */
	public <T> RejectMessage(IChatAppReceiver sender, AMessageAlgoCmd<?> message,
			ChatAppDataPacket<? extends IMessage> datapacket) {
		this.sender = sender;
		this.msg = message;
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
		return sender.toString() + " has sent a well known message type of " + msg.toString();
	}

}
