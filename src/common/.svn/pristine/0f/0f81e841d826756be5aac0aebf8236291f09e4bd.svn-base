package common.message.type.error;

import provided.datapacket.DataPacketIDFactory;
import provided.datapacket.IDataPacketID;

/**
 * An IExceptionMessage that should be sent if a message was damaged and could not be processed.  
 * This should not be confused with a failure error because this issue would be detected 
 * before the message processing even started.
 * 
 * @author Group C
 *
 */
public interface IErrorMessage extends IExceptionMessage {

	/**
	 * Returns ID of data type
	 * @return data packet ID of data type
	 */
	public static IDataPacketID GetID() {
		return DataPacketIDFactory.Singleton.makeID(IErrorMessage.class);
	}
	
	@Override
	public default IDataPacketID getID() {
		return IErrorMessage.GetID();
	}
	
}
