package jmb26_wmj1.chatapp.data;

import common.message.type.cmd.IRequestCmdMessage;
import provided.datapacket.IDataPacketID;

/**
 * @author jolisabrown
 *
 */
public class RequestCmdMessage implements IRequestCmdMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1363173473561027173L;

	// ID of message 
	/**
	 * class identifier
	 */
	IDataPacketID classInfo;

	/**
	 * constructor for requestcmd message
	 * @param classInformation
	 */
	public RequestCmdMessage(IDataPacketID classInformation) {
		this.classInfo = classInformation;
	}

	@Override
	public IDataPacketID getUnknownID() {
		// TODO Auto-generated method stub
		return classInfo;
	}

}
