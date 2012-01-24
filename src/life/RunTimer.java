package life;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Action to be executed repeatedly by the timer after it is launched
 */
public class RunTimer extends AbstractAction {

	private static final long serialVersionUID = 8989374966400334663L;
	private LifeModel model;

	public RunTimer(LifeModel model) {
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		model.makeStep();
	}

}