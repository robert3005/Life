package life;

public class Life {

	private ILifeModel model;
	private LifeGUI gui;
	
	public Life(ILifeModel model, LifeGUI gui) {
		this.model = model;
		this.gui = gui;
	}

	public void render() {
		gui.draw();
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
				ILifeModel lifeModel = new LifeModel(size);
				LifeGUI lifeGUI = new LifeGUI(lifeModel);
				Life lifeGame = new Life(lifeModel, lifeGUI);
				lifeGame.render();
			}
		});
	}
	
}
