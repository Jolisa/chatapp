/**
 * 
 */
package jmb26_wmj1.chatapp.chatroom.model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import common.AMessageAlgoCmd;
import common.ChatAppDataPacket;
import common.ChatAppDataPacketAlgo;
import common.IChatAppConnector;
import common.IChatAppReceiver;
import common.IChatRoomConnector;
import common.ICmdToModelAdapter;
import common.message.IMessage;
import common.message.type.IJoinMessage;
import common.message.type.ILeaveMessage;
import common.message.type.IStringMessage;
import jmb26_wmj1.chatapp.data.AddCmdMessage;
import jmb26_wmj1.chatapp.data.ErrorMessage;
import jmb26_wmj1.chatapp.data.ErrorMessageCmd;
import jmb26_wmj1.chatapp.data.FailureMessage;
import jmb26_wmj1.chatapp.data.FailureMessageCmd;
import jmb26_wmj1.chatapp.data.IPoemMessage;
import jmb26_wmj1.chatapp.data.JoinData;
import jmb26_wmj1.chatapp.data.LeaveData;
import jmb26_wmj1.chatapp.data.PoemCommand;
import jmb26_wmj1.chatapp.data.PoemData;
import jmb26_wmj1.chatapp.data.RejectMessage;
import jmb26_wmj1.chatapp.data.RejectMessageCmd;
import jmb26_wmj1.chatapp.util.ChatAppReceiver;
import jmb26_wmj1.chatapp.util.ChatRoomConnector;
import provided.datapacket.IDataPacketData;
import provided.datapacket.IDataPacketID;
import provided.rmiUtils.IRMI_Defs;
import common.message.type.cmd.IAddCmdMessage;
import common.message.type.cmd.IRequestCmdMessage;
import common.message.type.error.IErrorMessage;
import common.message.type.error.IFailureMessage;
import common.message.type.error.IRejectMessage;
import jmb26_wmj1.chatapp.data.RequestCmdMessage;
import jmb26_wmj1.chatapp.data.StringData;

/**
 * @author jolisabrown
 *
 */
public class ChatRoomModel {

	/**
	 * chatroom 
	 */
	private IChatRoomConnector chatroom;

	/**
	 * stub connector 
	 */
	private IChatAppConnector userStub;

	///stub of member 

	/**
	 * visitor algorithms to be 
	 */

	ChatAppDataPacketAlgo visitorAlgos;

	/**
	 * user receiver stub 
	 */
	private IChatAppReceiver receiverStub;

	/**
	 * adapter from mini mvc to main MVC 
	 */
	private IMini2MainAdapter m2MAdpt;

	/**
	 * adapter from model to view 
	 */
	private IChatRoomModel2ViewAdapter m2vAdpt;
	/**
	 * visitor containing execution 
	 */
	private ChatAppDataPacketAlgo visitor;
	/**
	 * adapter from commands to model
	 */
	private ICmdToModelAdapter c2mAdpt = new ICmdToModelAdapter() {

		@SuppressWarnings("unchecked")
		@Override
		public void sendMessage(IMessage message) {

			sendMessagetoChatRoomMembers((ChatAppDataPacket<? extends IMessage>) message);

		}

		@SuppressWarnings("unchecked")
		@Override
		public void sendMessage(IMessage message, IChatAppReceiver receiver) {
			// TODO Auto-generated method stub
			try {
				receiver.receiveMessage((ChatAppDataPacket<? extends IMessage>) message);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		@Override
		public void displayComponent(Supplier<JComponent> compSupplier, String title) {
			m2vAdpt.addComponent(title, compSupplier.get());

		}

		@Override
		public void displayScrollComponent(Supplier<JComponent> compSupplier, String title) {

			m2vAdpt.addComponent(title, compSupplier.get());

		}

		@Override
		public void displayString(String text) {

			m2vAdpt.appendString(text + "\n");
		}

		@Override
		public String getLocalUserName() {
			// TODO Auto-generated method stub
			try {
				return userStub.getName();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		@Override
		public String getCurrentChatRoomName() {
			// TODO Auto-generated method stub
			return chatroom.getChatRoomName();
		}

	};

	/**
	 * constructor for model
	 * @param chatroom2 chatrooom
	 * @param adpt adapter to main mvc
	 * @param _userStub stub of user
	 * @param _m2vAdpt adapter to view 
	 */
	public ChatRoomModel(IChatRoomConnector chatroom2, IMini2MainAdapter adpt, IChatAppConnector _userStub,
			IChatRoomModel2ViewAdapter _m2vAdpt) {

		start();
		chatroom = new ChatRoomConnector(chatroom2.getChatRoomName(), chatroom2.getID());

		// add all existing members to this room 
		for (IChatAppReceiver receiverStub : chatroom2.getAllReceivers()) {
			chatroom.getAllReceivers().add(receiverStub);
		}

		m2MAdpt = adpt;
		userStub = _userStub;
		m2vAdpt = _m2vAdpt;

		IChatAppReceiver receiver = new ChatAppReceiver(chatroom2, userStub, c2mAdpt, visitorAlgos);

		try {
			receiverStub = (IChatAppReceiver) UnicastRemoteObject.exportObject(receiver, IRMI_Defs.STUB_PORT_CLIENT);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		IJoinMessage data = new JoinData();
		ChatAppDataPacket<IJoinMessage> msg = new ChatAppDataPacket<>(data, receiverStub);

		sendMessagetoChatRoomMembers(msg);

		/////////////////////////////////////////////////////////////

		chatroom.getAllReceivers().add(receiverStub);

		///////////////////////////////////////////////////////////////////////////////////////////////////////////////

		m2MAdpt.addRoom2User(chatroom);
		// start of message handling stuff  

	}

	/**
	 * gets the current room
	 * @return the current room
	 */
	public IChatRoomConnector getRoom() {
		// TODO Auto-generated method stub
		return this.chatroom;
	}

	///////////////////////////////////////////////////

	/**
	 * remove room from list of rooms 
	 */
	public void removeRoomfromList() {
		m2MAdpt.removeRoomFromMainList(chatroom);
	}

	/**
	 * send message to all members of the chat room 
	 * @param dataToSend message to be sent
	 */
	void sendMessagetoChatRoomMembers(ChatAppDataPacket<? extends IMessage> dataToSend) {

		// TODO start with simple for each on the data stucture 

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

	/**
	 * allows user to leave room
	 * @throws RemoteException
	 */
	public void leaveRoom() throws RemoteException {

		chatroom.getAllReceivers().remove(receiverStub);

		LeaveData data = new LeaveData();
		/// not sure what to do after,.... 

		// tell everyone to remove user 
		ChatAppDataPacket<ILeaveMessage> leaveDataPacket = new ChatAppDataPacket<>(data, receiverStub);

		// need to message everyone with this data now... probably a for loop 1 by 1 
		sendMessagetoChatRoomMembers(leaveDataPacket);

		m2MAdpt.removeRoom4User(chatroom);
		m2MAdpt.removeRoomFromMainList(chatroom);
		m2MAdpt.removeRoomFromMainView(chatroom);

	}

	/////////////////////////////////////////////////////////////////////////////////

	/**
	 * Add a new member to chatroom upon join request
	 * @param sender member sending join request
	 */
	protected void addReceiver(IChatAppReceiver sender) {
		chatroom.getAllReceivers().add(sender);

	}

	/**
	 * Remove a member from chatroom upon request to leave
	 * @param sender member sending leave request
	 */
	protected void removeReceiver(IChatAppReceiver sender) {
		// TODO Auto-generated method stub
		chatroom.getAllReceivers().remove(sender);
	}

	/**
	 * allows user to add themselves to room 
	 */
	public void joinRoom() {
		// TODO Auto-generated method stub
		ChatAppDataPacket joinPacket = new ChatAppDataPacket(new JoinData(), receiverStub);

		// TODO 
		sendMessagetoChatRoomMembers(joinPacket);

	}

	/**
	 * send IString message
	 * @param msg text to be sent
	 */
	public void sendText(String msg) {
		IStringMessage IStringMessage = new StringData(msg);
		ChatAppDataPacket<IStringMessage> stringPacket = new ChatAppDataPacket<>(IStringMessage, receiverStub);
		this.sendMessagetoChatRoomMembers(stringPacket);
	}

	/**
	 * send Poem
	 * @param title the title given to the component
	 */
	public void sendPoem(String title) {
		// TODO Auto-generated method stub

		JTextArea poemArea = new JTextArea();
		poemArea.append(" Sir Walter Ralegh’s:\n" + "Like truthless dreams, so are my joys expir’d,\n"
				+ "And past return are all my dandled days;\n" + "My love misled, and fancy quite retir’d,\n"
				+ "Of all which pass’d the sorrow only stays…\n" + "or in Chidiock Tichborne’s ‘Elegy’:\n"
				+ "The spring is past, and yet it hath not sprung,\n"
				+ "The fruit is dead, and yet the leaves are green,\n" + "My youth is gone, and yet I am but young,\n"
				+ "I saw the world, and yet I was not seen,\n" + "My thread is cut, and yet it was not spun,\n"
				+ "And now I live, and now my life is done.\n" + "");

		JPanel poemLabel = new JPanel();
		poemLabel.add(poemArea);
		IPoemMessage data = new PoemData((JComponent) poemLabel, title);

		ChatAppDataPacket<IPoemMessage> datapacket = new ChatAppDataPacket<>(data, receiverStub);

		sendMessagetoChatRoomMembers(datapacket);

	}

	/**
	 * Create extended visitors for mini model
	 */
	public void start() {

		ConcurrentHashMap<IDataPacketID, List<ChatAppDataPacket<? extends IDataPacketData>>> cache = new ConcurrentHashMap<>();

		visitorAlgos = new ChatAppDataPacketAlgo(new AMessageAlgoCmd<IMessage>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Void apply(IDataPacketID index, ChatAppDataPacket<IMessage> host, Void... params) {

				if (cache.containsKey(index)) {
					cache.get(index).add(host);
				} else {
					ArrayList<ChatAppDataPacket<? extends IDataPacketData>> newList = new ArrayList<>();
					newList.add(host);
					cache.put(index, newList);
				}

				// use an request command for request unknown 

				IRequestCmdMessage data = new RequestCmdMessage(index);
				ChatAppDataPacket<IRequestCmdMessage> requestData = new ChatAppDataPacket<>(data, receiverStub);

				try {
					host.getSender().receiveMessage(requestData);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					errorMessage(host.getSender(), index, host);
					e.printStackTrace();
				} catch (NullPointerException e) {
					try {
						failureMessage(host.getSender(), index, host);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}

				return null;
			}

		});
		/*
		 * Execution case for join command
		 */
		visitorAlgos.setCmd(IJoinMessage.GetID(), new AMessageAlgoCmd<IJoinMessage>() {

			/**
			 * Generated UID
			 */
			private static final long serialVersionUID = -3561235409719293356L;

			@Override
			public Void apply(IDataPacketID index, ChatAppDataPacket<IJoinMessage> host, Void... params) {

				if (!chatroom.getAllReceivers().contains(host.getSender()))
					chatroom.getAllReceivers().add(host.getSender());

				try {
					m2vAdpt.appendString(host.getSender().getName() + " has joined the room");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					errorMessage(host.getSender(), index, host);
					e.printStackTrace();
				} catch (NullPointerException e) {

					try {
						failureMessage(host.getSender(), index, host);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				return null;
			}

		});
		/*
		 * Execution case for leave command
		 */
		visitorAlgos.setCmd(ILeaveMessage.GetID(), new AMessageAlgoCmd<ILeaveMessage>() {

			/**
			 * Generated UID
			 */
			private static final long serialVersionUID = -354492408991302327L;

			@Override
			public Void apply(IDataPacketID index, ChatAppDataPacket<ILeaveMessage> host, Void... params) {

				chatroom.getAllReceivers().remove(host.getSender());

				try {
					m2vAdpt.appendString(host.getSender().getName() + " has just left the room");
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return null;
			}

		});

		/*
		 * Execution case for string command
		 */
		visitorAlgos.setCmd(IStringMessage.GetID(), new AMessageAlgoCmd<IStringMessage>() {

			/**
			 * Generated UID
			 */
			private static final long serialVersionUID = 1504126955415236434L;

			@Override
			public Void apply(IDataPacketID index, ChatAppDataPacket<IStringMessage> host, Void... params) {

				m2vAdpt.appendString(host.getData().getString());

				return null;
			}

		});

		PoemCommand poemCmd = new PoemCommand();
		poemCmd.setCmd2ModelAdpt(c2mAdpt);
		visitorAlgos.setCmd(IPoemMessage.GetID(), poemCmd);

		/*
		 * Execution case for Add foreign command
		 */
		visitorAlgos.setCmd(IAddCmdMessage.GetID(), new AMessageAlgoCmd<IAddCmdMessage>() {

			/**
			 * Generated UID
			 */
			private static final long serialVersionUID = -4256573324819672625L;

			@Override
			public Void apply(IDataPacketID index, ChatAppDataPacket<IAddCmdMessage> host, Void... params) {

				/**
				 * Execution case for foreign command to be added to extended visitor ---- should the generic below be an
				 * IAddCmdMessage???
				 */

				AMessageAlgoCmd<? extends IMessage> foreignAlgo = host.getData().getUnknownAlgoCmd();
				foreignAlgo.setCmd2ModelAdpt(c2mAdpt);
				visitorAlgos.setCmd(host.getData().getUnknownID(), foreignAlgo);

				for (ChatAppDataPacket<? extends IDataPacketData> item : cache.get(host.getData().getUnknownID())) {

					item.execute(visitorAlgos, params);

				}

				return null;
			}

		});

		/*
		 * Execution case for Request foreign command
		 */
		visitorAlgos.setCmd(IRequestCmdMessage.GetID(), new AMessageAlgoCmd<IRequestCmdMessage>() {

			/**
			 * Generated UID
			 */
			private static final long serialVersionUID = -4789603591530945811L;

			@Override
			public Void apply(IDataPacketID index, ChatAppDataPacket<IRequestCmdMessage> host, Void... params) {

				IRequestCmdMessage request = host.getData();

				AMessageAlgoCmd<IMessage> cmd = (AMessageAlgoCmd<IMessage>) visitorAlgos.getCmd(request.getUnknownID());

				AddCmdMessage data = new AddCmdMessage(request.getUnknownID(), cmd);

				ChatAppDataPacket<IAddCmdMessage> addData = new ChatAppDataPacket<IAddCmdMessage>(data, receiverStub);

				try {

					// checks to see if existing 
					if (cmd == IJoinMessage.GetID() || cmd == ILeaveMessage.GetID() || cmd == IStringMessage.GetID()) {

						RejectMessage rejection = new RejectMessage(host.getSender(), cmd, addData);
						ChatAppDataPacket<IRejectMessage> statusErrorData = new ChatAppDataPacket<>(rejection,
								host.getSender());
						host.getSender().receiveMessage(statusErrorData);

					}

					host.getSender().receiveMessage(addData);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					errorMessage(host.getSender(), index, host);
					e.printStackTrace();
				} catch (NullPointerException e) {

					try {
						failureMessage(host.getSender(), index, host);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

				return null;
			}

		});

		//////////////////////////// install commands for error messages 

		// Error message command 

		ErrorMessageCmd errorMessageCmd = new ErrorMessageCmd(chatroom, this.m2vAdpt);
		errorMessageCmd.setCmd2ModelAdpt(this.c2mAdpt);
		visitorAlgos.setCmd(IErrorMessage.GetID(), errorMessageCmd);

		// Failure message command 

		FailureMessageCmd failureMessageCmd = new FailureMessageCmd(chatroom, this.m2vAdpt);
		failureMessageCmd.setCmd2ModelAdpt(this.c2mAdpt);
		visitorAlgos.setCmd(IFailureMessage.GetID(), failureMessageCmd);

		// Reject message command 

		RejectMessageCmd rejectMessageCmd = new RejectMessageCmd(chatroom, this.m2vAdpt);
		rejectMessageCmd.setCmd2ModelAdpt(this.c2mAdpt);
		visitorAlgos.setCmd(IRejectMessage.GetID(), rejectMessageCmd);

	} // end start 

	/**
	 * Send an error message
	 * @param rep sender of unproccessed message
	 * @param id id of failed message type
	 * @param datapacket packet that failed to send
	 */
	//////// Error methods 

	public void errorMessage(IChatAppReceiver rep, IDataPacketID id, ChatAppDataPacket<? extends IMessage> datapacket) {

		// remove problematic stub 
		chatroom.getAllReceivers().remove(rep);
		// send message to all 

		ErrorMessage data = new ErrorMessage(rep, id, datapacket);
		ChatAppDataPacket<IErrorMessage> errorPacket = new ChatAppDataPacket<>(data, rep);
		sendMessagetoChatRoomMembers(errorPacket);

	}

	/**
	 * Send a message on failure
	 * @param rep sender of unproccessedmessage
	 * @param id id of failed message type
	 * @param datapacket packet that failed to send
	 * @throws RemoteException
	 */

	public void failureMessage(IChatAppReceiver rep, IDataPacketID id, ChatAppDataPacket<? extends IMessage> datapacket)
			throws RemoteException {

		// send error to sender 

		FailureMessage data = new FailureMessage(rep, id, datapacket);

		ChatAppDataPacket<IFailureMessage> failurePacket = new ChatAppDataPacket<>(data, rep);
		rep.receiveMessage(failurePacket);

	}

}