/**
 * 
 */
package jmb26_wmj1.chatapp.data;

import common.AMessageAlgoCmd;
import common.message.IMessage;
import common.message.type.cmd.IAddCmdMessage;
import provided.datapacket.IDataPacketID;

/**
 * @author jolisabrown
 *
 */
public class AddCmdMessage implements IAddCmdMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -557618286933712232L;

	/**
	 * command
	 */
	AMessageAlgoCmd<IMessage> cmd;
	/**
	 * id for message
	 */
	IDataPacketID cmdID;

	/**
	 * add a command to visitor
	 * @param id
	 * @param aCmd
	 */
	public AddCmdMessage(IDataPacketID id, AMessageAlgoCmd<IMessage> aCmd) {
		this.cmdID = id;
		this.cmd = aCmd;
	}

	@Override
	public IDataPacketID getUnknownID() {
		// TODO Auto-generated method stub
		return cmdID;
	}

	@Override
	public AMessageAlgoCmd<IMessage> getUnknownAlgoCmd() {
		// TODO Auto-generated method stub
		return cmd;
	}

}
