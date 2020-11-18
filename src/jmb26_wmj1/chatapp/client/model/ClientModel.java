
package jmb26_wmj1.chatapp.client.model;

import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import common.ChatAppDataPacket;
import common.IChatAppConnector;
import common.IChatAppReceiver;
import common.IChatRoomConnector;
import common.message.IMessage;

import jmb26_wmj1.chatapp.util.ChatAppConnector;
import jmb26_wmj1.chatapp.util.ChatRoomConnector;

import provided.discovery.impl.model.DiscoveryConnector;
import provided.discovery.impl.model.RemoteAPIStubFactory;
import provided.rmiUtils.IRMIUtils;
import provided.rmiUtils.IRMI_Defs;
import provided.rmiUtils.RMIUtils;

/**
 * @author jolisabrown
 *
 */
public class ClientModel {

	/**
	 * Name of user 
	 */
	String ourName;

	/**
	 * Server Name 
	 */
	String serverName;

	/**
	 * Connector for discovery server 
	 */
	private DiscoveryConnector discoveryConnector;

	/**
	 * Chatroom connector 
	 */
	private IChatRoomConnector chatroom;

	/**
	 * stub to local user 
	 */
	private IChatAppConnector localUserStub;

	/**
	 * stub to remote user 
	 */
	private IChatAppConnector remoteUserStub;

	/**
	 * our receiver stub 
	 */
	private IChatAppReceiver ourReceiver;

	/**
	 * our user stub 
	 */
	private IChatAppConnector user;

	/**
	 * Local registry 
	 */
	private Registry localRegistry;

	/**
	 * Set of connected user stubs 
	 */
	private Set<IChatAppConnector> connectedUserStubs = new HashSet<IChatAppConnector>();

	/**
	 * Local name of user  
	 */
	public String LOCAL_USER_NAME = IChatAppConnector.BOUND_NAME;

	/**
	 * Remote name of user 
	 */
	public String REMOTE_USER_NAME = IChatAppConnector.BOUND_NAME;

	/**
	 * Local port 
	 */
	public int LOCAL_RMI_PORT = IRMI_Defs.CLASS_SERVER_PORT_CLIENT;

	/**
	 * Remote port 
	 */
	public int REMOTE_RMI_PORT = IRMI_Defs.CLASS_SERVER_PORT_SERVER;

	/**
	 * User port 
	 */
	public int BOUND_PORT_USER = IRMI_Defs.STUB_PORT_SERVER;

	/**
	 * Port 
	 */
	public int BOUND_PORT_REP = IRMI_Defs.STUB_PORT_CLIENT;

	/**
	 * Room Adapters 
	 */
	Hashtable<String, IMain2MiniAdapter> roomAdapters = new Hashtable<String, IMain2MiniAdapter>();

	/**
	 * Adapter to the view 
	 */
	IModel2ViewAdapter model2ViewAdapter;

	/**
	 * rmi Utils initialization 
	 */
	private IRMIUtils rmiUtils = new RMIUtils((s) -> {
		System.out.println("adapter accepted the string " + s);
		model2ViewAdapter.accept(s);
	});

	/**
	 * port for client class
	 */
	private int classServerPort = IRMI_Defs.CLASS_SERVER_PORT_CLIENT;

	/**
	 * port for stub of client
	 */
	private int stubPort = IRMI_Defs.STUB_PORT_CLIENT;

	/**
	 * Discovery Server connector 
	 */
	private DiscoveryConnector discConn;

	/**
	 * Remote stub factory for discovery server usage 
	 */
	private RemoteAPIStubFactory<IChatAppConnector> remoteAPIStubFac; // Substitute the API's connection-level Remote interface for "IConnectionEntity"

	/**
	 * Constructor 
	 * @param iModel2ViewAdapter
	 */
	public ClientModel(IModel2ViewAdapter iModel2ViewAdapter) {
		// TODO Auto-generated constructor stub
		this.model2ViewAdapter = iModel2ViewAdapter;
	}

	/**
	 * Get the internal IRMIUtils instance being used.    The discovery model start method needs the main model's IRMIUtils.
	 * ONLY call the method AFTER the model, i.e. the internal IRMIUtils, has been started!
	 * @return The internal IRMIUtils instance
	 */
	public IRMIUtils getRMIUtils() {
		return this.rmiUtils;
	}

	/**
	 * Manual connection to a Registry to retrieve and process a stub from it.
	 * Connects to the Registry at the given address, retrieves a stub from it, 
	 * then delegates to connectToStub() to process the stub.
	 * @param remoteRegistryIPAddr  The IP address of the remote Registry
	 * @return A remote user stub that we connected to 
	 */
	public IChatAppConnector connectTo(String remoteRegistryIPAddr) {

		try {
			model2ViewAdapter.accept("[MainModel.connectTo()] Locating registry at " + remoteRegistryIPAddr + "...");
			Registry remoteRegistry = rmiUtils.getRemoteRegistry(remoteRegistryIPAddr);
			model2ViewAdapter.accept("[MainModel.connectTo()] Found registry: " + remoteRegistry);
			remoteUserStub = (IChatAppConnector) remoteRegistry.lookup(REMOTE_USER_NAME);
			model2ViewAdapter.accept("[MainModel.connectTo()] Found remote stub: " + remoteUserStub);
			connectedUserStubs.add(remoteUserStub);
			model2ViewAdapter.accept("[MainModel] successfully connected to " + remoteUserStub + "!");

			remoteUserStub.autoConnect(localUserStub);
			model2ViewAdapter.accept("[MainModel]" + remoteRegistryIPAddr + "successfully connected back");

			return remoteUserStub;

		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * Process the newly acquired stub.  This is the method that the discovery model uses in "Client" or "Client + Server" usage modes 
	 * @param stub The newly acquired stub 
	 */
	public void connectToStub(IChatAppConnector remoteStub) {
		System.out.println("This is the remote stub in connectToStub \n" + remoteStub.toString());
		connectedUserStubs.add(remoteStub);
		model2ViewAdapter.accept("[MainModel] successfully connected to " + remoteStub + "!");

		// auto connect 
		try {
			remoteStub.autoConnect(localUserStub);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model2ViewAdapter.accept("[MainModel] successfully connected back");

	}

	/**
	 * Start method for the system 
	 */
	public void start() {
		// TODO Auto-generated method stub

		rmiUtils.startRMI(LOCAL_RMI_PORT);

		try {

			localRegistry = rmiUtils.getLocalRegistry();

			user = new ChatAppConnector("Wilson & Jolisa", model2ViewAdapter);

			localUserStub = (IChatAppConnector) UnicastRemoteObject.exportObject(user, BOUND_PORT_USER);
			((ChatAppConnector) user).setUserStub(localUserStub);
			localRegistry.rebind(LOCAL_USER_NAME, localUserStub);

			model2ViewAdapter.accept("[MainModel] successfully started");

		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	/**
	 * Stop method 
	 * @throws RemoteException 
	 */
	public void stop() {
		// TODO Auto-generated method stub

		model2ViewAdapter.accept("[Client]: Attempting to quit...\n");

		model2ViewAdapter.removeAllRooms();
		/////////////////////////////////////////////
		try {
			localRegistry.unbind(LOCAL_USER_NAME);
			rmiUtils.stopRMI();
		} catch (Exception e) {
			model2ViewAdapter.accept("[Client Error]: RMI unable to quit.\n");
		}
		System.exit(0);

	}

	/**
	 * creates a new chatroom
	 * @param roomName name identifier corresponding to chatroom
	 * @return 
	 */
	public IChatRoomConnector createNewRoom(String roomName) {

		IChatRoomConnector chatroom = new ChatRoomConnector(roomName);
		IMain2MiniAdapter m2mAdpt = model2ViewAdapter.makeMiniMVC(chatroom, localUserStub);
		//assign adapter to the rooms Main2MiniAdapter value and store adapter
		roomAdapters.put(roomName, m2mAdpt);
		addRoom(chatroom);

		return m2mAdpt.getMiniRoom();

	}

	/**
	 * join a new chatroom
	 * @param roomName name identifier corresponding to chatroom
	 * @return 
	 */
	public void joinChatRoom(IChatRoomConnector chatroom) throws RemoteException {

		IChatRoomConnector roomCopy = new ChatRoomConnector(chatroom.getChatRoomName(), chatroom.getID());

		// add all exisiting members to this room
		for (IChatAppReceiver repStub : chatroom.getAllReceivers()) {
			//roomCopy.addRep(repStub);
			roomCopy.getAllReceivers().add(repStub);
		}
		//add newly joined room to our record of room participations
		//ourRooms.add(roomCopy);

		// check so no duplicate 

		for (IChatRoomConnector room : user.request()) {
			if (chatroom.getID() == room.getID()) {
				model2ViewAdapter.accept("You are already in" + room.getChatRoomName());
				return;
			}
		}

		addRoom(chatroom);

		// tell everybody to add you
		IMain2MiniAdapter m2mAdpt = model2ViewAdapter.makeMiniMVC(roomCopy, localUserStub);
		model2ViewAdapter.addChatroom(m2mAdpt.getMiniRoom());

	}

	/**
	 * Leave room 
	 */
	public void leaveChatRoom(IChatRoomConnector chatroom) {

	}

	/**
	 * Add room for a user 
	 * @param IChatROomConnector chatroom connector 
	 */
	public void addRoom(IChatRoomConnector chatroom) {

		try {

			user.request().add(chatroom);

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * remove room for a user 
	 * @param IChatRoomConnector chat room 
	 * @return 
	 */
	public void removeRoom(IChatRoomConnector chatroom) {

		try {
			user.request().remove(chatroom);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * jAdd user stubs for person 
	 * @param userStub which is an IChatAppconnector 
	 * @return 
	 */
	public void addUser(IChatAppConnector userStub) {
		connectedUserStubs.add(userStub);

	}

	/**
	 * Invite a connected stub to a room 
	 * @param IChatAppCOnnector stub and IChatROomConnector room 
	 * @return 
	 */
	public void inviteToRoom(IChatAppConnector stub, IChatRoomConnector room) {
		try {
			stub.invite(room);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * request all the rooms a host has 
	 * @param IChatAppCOnnector host that you are connected to 
	 * @return List of the IChatRoomCOnnectors a host is connected to 
	 */
	public List<IChatRoomConnector> requestRooms(IChatAppConnector host) {
		try {
			return host.request();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * sends a message to all chat room members 
	 * @param data packet message to send 
	 */
	void sendMessagetoChatRoomMembers(ChatAppDataPacket<? extends IMessage> dataToSend) {

		ArrayList<IChatAppReceiver> copy = new ArrayList<>();
		for (IChatAppReceiver member : chatroom.getAllReceivers()) {
			copy.add(member);
		}

		///IChatRoomConnector roomCopy = new ChatRoomConnector(chatroom.getChatRoomName());
		(new Thread(() -> {

			for (IChatAppReceiver member : copy) {

				try {
					member.receiveMessage(dataToSend);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		})).start();

	}

}
