package life;

import java.util.Observable;
import java.util.Observer;

import javax.swing.Timer;

/**
 * Initialises the game and creates and registers listeners for the UI
 * Observes slider listener and adjusts timer delay accordingly
 */
public class Life implements Observer {

	private final Timer runTimer;
	private static final int delayConstant = 2000;

	public Life(LifeModel model, LifeGUI gui) {
		model.addObserver(gui);
		
		ChangeValue changeListener = new ChangeValue();
		changeListener.addObserver(this);
		
		runTimer = new Timer(delayConstant, new RunTimer(model));
		runTimer.setInitialDelay(0);
		
		ButtonClickListener buttonClick = new ButtonClickListener(model, gui,
				runTimer);
		
		CellMouseAdapter cellClick = new CellMouseAdapter(model);
		
		gui.createClickableCells(cellClick);
		gui.setButtonListeners(buttonClick);
		gui.setSliderListener(changeListener);
	}

	private static int readSize(String[] args) {
		int size = 30;
		if (args.length > 0) {
			try {
				int userSize = Integer.parseInt(args[0]);
				return userSize >= 4 ? userSize : size;
			} catch (NumberFormatException except) {
				System.err.println("The argument (size) should be an integer");
			}
		}
		return size;
	}

	public static void main(String[] args) {
		final int size = readSize(args);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				LifeModel lifeModel = new LifeModel(size);
				LifeGUI lifeGUI = new LifeGUI(size);
				new Life(lifeModel, lifeGUI);
				lifeGUI.draw();
			}
		});
	}

	@Override
	public void update(Observable o, Object arg) {
		runTimer.setDelay((int) (delayConstant / ((Integer) arg)));
	}

}
