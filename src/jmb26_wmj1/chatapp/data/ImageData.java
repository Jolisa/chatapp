package jmb26_wmj1.chatapp.data;

import javax.swing.JComponent;

/**
 * @author jolisabrown
 *
 */
public class ImageData implements IImageMessage {

	/**
	 * Generated ID
	 */
	private static final long serialVersionUID = -8030986741005731932L;

	/**
	 * imageIcon title 
	 */
	String title;

	/**
	 * the imageIcon to be sent
	 */
	JComponent data;

	/**
	 * Constructor
	 * @param data component 
	 */
	public ImageData(JComponent data) {
		this.data = data;
	}

	@Override
	public JComponent getImageComponent() {
		return data;
	}

	@Override
	public String getImageComponentTitle() {
		// TODO Auto-generated method stub
		return title;
	}

}
