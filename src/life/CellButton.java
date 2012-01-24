package life;

import javax.swing.JButton;

/**
 * Class representing life cell in the UI
 */
public class CellButton extends JButton {

	private static final long serialVersionUID = -5754428060608107926L;
	private int xcord;
	private int ycord;

	public CellButton(int x, int y) {
		super();
		xcord = x;
		ycord = y;
	}

	public int getXcord() {
		return xcord;
	}

	public int getYcord() {
		return ycord;
	}
	
}