package jmb26_wmj1.chatapp.data;

import java.rmi.RemoteException;

import common.AMessageAlgoCmd;
import common.ChatAppDataPacket;
import common.IChatRoomConnector;
import common.message.type.error.IExceptionMessage;
import jmb26_wmj1.chatapp.chatroom.model.IChatRoomModel2ViewAdapter;
import provided.datapacket.IDataPacketID;

/**
 * @author jolisabrown
 *
 */
public class ExceptionMessageCmd extends AMessageAlgoCmd<IExceptionMessage> {

	/**
	 * ID
	 */
	private static final long serialVersionUID = -3578085966015887535L;

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
	public ExceptionMessageCmd(IChatRoomConnector room, IChatRoomModel2ViewAdapter m2vAdpt) {
		chatroom = room;
		this._m2vAdpt = m2vAdpt;
	}

	@Override
	public Void apply(IDataPacketID index, ChatAppDataPacket<IExceptionMessage> host, Void... params) {
		// TODO Auto-generated method stub
		try {
			this._m2vAdpt.appendString(host.getSender().getName() + "is encountering exception when receiving message");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
