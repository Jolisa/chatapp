package jmb26_wmj1.chatapp.data;

import java.rmi.RemoteException;

import common.AMessageAlgoCmd;
import common.ChatAppDataPacket;
import common.IChatRoomConnector;
import common.message.type.IJoinMessage;
import jmb26_wmj1.chatapp.chatroom.model.IChatRoomModel2ViewAdapter;
import provided.datapacket.IDataPacketID;

/**
 * @author jolisabrown
 *
 */
public class JoinDataCmd extends AMessageAlgoCmd<IJoinMessage> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -546737070843927811L;

	/**
	 * room instance
	 */
	private IChatRoomConnector room;
	/**
	 * adapter from model to view
	 */
	private IChatRoomModel2ViewAdapter _m2vAdpt;

	/**
	 * constructor for join data
	 * @param room
	 * @param m2vAdpt
	 */
	public JoinDataCmd(IChatRoomConnector room, IChatRoomModel2ViewAdapter m2vAdpt) {

		this.room = room;
		this._m2vAdpt = m2vAdpt;

	}

	@Override
	public Void apply(IDataPacketID index, ChatAppDataPacket<IJoinMessage> host, Void... params) {
		// TODO Auto-generated method stub
		room.getAllReceivers().add(host.getSender());

		try {
			this._m2vAdpt.appendString(host.getSender().getName() + "has just joined the room!");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
