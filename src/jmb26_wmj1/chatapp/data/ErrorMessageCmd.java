package jmb26_wmj1.chatapp.data;

import java.rmi.RemoteException;

import common.AMessageAlgoCmd;
import common.ChatAppDataPacket;
import common.IChatRoomConnector;
import common.message.type.error.IErrorMessage;
import jmb26_wmj1.chatapp.chatroom.model.IChatRoomModel2ViewAdapter;
import provided.datapacket.IDataPacketID;

/**
 * @author jolisabrown
 *
 */
public class ErrorMessageCmd extends AMessageAlgoCmd<IErrorMessage> {

	/**
	 * ID
	 */
	private static final long serialVersionUID = 7822968093512122466L;

	/**
	 * room instance
	 */
	IChatRoomConnector chatroom;

	/**
	 * adapter from model to view
	 */
	private IChatRoomModel2ViewAdapter _m2vAdpt;

	/**
	 * constructor for error message
	 * @param room room instance
	 * @param m2vAdpt adapter
	 */
	public ErrorMessageCmd(IChatRoomConnector room, IChatRoomModel2ViewAdapter m2vAdpt) {
		chatroom = room;
		this._m2vAdpt = m2vAdpt;
	}

	@Override
	public Void apply(IDataPacketID index, ChatAppDataPacket<IErrorMessage> host, Void... params) {
		// TODO Auto-generated method stub
		try {
			this._m2vAdpt.appendString(host.getSender().getName() + "is having errors receiving message");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
