/**
 * 
 */
package jmb26_wmj1.chatapp.client.controller;

import java.awt.Component;
import java.awt.EventQueue;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import jmb26_wmj1.chatapp.chatroom.controller.ChatRoomController;
import jmb26_wmj1.chatapp.chatroom.model.IMini2MainAdapter;
import jmb26_wmj1.chatapp.chatroom.view.ChatRoomView;
import jmb26_wmj1.chatapp.client.model.ClientModel;
import jmb26_wmj1.chatapp.client.model.IMain2MiniAdapter;
import jmb26_wmj1.chatapp.client.model.IModel2ViewAdapter;
import jmb26_wmj1.chatapp.client.view.ClientView;

import provided.discovery.IEndPointData;
import provided.discovery.impl.model.DiscoveryModel;
import provided.discovery.impl.model.IDiscoveryModelToViewAdapter;
import provided.discovery.impl.view.DiscoveryPanel;
import provided.discovery.impl.view.IDiscoveryPanelAdapter;
import jmb26_wmj1.chatapp.client.view.IView2ModelAdapter;
import jmb26_wmj1.chatapp.util.ChatAppConnectorWrapper;
import jmb26_wmj1.chatapp.util.ChatRoomConnectorWrapper;
import common.*;

/**
 * @author wj
 *
 */
public class ClientController {

	/**
	 * The Discovery server UI panel for the view
	 */
	private DiscoveryPanel<IEndPointData> discPnl;

	/**
	 * A self-contained model to handle the discovery server.   MUST be started AFTER the main model as it needs the IRMIUtils from the main model! 
	 */
	private DiscoveryModel<IChatAppConnector> discModel; // Replace "IRemoteStubType" with the appropriate for the application, i.e. the Remote type of stub in Registry)  
	/**
	 * An instance of the ClientModel class
	 */
	private ClientModel model;
	/**
	 * An instance of the ClientView class
	 */

	private ClientView<ChatAppConnectorWrapper, ChatRoomConnectorWrapper> view;

	/**
	 * Controller connecting client model to view
	 */
	/**
	 * Constructor of the class.   Instantiates and connects the model and the view plus the discovery panel and model.
	 */

	/**
	 * Constructor of the class.   Instantiates and connects the model and the view plus the discovery panel and model.
	 */
	public ClientController() {

		discPnl = new DiscoveryPanel<IEndPointData>(
				(IDiscoveryPanelAdapter<IEndPointData>) new IDiscoveryPanelAdapter<IEndPointData>() {

					/**
					 * watchOnly is ignored b/c discovery model configured for watchOnly = true
					 */
					@Override
					public void connectToDiscoveryServer(String category, boolean watchOnly,
							Consumer<Iterable<IEndPointData>> endPtsUpdateFn) {
						// Ask the discovery model to connect to the discovery server on the given category and use the given updateFn to update the endpoints list in the discovery panel.
						discModel.connectToDiscoveryServer(category, endPtsUpdateFn);
					}

					@Override
					public void connectToEndPoint(IEndPointData selectedEndPt) {
						// Ask the discovery model to obtain a stub from a remote Registry using the info from the given endpoint 
						discModel.connectToEndPoint(selectedEndPt);
					}

				}, true, true); // "Client + Server" usage mode

		discModel = new DiscoveryModel<IChatAppConnector>(new IDiscoveryModelToViewAdapter<IChatAppConnector>() {

			@Override
			public void addStub(IChatAppConnector stub) {
				model.connectToStub(stub); // Give the stub obtained from a remote Registry to the model to process
				view.addToUserList(new ChatAppConnectorWrapper(stub));
			}

		});

		// --- instantiation of main model and view elided ---
		model = new ClientModel(new IModel2ViewAdapter() {

			@Override
			public void accept(String s) {
				//Send to component 
				view.accept(s);

			}

			@Override
			public void startDiscoveryPanel() {
				// TODO Auto-generated method stub

			}

			@Override
			public void createMiniController() {
				// TODO Auto-generated method stub

			}

			@Override
			public void addUser(IChatAppConnector connector) {
				// TODO Auto-generated method stub
				//model.addUser(connector);
				view.addUser(new ChatAppConnectorWrapper(connector));

			}

			@Override
			public void addChatroom(IChatRoomConnector chatRoom) {
				view.addRoom(new ChatRoomConnectorWrapper(chatRoom));

			}

			@Override
			public void removeRoomFromView(IChatRoomConnector room) {
				view.removeMiniView(new ChatRoomConnectorWrapper(room));

			}

			@Override
			public void removeAllRooms() {
				view.removeAllMiniViews();

			}

			@Override
			public IMain2MiniAdapter makeMiniMVC(IChatRoomConnector room, IChatAppConnector user) {

				ChatRoomController miniController;

				miniController = new ChatRoomController(room, user, new IMini2MainAdapter() {
					@Override
					public void updateRoomView() {
						//should this be placed on an invokeLater thread

					}

					@Override
					public void setRoomView() {
						//should this be placed on an invokeLater thread

					}

					@Override
					public void removeRoomFromMainView(IChatRoomConnector room) {
						view.removeMiniView(new ChatRoomConnectorWrapper(room));
					}

					@Override
					public void removeRoomFromMainList(IChatRoomConnector room) {
						//remove room from model list as well 
						model.removeRoom(room);
						view.removeRoomFromList(new ChatRoomConnectorWrapper(room));
					}

					@Override
					public void addRoom2User(IChatRoomConnector room) {
						model.addRoom(room);
					}

					@Override
					public void removeRoom4User(IChatRoomConnector room) {
						model.removeRoom(room);
					}

					@Override
					public void displayString(String text) {
						// TODO Auto-generated method stub

					}

				});

				/// install the mini view 

				ChatRoomView miniView = miniController.getMiniView();
				view.installMiniView(room.getChatRoomName(), miniView);

				return new IMain2MiniAdapter() {

					@Override
					public void appendString(String text) {
						miniView.updateTextView(text);
					}

					@Override
					public IChatRoomConnector getMiniRoom() {
						// TODO Auto-generated method stub
						//return miniController.getMiniRoom();
						return miniController.getMiniModel().getRoom();
					}

					@Override
					public void addComponent(Supplier<Component> compFac) {
						// TODO Auto-generated method stub

					}

					@Override
					public void joinMiniRoom() {
						//miniController.joinMiniRoom();
					}

				};

			}

		});

		view = new ClientView<>(new IView2ModelAdapter<ChatAppConnectorWrapper, ChatRoomConnectorWrapper>() {

			@Override
			public void quit() {
				model.stop();

			}

			@SuppressWarnings("unchecked")
			@Override
			public void connect(IEndPointData endPt, String category, Consumer<Iterable<IEndPointData>> updateFn) {

				boolean watchOnly = false;
				//public void connectToDiscoveryServer(String category, boolean watchOnly, Consumer<Iterable<IEndPointData>> endPtsUpdateFn)

				((IDiscoveryPanelAdapter<IEndPointData>) discPnl).connectToDiscoveryServer(category, watchOnly,
						updateFn);
				((IDiscoveryPanelAdapter<IEndPointData>) discPnl).connectToEndPoint(endPt);
			}

			@Override
			public ChatAppConnectorWrapper connectUser(String IP) throws RemoteException {
				// TODO Auto-generated method stub
				return new ChatAppConnectorWrapper(model.connectTo(IP));
			}

			@Override
			public List<ChatRoomConnectorWrapper> getSelectedUserRooms(ChatAppConnectorWrapper stub)
					throws RemoteException {
				// TODO Auto-generated method stub
				List<ChatRoomConnectorWrapper> list = new ArrayList<ChatRoomConnectorWrapper>();

				stub.getUser().request().forEach((room) -> list.add(new ChatRoomConnectorWrapper(room)));

				return list;
			}

			@Override
			public ChatRoomConnectorWrapper createRoom(String name) {
				// TODO Auto-generated method stub
				return new ChatRoomConnectorWrapper(model.createNewRoom(name));
			}

			@Override
			public void joinRoom(ChatRoomConnectorWrapper room) throws RemoteException {
				model.joinChatRoom(room.getRoom());

			}

			@Override
			public void leaveRoom(ChatRoomConnectorWrapper room) {
				model.leaveChatRoom(room.getRoom());

			}

			@Override
			public void inviteUser(ChatAppConnectorWrapper connectedUser, ChatRoomConnectorWrapper yourRoom) {
				model.inviteToRoom(connectedUser.getUser(), yourRoom.getRoom());

			}

		});
	}

	/**
	 * Starts the view then the model plus the discovery panel and model.  The view needs to be started first so that it can display 
	 * the model status updates as it starts.   The discovery panel is added to the main view after the discovery model starts. 
	 */
	public void start() {
		// start the main view and model
		view.start();
		model.start(); // starts the internal IRMIUtils instance too.

		discPnl.start(); // start the discovery panel
		// Start the discovery model with info about the published endpoint, substituting the appropriate values for the application's friendly name (to appear in discovery server) and bound name for the stub in the local Registry.
		discModel.start(model.getRMIUtils(), "Jolisa", IChatAppConnector.BOUND_NAME);
		view.addCtrlComponent(discPnl); // Add the discovery panel to the view's "control" panel.
	}

	/**
	 * Launch the application.   Sets the window closing behavior to WindowConstants.EXIT_ON_CLOSE
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					(new ClientController()).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
