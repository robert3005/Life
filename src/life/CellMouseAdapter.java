package life;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;

import life.LifeCell.CellColor;

public class CellMouseAdapter extends MouseAdapter {

	private LifeModel model;

	public CellMouseAdapter(LifeModel model) {
		this.model = model;
	}

	public void mouseClicked(MouseEvent event) {
		CellButton sourceButton = (CellButton) event.getSource();

		int x = sourceButton.getXcord();
		int y = sourceButton.getYcord();
		if (sourceButton.isEnabled()) {
			if (SwingUtilities.isLeftMouseButton(event)) {
				sourceButton.setBackground(Color.RED);
				model.setCell(x, y, CellColor.Red);
			} else if (SwingUtilities.isRightMouseButton(event)) {
				sourceButton.setBackground(Color.GREEN);
				model.setCell(x, y, CellColor.Green);
			}
		}
	}
}
