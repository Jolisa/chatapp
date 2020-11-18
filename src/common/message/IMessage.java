package common.message;

import provided.datapacket.IDataPacketData;

/**
 * Interface for messages used for type narrowing purposes. This is used to 
 * define well known types in this system. Similar to how you created your own
 * tasks in HW07, if you would like to create more well known types for your system, 
 * every type you make would need to implement this interface to be 
 * received by IChatAppReceivers.
 * @author Group C
 *
 */
public interface IMessage extends IDataPacketData {
	
}
