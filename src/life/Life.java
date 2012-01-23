package life;

import java.util.Observable;
import java.util.Observer;

import javax.swing.Timer;

public class Life implements Observer {

	private final Timer runTimer;
	private static final int delayConstant = 2000;

	public Life(LifeModel model, LifeGUI gui) {
		model.addObserver(this);
		model.addObserver(gui);
		ChangeValue changeListener = new ChangeValue();
		changeListener.addObserver(model);
		runTimer = new Timer(delayConstant, new RunTimer(model));
		ButtonClickListener buttonClick = new ButtonClickListener(model, gui,
				runTimer);
		CellMouseAdapter cellClick = new CellMouseAdapter(model);
		gui.createClickableCells(cellClick);
		gui.setButtonListeners(buttonClick);
		gui.setSliderListener(changeListener);
	}

	private static int readSize(String[] args) {
		int size = 40;
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
		LifeModel model = (LifeModel) o;
		runTimer.setDelay((int) (delayConstant / model.getRate()));
	}

}
