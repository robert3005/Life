package life;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.Timer;

/**
 * Listener which is responsible for handling 4 action buttons
 */
public class ButtonClickListener implements ActionListener {

	private LifeModel model;
	private LifeGUI gui;
	private Timer timer;

	public ButtonClickListener(LifeModel model, LifeGUI gui, Timer timer) {
		this.model = model;
		this.gui = gui;
		this.timer = timer;
	}

	public void actionPerformed(final ActionEvent event) {
		
		// Get the source button
		JButton sent = (JButton) event.getSource();
		String label = sent.getText();

		if (label.equals("Clear")) {
			model.clear();
		} else if (label.equals("Step")) {
			model.makeStep();
		} else if (label.equals("Run")) {
			gui.disableFields(true);
			timer.start();
		} else if (label.equals("Quit")) {
			System.exit(0);
		} else if (label.equals("Pause")) {
			timer.stop();
			gui.disableFields(false);
		}
	}
}