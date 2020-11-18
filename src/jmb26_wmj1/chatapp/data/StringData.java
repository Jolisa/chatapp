/**
 * 
 */
package jmb26_wmj1.chatapp.data;

import common.message.type.IStringMessage;

/**
 * @author jolisabrown
 *
 */
public class StringData implements IStringMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = 518213312148754584L;

	/**
	 * string to be sent
	 */
	private String stringContents;

	/**
	 * constructor for string data
	 * @param contents
	 */
	public StringData(String contents) {
		this.stringContents = contents;
	}

	@Override
	public String getString() {
		// TODO Auto-generated method stub
		return stringContents;
	}

}
