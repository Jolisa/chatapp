/**
 * 
 */
package jmb26_wmj1.chatapp.data;

import javax.swing.JComponent;

/**
 * @author jolisabrown
 *
 */
public class PoemData implements IPoemMessage {

	/**
	 * imageIcon title 
	 */
	String title;

	/**
	 * the imageIcon to be sent
	 */
	JComponent data;

	/**
	 * constructor for poem data
	 * @param data
	 * @param title
	 */
	public PoemData(JComponent data, String title) {
		this.data = data;
		this.title = title;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -8232856606877240099L;

	@Override
	public JComponent getPoemComponent() {
		// TODO Auto-generated method stub
		return data;
	}

	@Override
	public String getComponentTitle() {
		// TODO Auto-generated method stub
		return title;
	}

}
