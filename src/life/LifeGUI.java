package life;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import life.ICell.CellColor;

public class LifeGUI {

	private final JLabel turn = new JLabel("0", SwingConstants.CENTER);
	private final CellButton[][] cellGrid;

	private int boardSize;
	private ILifeModel lifeModel;

	public LifeGUI(ILifeModel model) {
		lifeModel = model;
		boardSize = model.getBoardSize();
		cellGrid = new CellButton[boardSize][boardSize];

		CellMouseAdapter mouseListen = new CellMouseAdapter();
		for (int i = 0; i < boardSize; ++i) {
			for (int j = 0; j < boardSize; ++j) {
				CellButton cellButton = new CellButton(i, j);
				cellButton.setBackground(Color.GRAY);
				cellButton.addMouseListener(mouseListen);
				cellGrid[i][j] = cellButton;
			}
		}

		try {
			// Set System L&F
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (UnsupportedLookAndFeelException e) {
			// handle exception
		} catch (ClassNotFoundException e) {
			// handle exception
		} catch (InstantiationException e) {
			// handle exception
		} catch (IllegalAccessException e) {
			// handle exception
		}
		JFrame.setDefaultLookAndFeelDecorated(true);
	}

	public void draw() {
		final JFrame frame = new JFrame();
		frame.setSize(300, 300);

		final Box buttonBox = new Box(BoxLayout.X_AXIS);
		final JButton run = new JButton("Run");
		final JButton step = new JButton("Step");
		final JButton blank = new JButton("Clear");
		final JButton quit = new JButton("Quit");
		buttonBox.add(blank);
		buttonBox.add(step);
		buttonBox.add(run);
		buttonBox.add(quit);
		buttonBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));

		final JSlider slide = new JSlider(JSlider.VERTICAL, 1, 10, 5);
		// Define the scale to be shown on the slide.
		slide.setMajorTickSpacing(2);
		slide.setMinorTickSpacing(1);
		slide.setPaintTicks(true);
		slide.setPaintLabels(true);
		// Create a border around the slider and its scale.
		slide.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		slide.addChangeListener(new ChangeValue());

		JPanel cellPane = new JPanel();
		cellPane.setLayout(new GridLayout(boardSize, boardSize));
		for (int i = 0; i < boardSize; ++i) {
			for (int j = 0; j < boardSize; ++j) {
				cellPane.add(cellGrid[i][j]);
			}
		}

		frame.add(cellPane, BorderLayout.CENTER);
		frame.add(buttonBox, BorderLayout.SOUTH);
		frame.add(slide, BorderLayout.EAST);
		frame.add(turn, BorderLayout.NORTH);

		frame.setVisible(true);
	}

	private Color getCellColor(int x, int y) {
		CellColor modelColor = lifeModel.getCellColor(x, y);
		switch (modelColor) {
		case Gray:
			return Color.GRAY;
		case Red:
			return Color.RED;
		case Green:
			return Color.GREEN;
		default:
			return Color.GRAY;
		}
	}

	private void updateCellsFromModel() {
		for (int i = 0; i < boardSize; ++i) {
			for (int j = 0; j < boardSize; ++j) {
				cellGrid[i][j].setBackground(getCellColor(i, j));
			}
		}
		turn.setText(Integer.toString(lifeModel.getTurn()));
	}

	class CellButton extends JButton {

		private static final long serialVersionUID = -5754428060608107926L;
		public int x;
		public int y;

		public CellButton(int x, int y) {
			super();
			this.x = x;
			this.y = y;
		}
	}

	class CellMouseAdapter extends MouseAdapter {

		public void mouseClicked(MouseEvent event) {
			CellButton sourceButton = (CellButton) event.getSource();

			int x = sourceButton.x;
			int y = sourceButton.y;

			if (SwingUtilities.isLeftMouseButton(event)) {
				sourceButton.setBackground(Color.RED);
				lifeModel.setCell(x, y, CellColor.Red);
			} else if (SwingUtilities.isRightMouseButton(event)) {
				sourceButton.setBackground(Color.GREEN);
				lifeModel.setCell(x, y, CellColor.Green);
			}
		}
	}

	class ChangeValue implements ChangeListener {

		public void stateChanged(final ChangeEvent expn) {
			final JSlider source = (JSlider) expn.getSource();
			if (!source.getValueIsAdjusting()) {
				lifeModel.setRate((int) source.getValue());
			}
		}
	}

}
