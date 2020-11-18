/**
 * 
 */
package jmb26_wmj1.chatapp.chatroom.controller;

import java.awt.Component;
import java.rmi.RemoteException;
import javax.swing.JPanel;

import common.IChatAppConnector;
import common.IChatRoomConnector;
import jmb26_wmj1.chatapp.chatroom.model.ChatRoomModel;
import jmb26_wmj1.chatapp.chatroom.model.IChatRoomModel2ViewAdapter;
import jmb26_wmj1.chatapp.chatroom.model.IMini2MainAdapter;
import jmb26_wmj1.chatapp.chatroom.view.ChatRoomView;
import jmb26_wmj1.chatapp.chatroom.view.IChatRoomView2ModelAdapter;

/**
 * @author jolisabrown
 *
 */
public class ChatRoomController {

	/**
	 * The chat room model 
	 */
	private ChatRoomModel roomModel;

	/**
	 * The chat room view
	 */
	private ChatRoomView roomView;

	/**
	 * Constructor 
	 * @param chatroom  chatroom to be created
	 * @param userMainStub stub of user
	 * @param m2MAdpt adapter from mini mvc to main mvc
	 */
	public ChatRoomController(IChatRoomConnector chatroom, IChatAppConnector userMainStub, IMini2MainAdapter m2MAdpt) {

		roomView = new ChatRoomView(new IChatRoomView2ModelAdapter() {
			@Override
			public void sendText(String msg) {
				roomModel.sendText(msg);
			}

			@Override
			public void leaveRoom() {
				// TODO Auto-generated method stub
				try {
					roomModel.leaveRoom();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

			@Override
			public void removeRoomfromList() {
				// TODO Auto-generated method stub
				roomModel.removeRoomfromList();
			}

			@Override
			public void sendPoem(String text) {
				// TODO Auto-generated method stub
				roomModel.sendPoem(text);

			}

		});

		roomModel = new ChatRoomModel(chatroom, m2MAdpt, userMainStub, new IChatRoomModel2ViewAdapter() {

			@Override
			public JPanel createPanel(String roomName) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public JPanel updatePanel(String roomName) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public void appendString(String t) {
				// TODO Auto-generated method stub
				roomView.updateTextView(t);

			}

			@Override
			public void addComponent(String title, Component comp) {
				// TODO Auto-generated method stub
				roomView.addComponent(title, comp);

			}

		});

	}

	/**
	 * returns the mini modl
	 * @return  mini model
	 */
	public ChatRoomModel getMiniModel() {
		return roomModel;
	}

	/**
	 * returns the chatroom view
	 * @return chatroom view
	 */
	public ChatRoomView getMiniView() {
		return roomView;
	}

	/**
	 * starts the mini model and controller
	 */
	public void start() {
		roomModel.start();
		roomView.start();

	}

}
