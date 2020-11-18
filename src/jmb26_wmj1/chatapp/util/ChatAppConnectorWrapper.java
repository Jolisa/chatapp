package jmb26_wmj1.chatapp.util;

import java.rmi.RemoteException;

import common.IChatAppConnector;

/**
 * @author jolisabrown
 *
 */
public class ChatAppConnectorWrapper {

	/**
	 * The user stub wrapped in 
	 */
	private IChatAppConnector remoteConnectionStub;

	/**
	 * Constructor
	 * @param remoteConnectionStub The remote connection stub
	 */
	public ChatAppConnectorWrapper(IChatAppConnector remoteConnectionStub) {
		this.remoteConnectionStub = remoteConnectionStub;
	}

	/**
	 * Gets the user of the remote connection stub
	 * @return The remote connection user
	 */
	public IChatAppConnector getUser() {
		return this.remoteConnectionStub;
	}

	@Override
	public String toString() {
		String name = "unknown user";
		try {
			name = remoteConnectionStub.getName();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return name;
	}

}
