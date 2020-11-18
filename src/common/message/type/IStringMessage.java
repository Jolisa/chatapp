package common.message.type;

import common.message.IMessage;
import provided.datapacket.DataPacketIDFactory;
import provided.datapacket.IDataPacketID;

/**
 * Message type for strings. IStringMessages are considered
 * to be well-known types in this system.
 * @author Group C
 *
 */
public interface IStringMessage extends IMessage {
	
	/**
	 * Returns ID of data type
	 * @return data packet ID of data type
	 */
	public static IDataPacketID GetID() {
		return DataPacketIDFactory.Singleton.makeID(IStringMessage.class);
	}
	
	@Override
	public default IDataPacketID getID() {
		return IStringMessage.GetID();
	}
	
	/**
	 * Method that's called to retrieve this message/string/text.
	 * @return String representation of message
	 */
	public String getString();
}
