package common;

import common.message.IMessage;
import provided.datapacket.ADataPacketAlgoCmd;

/**
 * Convenient wrapper class which can be used to set visitor commands.
 * This class is meant to help you with any type-related issues when setting
 * up visitor commands.
 * @param <T> generic type of IMessage
 * @author Group C
 *
 */
public abstract class AMessageAlgoCmd<T extends IMessage> extends ADataPacketAlgoCmd<Void, T, Void, 
	ICmdToModelAdapter, ChatAppDataPacket<T>> {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -6877050469076594859L;
	
	/**
	 * Command to model adapter which we can assume every receiver has.
	 */
	protected transient ICmdToModelAdapter c2mAdapter;
	
	@Override
	public void setCmd2ModelAdpt(ICmdToModelAdapter cmd2ModelAdpt) {
		this.c2mAdapter = cmd2ModelAdpt;
	}
}
