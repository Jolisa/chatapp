/**
 * 
 */
package jmb26_wmj1.chatapp.data;

import java.rmi.RemoteException;

import common.AMessageAlgoCmd;
import common.ChatAppDataPacket;
import common.IChatRoomConnector;
import common.message.type.error.IRejectMessage;
import jmb26_wmj1.chatapp.chatroom.model.IChatRoomModel2ViewAdapter;
import provided.datapacket.IDataPacketID;

/**
 * @author wj
 *
 */
public class RejectMessageCmd extends AMessageAlgoCmd<IRejectMessage> {

	/**
	 * ID 
	 */
	private static final long serialVersionUID = 1528795041197724888L;

	/**
	 * room instance
	 */
	IChatRoomConnector chatroom;

	/**
	 * adapter from model to view
	 */
	private IChatRoomModel2ViewAdapter _m2vAdpt;

	/**
	 * @param room
	 * @param m2vAdpt
	 */
	public RejectMessageCmd(IChatRoomConnector room, IChatRoomModel2ViewAdapter m2vAdpt) {
		this.chatroom = room;
		this._m2vAdpt = m2vAdpt;
	}

	@Override
	public Void apply(IDataPacketID index, ChatAppDataPacket<IRejectMessage> host, Void... params) {
		// TODO Auto-generated method stub

		try {
			this._m2vAdpt.appendString(host.getSender().getName() + "has sent a request message that is well known");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
