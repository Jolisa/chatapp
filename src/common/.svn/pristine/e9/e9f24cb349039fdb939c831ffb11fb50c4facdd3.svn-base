package common.message.type.error;

import provided.datapacket.DataPacketIDFactory;
import provided.datapacket.IDataPacketID;

/**
 * An IExceptionMessage that should be sent if a the command
 * to process a message failed in some manner.
 * @author Group C
 *
 */
public interface IFailureMessage extends IExceptionMessage {

	/**
	 * Returns ID of data type
	 * @return data packet ID of data type
	 */
	public static IDataPacketID GetID() {
		return DataPacketIDFactory.Singleton.makeID(IFailureMessage.class);
	}
	
	@Override
	public default IDataPacketID getID() {
		return IFailureMessage.GetID();
	}
}
