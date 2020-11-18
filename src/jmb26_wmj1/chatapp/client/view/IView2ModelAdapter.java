/**
 * 
 */
package jmb26_wmj1.chatapp.client.view;

import java.rmi.RemoteException;
import java.util.List;
import java.util.function.Consumer;
import provided.discovery.IEndPointData;

/**
 * @author jolisabrown
 * @param <TDropDown1> dropdown for hosts
 * @param <TDropDown2> dropdown for rooms
 *
 */
public interface IView2ModelAdapter<TDropDown1, TDropDown2> {

	/**
	 * connect to someone via discovery server and get their rooms/etc
	 * @param endPt the endpoint connected to 
	 * @param category the server category
	 * @param updateFn update endpoint info
	 */
	public void connect(IEndPointData endPt, String category, Consumer<Iterable<IEndPointData>> updateFn);

	/**
	 * quit connections
	 */
	public void quit();

	/**
	 * connect to s user
	 * @param IP ip address
	 * @return a dropdown instance of the user connected to
	 * @throws RemoteException
	 */
	public TDropDown1 connectUser(String IP) throws RemoteException;

	/**
	 * get a user's rooms
	 * @param stub Stub of user connected to
	 * @return a list of dropdown instances corresponding to user's rooms
	 * @throws RemoteException
	 */
	public List<TDropDown2> getSelectedUserRooms(TDropDown1 stub) throws RemoteException;

	/**
	 * create a room
	 * @param name name of room created
	 * @return a dropdown instance of the room created
	 */
	public TDropDown2 createRoom(String name);

	/**
	 * allows user to join a room
	 * @param room room to join
	 * @throws RemoteException
	 */
	public void joinRoom(TDropDown2 room) throws RemoteException;

	/**
	 * allows a user to leave a room
	 * @param room room to leave
	 */
	public void leaveRoom(TDropDown2 room);

	/**
	 * invites a user to a room
	 * @param connectedUser user to be invited
	 * @param yourRoom room to be invited to
	 */
	public void inviteUser(TDropDown1 connectedUser, TDropDown2 yourRoom);

}
