package common.message.type;

import common.message.IMessage;
import provided.datapacket.DataPacketIDFactory;
import provided.datapacket.IDataPacketData;
import provided.datapacket.IDataPacketID;

/**
 * Message type that receivers send when joining a chat room. 
 * @author Group C
 *
 */
public interface IJoinMessage extends IMessage {
	
	/**
	 * Returns ID of data type
	 * @return data packet ID of data type
	 */
	public static IDataPacketID GetID() {
		return DataPacketIDFactory.Singleton.makeID(IJoinMessage.class);
	}
	
	@Override
	public default IDataPacketID getID() {
		return IJoinMessage.GetID();
	}
	
}
