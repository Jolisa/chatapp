package jmb26_wmj1.chatapp.client.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import javax.swing.JButton;

import javax.swing.JComponent;

import jmb26_wmj1.chatapp.chatroom.view.ChatRoomView;

import jmb26_wmj1.chatapp.util.ChatAppConnectorWrapper;
import jmb26_wmj1.chatapp.util.ChatRoomConnectorWrapper;

import javax.swing.JScrollPane;

import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

import javax.swing.JSplitPane;

/**
 * @author jolisabrown
 *
 * @param <TDropDown1> dropdown for hosts
 * @param <TDropDown2> dropdown for rooms
 */
public class ClientView<TDropDown1, TDropDown2> extends JFrame {
	//TDropDown1 is host, TDropDown2 is room

	/**
	 * 
	 */
	private static final long serialVersionUID = -72085843231102900L;
	/**
	 * set of rooms connected to 
	 */
	private Set<TDropDown2> rooms = new HashSet<>();
	/**
	 * set of users connected to 
	 */
	private Set<TDropDown1> users = new HashSet<>();

	/**
	 * Adapter to Model
	 */
	private IView2ModelAdapter<TDropDown1, TDropDown2> view2ModelAdpt;
	/**
	 * panel for controls
	 */
	private final JPanel controlPanel = new JPanel();
	/**
	 * panel for making chatroom
	 */
	private final JPanel panel_2 = new JPanel();
	/**
	 * label for making chatroom
	 */
	private final JLabel makeChatRoomLabel = new JLabel("Make Chat Room");
	/**
	 * text input for chatroom name
	 */
	private final JTextField chatRoomNameInput = new JTextField();
	/**
	 * button to make chat room
	 */
	private final JButton makeChatRoomButton = new JButton("Make it! ");
	/**
	 * panel containing discovery server
	 */
	private final JPanel panel_3 = new JPanel();
	/**
	 * label for remote host
	 */
	private final JLabel remoteHostLabel = new JLabel("Remote Hosts");
	/**
	 * panel for discovery server
	 */
	private final JPanel discoveryPanel = new JPanel();

	/**
	 * panel for outputs
	 */
	private final JPanel outputPanel = new JPanel();

	/**
	 * panel for interacting with personal and connected rooms
	 */
	private final JPanel panel_4 = new JPanel();
	/**
	 * panel for manually connecting to other
	 */
	private final JPanel manualConnectPanel = new JPanel();
	/**
	 *label for manual connection
	 */
	private final JLabel lblNewLabel = new JLabel("Connect To Manually");
	/**
	 * text field for manual connection
	 */
	private final JTextField manualConnectTextField = new JTextField();
	/**
	 * button for manual connection
	 */
	private final JButton manualConnectButton = new JButton("Connect!");
	/**
	 * panel where connected host info is shown
	 */
	private final JPanel hostPanel = new JPanel();
	/**
	 * label for connected hosts
	 */
	private final JLabel connectedHostsLabel = new JLabel("Connected Hosts");
	/**
	 * dropdown for prior connected hosts
	 */
	private final JComboBox<TDropDown1> hostComboBox = new JComboBox<TDropDown1>();
	/**
	 * panel for connection buttons
	 */
	private final JPanel connectButtonsPanel = new JPanel();
	/**
	 * button for sending invites
	 */
	private final JButton connectInviteButton = new JButton("Invite");
	/**
	 * button for joining other's rooms
	 */
	private final JButton connectJoinButton = new JButton("Join");
	//private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	/**
	 * tabbed pane for rooms 
	 */
	private final JTabbedPane tabbedPane = new JTabbedPane();
	/**
	 * north south split pane for control and output panel
	 */
	private JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, controlPanel, outputPanel);
	/**
	 * panel for room options
	 */
	private final JPanel roomDropdownPanel = new JPanel();
	/**
	 * dropdown for rooms 
	 */
	private final JComboBox<TDropDown2> roomsComboBox = new JComboBox<TDropDown2>();
	/**
	 * label for available chatrooms
	 */
	private final JLabel lblAvailableChatrooms = new JLabel("Connected Host's Chatrooms");
	/**
	 * panel for console
	 */
	private final JPanel consolePanel = new JPanel();
	/**
	 * scrollable panel for console
	 */
	private final JScrollPane consoleScrollPane = new JScrollPane();
	/**
	 * text area where connection information will be appended
	 */
	private final JTextArea consoleArea = new JTextArea();
	/**
	 * panel for user chat rooms
	 */
	private final JPanel userChatRoomsPanel = new JPanel();
	/**
	 * label for personal connected chatrooms
	 */
	private final JLabel lblNewLabel_1 = new JLabel("Your Connected Chatrooms");
	/**
	 * droplist for chatrooms
	 */
	private final JComboBox<TDropDown2> dropListCurChatrooms = new JComboBox<TDropDown2>();

	/**
	 * constructor for client view
	 * @param iView2ModelAdapter adapter to model
	 */

	public ClientView(IView2ModelAdapter<TDropDown1, TDropDown2> iView2ModelAdapter) {
		manualConnectTextField.setToolTipText("Enter IP address for manual connect");
		manualConnectTextField.setColumns(10);
		// TODO Auto-generated constructor stub
		this.view2ModelAdpt = iView2ModelAdapter;

		chatRoomNameInput.setToolTipText("Enter chatroom name for creation here ");
		chatRoomNameInput.setColumns(10);
		initGUI();
	}

	/**
	 * function for initializing gui and elements
	 */
	private void initGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		controlPanel.setToolTipText("Control Panel for ChatApp");

		//getContentPane().add(controlPanel, BorderLayout.NORTH);
		controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.X_AXIS));
		panel_2.setToolTipText("Panel for making ChatRoom");

		controlPanel.add(panel_2);
		panel_2.setLayout(new GridLayout(3, 0, 0, 0));
		makeChatRoomLabel.setToolTipText("Make ChatRoom Label");
		controlPanel.add(panel_3);
		panel_2.add(makeChatRoomLabel);

		panel_2.add(chatRoomNameInput);
		makeChatRoomButton.setToolTipText("Click to make ChatRoom! ");
		makeChatRoomButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//view2ModelAdpt.makeRoom(chatRoomNameInput.getText());
				TDropDown2 newRoom = view2ModelAdpt.createRoom(chatRoomNameInput.getText());

				dropListCurChatrooms.addItem(newRoom);
			}
		});

		panel_2.add(makeChatRoomButton);

		panel_4.setToolTipText("panel for holding additional connection panels");

		controlPanel.add(panel_4);
		///////////
		//panel_4.setLayout(new GridLayout(4, 0, 0, 0));
		panel_4.setLayout(new GridLayout(5, 0, 0, 0));
		manualConnectPanel.setToolTipText("Manual connect to panel");

		panel_4.add(manualConnectPanel);
		manualConnectPanel.setLayout(new GridLayout(3, 0, 0, 0));
		lblNewLabel.setToolTipText("Connect to manually label");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 8));

		manualConnectPanel.add(lblNewLabel);

		manualConnectPanel.add(manualConnectTextField);
		manualConnectButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				TDropDown1 userStub;

				try {
					userStub = view2ModelAdpt.connectUser(manualConnectTextField.getText());

					if (!isInUsers(userStub)) {
						hostComboBox.addItem(userStub);
						users.add(userStub);
					}
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		manualConnectButton.setToolTipText("manual connect button");

		manualConnectPanel.add(manualConnectButton);
		hostPanel.setToolTipText("Panel that holds host info");

		panel_4.add(hostPanel);
		hostPanel.setLayout(new GridLayout(2, 0, 0, 0));
		connectedHostsLabel.setToolTipText("Connected Hosts Label");
		connectedHostsLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 8));

		hostPanel.add(connectedHostsLabel);
		hostComboBox.setToolTipText("Connected Hosts Box");

		hostPanel.add(hostComboBox);

		hostComboBox.addActionListener(new ActionListener() {
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent e) {

				List<TDropDown2> otherRooms;

				try {

					otherRooms = view2ModelAdpt.getSelectedUserRooms((TDropDown1) hostComboBox.getSelectedItem());
					// add other user to the drop list 1 

					// clear the rooms of other person previous 
					roomsComboBox.removeAllItems();

					// add them in to menu 
					for (TDropDown2 room : otherRooms) {
						roomsComboBox.addItem(room);
					}

				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		connectButtonsPanel.setToolTipText("Panel that holds buttons for connecting");
		panel_4.add(roomDropdownPanel);
		roomDropdownPanel.setLayout(new GridLayout(2, 0, 0, 0));
		panel_4.add(connectButtonsPanel);
		connectButtonsPanel.setLayout(new GridLayout(0, 2, 0, 0));
		connectInviteButton.setToolTipText("Invite to chatrooms");
		connectButtonsPanel.add(connectJoinButton);

		connectInviteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//view2ModelAdpt.invite2Room(chatRoomNameInput.getText());
				view2ModelAdpt.inviteUser(hostComboBox.getItemAt(hostComboBox.getSelectedIndex()),
						dropListCurChatrooms.getItemAt(dropListCurChatrooms.getSelectedIndex()));
			}
		});

		connectButtonsPanel.add(connectInviteButton);
		connectJoinButton.setToolTipText("Join selected chat room");

		connectButtonsPanel.add(connectJoinButton);
		roomDropdownPanel.setToolTipText("a panel containing rooms to connect to");

		roomsComboBox.setToolTipText("dropdown populating with the rooms of all connections");
		roomDropdownPanel.add(lblAvailableChatrooms);
		roomDropdownPanel.add(roomsComboBox);
		lblAvailableChatrooms.setToolTipText("label for available rooms");
		userChatRoomsPanel.setToolTipText("panel that holds the user's chatrooms ");

		panel_4.add(userChatRoomsPanel);
		userChatRoomsPanel.setLayout(new GridLayout(2, 0, 0, 0));

		userChatRoomsPanel.add(lblNewLabel_1);
		dropListCurChatrooms.setToolTipText("the chatrooms you are connected to");

		userChatRoomsPanel.add(dropListCurChatrooms);

		connectJoinButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//view2ModelAdpt.joinRoom(chatRoomNameInput.getText());

				try {
					view2ModelAdpt.joinRoom(roomsComboBox.getItemAt(roomsComboBox.getSelectedIndex()));
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		outputPanel.setToolTipText("panel containing all outputs from chatapp");

		//getContentPane().add(outputPanel, BorderLayout.SOUTH);
		outputPanel.setLayout(new GridLayout(1, 0, 0, 0));
		//messagePanel.setToolTipText("panel containing information about message exchange");

		//outputPanel.add(messagePanel);
		//messagePanel.setLayout(new GridLayout(1, 0, 0, 0));

		outputPanel.add(tabbedPane);
		tabbedPane.setToolTipText("Tabbed pane to hold chatrooms ");
		tabbedPane.addTab("Console", null, consolePanel);

		consolePanel.setLayout(new GridLayout(1, 0, 0, 0));
		consoleScrollPane.setToolTipText("scrollpane containing message exchanges");

		consolePanel.add(consoleScrollPane);
		consoleArea.setToolTipText("text area containing message exchanges");

		consoleScrollPane.setViewportView(consoleArea);
		consoleArea.append("Welcome to chat! \n");

		getContentPane().add(splitPane, BorderLayout.SOUTH);

		getContentPane().add(splitPane, BorderLayout.WEST);

		getContentPane().add(splitPane, BorderLayout.WEST);
	}

	/**
	 * Method for displaying connection information to console
	 * @param s string to be displayed to console
	 */
	public void accept(String s) {
		// TODO Auto-generated method stub
		consoleArea.append(s + '\n');

	}

	/**
	 * After connecting to someone in the discovery server, add their stub to hosts dropdown
	 * @param host
	 */
	public void populateHosts(TDropDown1 host) {
		TDropDown1 o = host;
		if (null == o)
			return;

		hostComboBox.insertItemAt(o, 0);

	}

	/**
	 * 
	 * @param rooms
	 */
	public void populateRoom(TDropDown2 rooms) {
		TDropDown2 o = rooms;
		if (null == o)
			return;

		roomsComboBox.insertItemAt(o, 0);

	}

	/**
	 * Add the given component (here a discovery server) to the control panel,  then revalidating and packing the frame.
	 * @param comp The component to add
	 */
	public void addCtrlComponent(JComponent comp) {
		panel_3.add(comp); // Add the component to the control panel
		validate(); // re-runs the frame's layout manager to account for the newly added component 
		pack(); // resizes the frame and panels to make sure the newly added component is visible.  Note that this may adversely affect empty text displays without a preferred size setting.
	}

	/**
	 * add a new tab for newly joined/created chatrooms
	 * @param chatRoomName the name of the new chat
	 * @param miniView   the view object to be installed
	 */
	public void installMiniView(String chatRoomName, ChatRoomView miniView) {
		tabbedPane.addTab(chatRoomName, miniView);

	}

	/////////////////////////////////////////////////////////////////////
	/**
	 * remove all chatrooms from view
	 */
	public void removeAllMiniViews() {
		tabbedPane.removeAll();
	}

	/**
	 * remove a particular chatroom from view
	 * @param room
	 */
	public void removeMiniView(TDropDown2 room) {
		tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
	}

	/**
	 * add a new connected user in view
	 * @param user new connected user
	 */
	public void addUser(TDropDown1 user) {
		hostComboBox.addItem(user);
	}

	/**
	 * add a new room instance to room dropdown
	 * @param room new room instance
	 */

	public void addRoom(TDropDown2 room) {
		if (!isInRooms(room)) {

			dropListCurChatrooms.addItem(room);
			rooms.add(room);
		}
	}

	/**
	 * remove a chatroom from list
	 * @param room room to be removed
	 */

	public void removeRoomFromList(TDropDown2 room) {
		dropListCurChatrooms.removeItem(room);
		rooms.remove(room);
	}

	/**
	 * check with a new user already exists in a chatroom
	 * @param newUser new user in room
	 * @return boolean as to status of user presence
	 */
	private boolean isInUsers(TDropDown1 newUser) {
		for (TDropDown1 user : users) {
			ChatAppConnectorWrapper wrapper = (ChatAppConnectorWrapper) user;
			ChatAppConnectorWrapper newWrapper = (ChatAppConnectorWrapper) newUser;

			try {
				if (wrapper.getUser().getName().equals(newWrapper.getUser().getName()))
					return true;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return false;
	}

	/**
	 * check if you are in a room
	 * @param newRoom
	 * @return boolean as to presence
	 */

	private boolean isInRooms(TDropDown2 newRoom) {
		for (TDropDown2 room : rooms) {
			ChatRoomConnectorWrapper wrapper = (ChatRoomConnectorWrapper) room;
			ChatRoomConnectorWrapper newWrapper = (ChatRoomConnectorWrapper) newRoom;

			if (wrapper.getRoom().getID().equals(newWrapper.getRoom().getID()))
				return true;

		}
		return false;
	}

	///// for the process stub of the discovery server 
	/**
	 * add new connection to list of users
	 * @param userStub stub of user
	 */
	public void addToUserList(TDropDown1 userStub) {

		if (!isInUsers(userStub)) {
			hostComboBox.addItem(userStub);
			users.add(userStub);
		}

	}

	/**
	 * start the view
	 */
	public void start() {
		// TODO Auto-generated method stub
		setVisible(true);
	}

	/**
	 * stop the view
	 */
	public void stop() {
		setVisible(false);
	}

}
