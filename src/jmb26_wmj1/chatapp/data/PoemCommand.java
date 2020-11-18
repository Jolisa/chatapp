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
 * @author jolisabrown
 *
 */
public class PoemCommand extends AMessageAlgoCmd<IPoemMessage> {

	/**
	 * Generated UID
	 */
	private static final long serialVersionUID = -3482482737515592157L;

	/**
	 * constructor for poem command
	 */
	public PoemCommand() {
	}

	@Override
	public Void apply(IDataPacketID index, ChatAppDataPacket<IPoemMessage> host, Void... params) {
		// TODO Auto-generated method stub
		System.out.println("into the apply method of poemCommand");
		System.out.println(host.getData().getPoemComponent());
		//m2vAdpt.addComponentImage(host.getData().getImageComponent());

		Supplier<JComponent> bigComp = () -> host.getData().getPoemComponent();

		this.c2mAdapter.displayComponent(bigComp, host.getData().getComponentTitle());

		return null;
	}

}
