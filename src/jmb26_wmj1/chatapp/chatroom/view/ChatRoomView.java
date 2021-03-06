package jmb26_wmj1.chatapp.chatroom.view;

import java.awt.BorderLayout;
import java.awt.Component;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import jmb26_wmj1.chatapp.chatroom.VerticalScrollPanel;

import java.awt.Color;

/**
 * @author jolisabrown
 *
 */
public class ChatRoomView extends JPanel {

	/**
	 * generated UID 
	 */
	private static final long serialVersionUID = -5244971942429407991L;

	/**
	 * Adapter for the view to model of the chatroom.
	 */
	private IChatRoomView2ModelAdapter v2mAdpt;

	/**
	 * Holds the display and control panel.
	 */
	private final JPanel mainPanel = new JPanel();
	/**
	 * Panel for messaging.
	 */
	private final JPanel controlPanel = new JPanel();
	/**
	 * Panel for the display.
	 */
	private final JScrollPane displayPanel = new JScrollPane();

	/**
	 * Area that displays the messages.
	 */
	private final JTextArea textAreaRoom = new JTextArea(15, 25);

	/**
	 * Panel for status.
	 */
	private final JPanel panelText = new JPanel();

	/**
	 * text field for message sending
	 */
	private final JTextField textField = new JTextField();
	/**
	 * Button to send a message in a chatroom.
	 */
	private final JButton btnSendMsg = new JButton("Send Message");
	/**
	 * Panel for messaging and chatrooms.
	 */
	private final JPanel panelMessage = new JPanel();

	/**
	 * Button to leave the chatroom.
	 */
	private final JButton btnLeave = new JButton("Leave Room");
	/**
	 * Button to send unknown message.
	 */
	private final JButton btnSendUnknown = new JButton("Send Poem");
	/**
	 * panel where poem is shown
	 */
	private final JPanel componentPanel = new JPanel();

	/**
	 * panel where poem is shown
	 */
	private final JPanel consolePanel = new JPanel();
	/**
	 * scrolling panel for poem
	 */
	private final JScrollPane consoleScrollPane = new JScrollPane();
	/**
	 * panel for poem
	 */
	private final JPanel consoleArea = new VerticalScrollPanel(10);

	/**
	 * Constructor
	 * @param adpt IChatRoomView2ModelAdapter
	 */
	public ChatRoomView(IChatRoomView2ModelAdapter adpt) {
		this.v2mAdpt = adpt;
		initGUI();
	}

	/**
	 * Starts the view
	 */
	public void start() {
		setVisible(true);
	}

	/**
	 * Initializes the view.
	 */
	private void initGUI() {

		mainPanel.setLayout(new GridLayout(1, 3));
		add(mainPanel, BorderLayout.CENTER);
		mainPanel.add(displayPanel);
		mainPanel.add(controlPanel);

		displayPanel.setViewportView(panelText);
		panelText.add(textAreaRoom);
		panelText.add(textAreaRoom, BorderLayout.CENTER);

		controlPanel.setLayout(new GridLayout(3, 1));

		controlPanel.add(panelMessage);
		panelMessage.setLayout(new BorderLayout(0, 0));
		textField.setColumns(10);
		panelMessage.add(textField, BorderLayout.NORTH);

		btnSendUnknown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				v2mAdpt.sendPoem(textField.getText());

			}
		});
		btnSendUnknown.setToolTipText("Send unknown message.");
		panelMessage.add(btnSendUnknown, BorderLayout.SOUTH);

		panelMessage.add(btnSendMsg, BorderLayout.CENTER);
		btnSendMsg.setToolTipText("Send message to chatroom.");
		btnSendMsg.addActionListener(e -> {
			v2mAdpt.sendText(textField.getText());

		});

		controlPanel.add(btnLeave);
		componentPanel.setBackground(Color.WHITE);
		componentPanel.setToolTipText("panel for displaying components");

		mainPanel.add(consolePanel);

		btnLeave.addActionListener(e -> {
			v2mAdpt.leaveRoom();
			v2mAdpt.removeRoomfromList();
		});

		consolePanel.setLayout(new GridLayout(1, 0, 0, 0));
		consolePanel.add(consoleScrollPane);

		consoleScrollPane.setViewportView(consoleArea);

	}

	/**
	 * @return room panel
	 */
	public JPanel getPanel() {
		// TODO Auto-generated method stub
		//return chatroomPanel;
		return null;
	}

	/**
	 * adds text to room view when string sent
	 * @param text text to be sent
	 */
	public void updateTextView(String text) {

		textAreaRoom.append(text + "\n");

	}

	/**
	 * adds component to room view
	 * @param title title of component
	 * @param comp the component being added
	 */

	public void addComponent(String title, Component comp) {

		((VerticalScrollPanel) consoleArea).addComponent(title, comp);
	}

}
