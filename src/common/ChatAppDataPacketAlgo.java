package common;

import common.message.IMessage;
import provided.datapacket.DataPacketAlgo;

/**
 * Type narrowed class for a visitor in this design.
 * You should create an instance of this class when you 
 * create a visitor to handle messages in your IChatAppReceiver.
 * 
 * @author Group C
 *
 */
public class ChatAppDataPacketAlgo extends DataPacketAlgo<Void, Void> {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -7036074218762326056L;

	/**
	 * Constructor for a ChatAppDataPacketAlgo
	 * @param defaultCmd the default command that this 
	 */
	public ChatAppDataPacketAlgo(AMessageAlgoCmd<? extends IMessage> defaultCmd) {
		super(defaultCmd);
	}

}
