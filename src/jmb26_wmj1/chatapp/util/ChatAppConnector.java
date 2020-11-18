/**
 * 
 */
package jmb26_wmj1.chatapp.util;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import common.IChatAppConnector;
import common.IChatAppReceiver;
import common.IChatRoomConnector;
import jmb26_wmj1.chatapp.client.model.IMain2MiniAdapter;
import jmb26_wmj1.chatapp.client.model.IModel2ViewAdapter;

/**
 * @author wj
 *
 */
public class ChatAppConnector implements IChatAppConnector {

	/**
	 * The user name
	 */
	private String name;

	/**
	 * The set of chat rooms this user is currently in
	 */
	private List<IChatRoomConnector> rooms;

	/**
	 * Set of connectors connected to 
	 */
	private List<IChatAppConnector> connectedStubs;
	/**
	 * The Model to View adapter
	 */
	private IModel2ViewAdapter m2vAdpt;
	/**
	 * The stub to this user
	 */
	private IChatAppConnector userStub;

	/**
	 * constructor for chatappconnector
	 * @param name
	 * @param _m2vAdpt
	 */
	public ChatAppConnector(String name, IModel2ViewAdapter _m2vAdpt) {
		this.name = name;
		this.rooms = new ArrayList<>();
		this.m2vAdpt = _m2vAdpt;
		this.connectedStubs = new ArrayList<>();

	}

	@Override
	public String getName() throws RemoteException {
		// TODO Auto-generated method stub
		return name;
	}

	@Override
	public void invite(IChatRoomConnector connector) throws RemoteException {
		// TODO Auto-generated method stub
		if (!rooms.contains(connector)) {
			IChatRoomConnector roomCopy = new ChatRoomConnector(connector.getChatRoomName(), connector.getID());
			// add all members to this room
			for (IChatAppReceiver repStub : connector.getAllReceivers()) {
				//roomCopy.addRep(repStub);
				roomCopy.getAllReceivers().add(repStub);
			}
			// tell everybody to add you
			IMain2MiniAdapter m2mAdpt = m2vAdpt.makeMiniMVC(roomCopy, userStub);
			m2vAdpt.addChatroom(m2mAdpt.getMiniRoom());
		}

	}

	@Override
	public List<IChatRoomConnector> request() throws RemoteException {

		return rooms;
	}

	@Override
	public void autoConnect(IChatAppConnector connector) throws RemoteException {
		// TODO Auto-generated method stub
		m2vAdpt.addUser(connector);

	}

	@Override
	public void disconnect(IChatAppConnector connector) throws RemoteException {
		// TODO Auto-generated method stub

	}

	/**
	 * setter for stub
	 * @param userStub
	 */
	public void setUserStub(IChatAppConnector userStub) {
		this.userStub = userStub;
	}

	@Override
	public String toString() {
		return name;
	}

	////////////////// this one 
	@Override
	public List<IChatAppConnector> getIChatAppConnectors() throws RemoteException {
		// TODO Auto-generated method stub
		return connectedStubs;
	}

}
