/**
 * 
 */
package jmb26_wmj1.chatapp.chatroom;

/**
 * @author jolisabrown
 *
 */
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.TitledBorder;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.function.Supplier;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;

public class VerticalScrollPanel extends JPanel {

	private static final long serialVersionUID = 5168191996461717724L;

	private Timer revalidateTimer;

	/**
	 * 
	 * @param revalidateInterval  Auto-revalidate interval in milliseconds.  revalidateInterval <= 0 means don't auto-revalidate. 
	 */
	public VerticalScrollPanel(int revalidateInterval) {

		initGUI();
		/*if (0<revalidateInterval) {
		 revalidateTimer = new Timer(50, (e) -> {
		  revalidate();
		 });
		 revalidateTimer.start();
		}*/
	}

	private void initGUI() {
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		//add(Box.createVerticalGlue());
	}

	public void addComponent(String title, Supplier<JComponent> compSupplier) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(new TitledBorder(title));
		Component newComponent = compSupplier.get();
		System.out.println("in special vertical add component");
		panel.add(newComponent, BorderLayout.CENTER);
		this.add(panel);
		this.revalidate(); // seems to work better than validate()
		this.repaint();
	}

	public void addComponent(String title, Component fac) {
		JPanel panel = new JPanel(new BorderLayout());
		//panel.setBorder(new TitledBorder(title));
		//Component newComponent = fac.get();
		System.out.println("in special vertical add component");
		panel.add(fac, BorderLayout.CENTER);
		this.add(panel);
		this.revalidate(); // seems to work better than validate()
		this.repaint();
	}

}
