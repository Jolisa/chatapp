/**
 * 
 */
package jmb26_wmj1.chatapp.data;

import java.rmi.RemoteException;

import common.AMessageAlgoCmd;
import common.ChatAppDataPacket;
import common.IChatRoomConnector;
import common.message.type.error.IFailureMessage;
import jmb26_wmj1.chatapp.chatroom.model.IChatRoomModel2ViewAdapter;
import provided.datapacket.IDataPacketID;

/**
 * @author wj
 *
 */
public class FailureMessageCmd extends AMessageAlgoCmd<IFailureMessage> {

	/**
	 * Generated ID 
	 */
	private static final long serialVersionUID = -6759087226743367221L;

	/**
	 * room instance
	 */
	IChatRoomConnector chatroom;

	/**
	 * adapter
	 */
	private IChatRoomModel2ViewAdapter _m2vAdpt;

	/** 
	 * constructor
	 * @param room
	 * @param m2vAdpt
	 */
	public FailureMessageCmd(IChatRoomConnector room, IChatRoomModel2ViewAdapter m2vAdpt) {
		this.chatroom = room;
		this._m2vAdpt = m2vAdpt;
	}

	@Override
	public Void apply(IDataPacketID index, ChatAppDataPacket<IFailureMessage> host, Void... params) {
		// TODO Auto-generated method stub
		try {
			this._m2vAdpt.appendString(host.getSender().getName() + "cannot process this message: " + index.toString());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
