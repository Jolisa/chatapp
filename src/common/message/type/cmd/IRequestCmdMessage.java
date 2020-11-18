package common.message.type.cmd;

import common.message.IMessage;
import provided.datapacket.DataPacketIDFactory;
import provided.datapacket.IDataPacketID;

/**
 * If a recipient of a message received an unknown message type from a sender,
 * the recipient would send this message type back to the sender so the sender 
 * can reply with an IAddCmdMessage.
 * @author Group C
 *
 */
public interface IRequestCmdMessage extends IMessage {
	
	/**
	 * Returns ID of data type
	 * @return data packet ID of data type
	 */
	public static IDataPacketID GetID() {
		return DataPacketIDFactory.Singleton.makeID(IRequestCmdMessage.class);
	}
	
	@Override
	public default IDataPacketID getID() {
		return IRequestCmdMessage.GetID();
	}
	
	/**
	 * Getter for the ID of the message that was unknown to a receiver.
	 * The receiver of this message would be able to use this ID to know 
	 * what command to send back in the form of an IAddCmdMessage.
	 * @return ID of the message that was unknown to a receiver
	 */
	public IDataPacketID getUnknownID();
}
