package common.message.type.error;

import common.ChatAppDataPacket;
import common.message.IMessage;

/**
 * An IExceptionMessage captures the invariants of an IErrorMessage, 
 * IFailureMessage, and an IRejectMessage. If you receive any three
 * of these well known types, you will have the capability to get 
 * an error string and the original data packet that could not
 * be processed successfully.
 * 
 * @author Group C
 *
 */
public interface IExceptionMessage extends IMessage {
	
	/**
	 * Returns the original DataPacket that could not be processed
	 * by a message recipient. Since an error could be in the packet not the message itself, 
	 * it is best to send back exactly what was received.
	 * @return DataPacket that could not be processed
	 */
	public ChatAppDataPacket<? extends IMessage> getErrorMessage();
	
	/**
	 * Return an error message that describes exactly what happened. For example,
	 * this can be the exception message that came up in a processing failure.
	 * @return a String describing why a message could not be processed 
	 */
	public String getErrorString();
}
