package jmb26_wmj1.chatapp.client.model;

import java.awt.Component;
import java.util.function.Supplier;

import common.IChatRoomConnector;

/**
 *  Adapter from the main to the mini MVC 
 * @author wj
 *
 */
public interface IMain2MiniAdapter {

	/**
	 * get a room instance from mini mvc
	 * @return an IChatRoomConnector representing room
	 */
	public IChatRoomConnector getMiniRoom();

	/**
	 * adds a component
	 * @param compFac a factory for creating components
	 */
	public void addComponent(Supplier<Component> compFac);

	/**
	 * connects to mini mvc to join a room instance
	 */
	public void joinMiniRoom();

	/**
	 * append received string to view
	 * @param text text to append
	 */
	public void appendString(String text);

}
