package life;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
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
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import life.ICell.CellColor;

public class LifeGUI {

	private final JLabel turn = new JLabel("0", SwingConstants.CENTER);
	final JButton run = new JButton("Run");
	final JButton step = new JButton("Step");
	final JButton blank = new JButton("Clear");
	final JButton quit = new JButton("Quit");
	private final CellButton[][] cellGrid;
	private static final int delayConstant = 2000;
	private final Timer runTimer;

	private int boardSize;
	private ILifeModel lifeModel;

	public LifeGUI(ILifeModel model) {
		lifeModel = model;
		boardSize = model.getBoardSize();
		cellGrid = new CellButton[boardSize][boardSize];
		runTimer = new Timer((int) (delayConstant / lifeModel.getRate()),
				new AbstractAction() {

					public void actionPerformed(ActionEvent e) {
						lifeModel.makeStep();
						updateCellsFromModel();
					}
				});

		CellMouseAdapter mouseListen = new CellMouseAdapter();
		for (int i = 0; i < boardSize; ++i) {
			for (int j = 0; j < boardSize; ++j) {
				CellButton cellButton = new CellButton(j, i);
				cellButton.setBackground(Color.GRAY);
				cellButton.addMouseListener(mouseListen);
				cellGrid[j][i] = cellButton;
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
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setSize(300, 300);

		ButtonClickListener clickListen = new ButtonClickListener();

		final Box buttonBox = new Box(BoxLayout.X_AXIS);

		run.addActionListener(clickListen);
		step.addActionListener(clickListen);
		blank.addActionListener(clickListen);
		quit.addActionListener(clickListen);

		buttonBox.add(blank);
		buttonBox.add(step);
		buttonBox.add(run);
		buttonBox.add(quit);
		buttonBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));

		final JSlider slide = new JSlider(JSlider.VERTICAL, 1, 10, 1);
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

	private void disableFields(boolean disable) {
		for (int i = 0; i < boardSize; ++i) {
			for (int j = 0; j < boardSize; ++j) {
				cellGrid[i][j].setEnabled(!disable);
			}
		}
		step.setEnabled(!disable);
		blank.setEnabled(!disable);
		quit.setEnabled(!disable);
		String runText = disable ? "Pause" : "Run";
		run.setText(runText);
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
				runTimer.setDelay((int) (delayConstant / lifeModel.getRate()));
			}
		}
	}

	class ButtonClickListener implements ActionListener {

		public void actionPerformed(final ActionEvent event) {

			// the button pressed
			final JButton sent = (JButton) event.getSource();
			// the button's label
			final String label = sent.getText();

			if (label.equals("Clear")) {
				for (int i = 0; i < boardSize; ++i) {
					for (int j = 0; j < boardSize; ++j) {
						lifeModel.setCell(i, j, CellColor.Gray);
					}
				}
				lifeModel.setTurn(0);
				updateCellsFromModel();
			} else if (label.equals("Step")) {
				lifeModel.makeStep();
				updateCellsFromModel();
			} else if (label.equals("Run")) {
				disableFields(true);
				runTimer.start();
			} else if (label.equals("Quit")) {
				System.exit(0);
			} else if (label.equals("Pause")) {
				runTimer.stop();
				disableFields(false);
			}
		}
	}

}
