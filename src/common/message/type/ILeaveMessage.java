package common.message.type;

import common.message.IMessage;
import provided.datapacket.DataPacketIDFactory;
import provided.datapacket.IDataPacketID;

/**
 * Message type that users send when leaving a chat room.
 * @author Group C
 *
 */
public interface ILeaveMessage extends IMessage {
	
	/**
	 * Returns ID of data type
	 * @return data packet ID of data type
	 */
	public static IDataPacketID GetID() {
		return DataPacketIDFactory.Singleton.makeID(ILeaveMessage.class);
	}
	
	@Override
	public default IDataPacketID getID() {
		return ILeaveMessage.GetID();
	}
	
}
