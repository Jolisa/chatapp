/**
 * 
 */
package jmb26_wmj1.chatapp.data;

import java.rmi.RemoteException;

import common.AMessageAlgoCmd;
import common.ChatAppDataPacket;
import common.IChatRoomConnector;
import common.message.type.ILeaveMessage;
import jmb26_wmj1.chatapp.chatroom.model.IChatRoomModel2ViewAdapter;
import provided.datapacket.IDataPacketID;

/**
 * @author jolisabrown
 *
 */
public class LeaveDataCmd extends AMessageAlgoCmd<ILeaveMessage> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1667649723322680126L;

	/**
	 * room instance
	 */
	private IChatRoomConnector chatroom;

	/**
	 * adapter from model to view
	 */
	private IChatRoomModel2ViewAdapter _m2vAdpt;

	/**
	 * constructor for leave data
	 * @param room
	 * @param m2vAdpt
	 */
	public LeaveDataCmd(IChatRoomConnector room, IChatRoomModel2ViewAdapter m2vAdpt) {

		this.chatroom = room;
		this._m2vAdpt = m2vAdpt;

	}

	@Override
	public Void apply(IDataPacketID index, ChatAppDataPacket<ILeaveMessage> host, Void... params) {
		// TODO Auto-generated method stub
		chatroom.getAllReceivers().remove(host.getSender());

		try {
			this._m2vAdpt.appendString(host.getSender().getName() + "has left the room!");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
