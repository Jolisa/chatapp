/**
 * 
 */
package jmb26_wmj1.chatapp.data;

import common.ChatAppDataPacket;
import common.message.IMessage;
import common.message.type.error.IExceptionMessage;
import provided.datapacket.IDataPacketID;

/**
 * @author jolisabrown
 *
 */
public class ExceptionMessage implements IExceptionMessage {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 7470024938741048858L;

	/**
	 * c2m adapter
	 */
	CmdToModelAdapter adpt;
	/**
	 * exception message
	 */
	String error;
	/**
	 * packet attempted to send
	 */
	ChatAppDataPacket packet;
	/**
	 * message id of packer
	 */
	IDataPacketID id;

	/**
	 * constructor for exception message
	 * @param _adpt
	 * @param _packet
	 * @param _error
	 */
	public ExceptionMessage(CmdToModelAdapter _adpt, ChatAppDataPacket _packet, String _error) {
		this.adpt = _adpt;
		this.error = _error;
		this.packet = _packet;

	}

	@Override
	public ChatAppDataPacket<? extends IMessage> getErrorMessage() {
		// TODO Auto-generated method stub

		return packet;
	}

	@Override
	public String getErrorString() {
		// TODO Auto-generated method stub
		return error;
	}

	@Override
	public IDataPacketID getID() {
		// TODO Auto-generated method stub
		return null;
	}

}
