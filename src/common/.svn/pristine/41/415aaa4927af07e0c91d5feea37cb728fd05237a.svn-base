package common.message.type.error;

import provided.datapacket.DataPacketIDFactory;
import provided.datapacket.IDataPacketID;

/**
 * An IExceptionMessage that should be sent if 
 * the receiving system refused to process a message. For example,
 * the receiving system could have received a request for the command 
 * for a well-known message type.
 * @author Group C
 *
 */
public interface IRejectMessage extends IExceptionMessage {
	
	/**
	 * Returns ID of data type
	 * @return data packet ID of data type
	 */
	public static IDataPacketID GetID() {
		return DataPacketIDFactory.Singleton.makeID(IRejectMessage.class);
	}
	
	@Override
	public default IDataPacketID getID() {
		return IRejectMessage.GetID();
	}

}
