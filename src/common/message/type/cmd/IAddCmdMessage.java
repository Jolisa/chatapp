package common.message.type.cmd;

import common.AMessageAlgoCmd;
import common.message.IMessage;
import provided.datapacket.DataPacketIDFactory;
import provided.datapacket.IDataPacketID;

/**
 * This is the message that a sender of a message would respond with if the recipient
 * of a previous message did not know how to process that message. This command essentially
 * allows the original sender to tell the original receiver how to process a message.
 * @author Group C
 *
 */
public interface IAddCmdMessage extends IMessage {
	
	/**
	 * Returns ID of data type
	 * @return data packet ID of data type
	 */
	public static IDataPacketID GetID() {
		return DataPacketIDFactory.Singleton.makeID(IAddCmdMessage.class);
	}
	
	@Override
	public default IDataPacketID getID() {
		return IAddCmdMessage.GetID();
	}
	
	/**
	 * Getter for the ID of an unknown message. This would 
	 * be the ID of the command that the recipient of this message is learning
	 * to process.
	 * @return ID of unknown message type which recipient is learning about
	 */
	public IDataPacketID getUnknownID();
	
	/**
	 * Getter for the command of an unknown message. This would be the command
	 * that the recipient of this message is learning to process.
	 * @return algo command which recipient is learning about
	 */
	public AMessageAlgoCmd<? extends IMessage> getUnknownAlgoCmd();
	
}
