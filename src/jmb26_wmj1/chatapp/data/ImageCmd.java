/**
 * 
 */
package jmb26_wmj1.chatapp.data;

import java.util.function.Supplier;

import javax.swing.JComponent;
import common.AMessageAlgoCmd;
import common.ChatAppDataPacket;
import provided.datapacket.IDataPacketID;

/**
 * @author wj
 *
 */
public class ImageCmd extends AMessageAlgoCmd<IImageMessage> {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = 1504126955415236434L;

	/**
	 * constructor for imageCmd
	 */
	public ImageCmd() {

	}

	@Override
	public Void apply(IDataPacketID index, ChatAppDataPacket<IImageMessage> host, Void... params) {
		// TODO Auto-generated method stub

		//m2vAdpt.addComponentImage(host.getData().getImageComponent());

		Supplier<JComponent> bigComp = () -> host.getData().getImageComponent();

		this.c2mAdapter.displayComponent(bigComp, "image");
		//c2mAdpt.displayComponent(bigComp, "image");

		return null;
	}

}
