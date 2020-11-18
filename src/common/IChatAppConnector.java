package common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * An interface to create stubs of chat application users. You can 
 * use this interface to create stubs to be put on the discovery server. This is the 
 * application level stub of a receiver, not to be confused with IChatAppReceiever 
 * which can create chat room level stubs of a receiver.
 * @author Group C
 *
 */
public interface IChatAppConnector extends Remote  {
	
	/**
	 * The name the IChatAppConnector object will be bound to in the RMI Registry.
	 */
	public static final String BOUND_NAME = "ChatAppConnector";
	
	/**
	 * Return the name of this user. In other words, return this user's unique user name.
	 * We assume a user's name to be an invariant feature of an IChatAppConnector.
	 * @return String representation of the name of this user
	 * @throws RemoteException remote exception
	 */
	public String getName() throws RemoteException;
	
	/**
	 * Invite a remote user to the room represented by connector. This invitation
	 * feature supports an auto-accept feature. When a remote IChatAppConnector
	 * receives an invitation, they would automatically connect to the room they 
	 * were invited to. You do not need to accept an invitation in this design.
	 * @param connector an instance of a wrapper interface of a chat room connector
	 * @throws RemoteException remote exception
	 */
	public void invite(IChatRoomConnector connector) throws RemoteException;
	
	/**
	 * Request all of the rooms that a remote user is in. If a local user wants to join
	 * one of the rooms, they can simply join by sending all receivers in the room an IJoinMessage.
	 * It is not necessary to ask for permission to join a room.
	 * @return a list of IChatRoomConnectors representing all of the rooms a remote user is in
	 * @throws RemoteException remote exception
	 */
	public List<IChatRoomConnector> request() throws RemoteException;
	
	/**
	 * Auto connects back to a connector upon connection.
	 * @param connector connector stub of user who just connected to you
	 * @throws RemoteException remote exception
	 */
	public void autoConnect(IChatAppConnector connector) throws RemoteException;
	
	/**
	 * Gets all IChatAppConnectors that this connector is connected to.
	 * @return a list of IChatAppConnectors this connector is connected to
	 * @throws RemoteException remote exception
	 */
	public List<IChatAppConnector> getIChatAppConnectors() throws RemoteException;
	
	/**
	 * Disconnects from the application with the stub, connector.
	 * @param connector stub of person who closed their application
	 * @throws RemoteException remote exception
	 */
	public void disconnect(IChatAppConnector connector) throws RemoteException;

}

