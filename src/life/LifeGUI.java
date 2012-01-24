package life;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeListener;

import life.LifeCell.CellColour;

/**
 * Creates UI of Life and registers listeners for objects
 */
public class LifeGUI implements Observer {

	private final JLabel turn = new JLabel("0", SwingConstants.CENTER);
	private final JButton run = new JButton("Run");
	private final JButton step = new JButton("Step");
	private final JButton blank = new JButton("Clear");
	private final JButton quit = new JButton("Quit");
	private final CellButton[][] cellGrid;
	private final JSlider slide = new JSlider(JSlider.VERTICAL, 1, 10, 1);

	private int boardSize;

	public LifeGUI(int size) {
		boardSize = size;
		cellGrid = new CellButton[boardSize][boardSize];

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
		frame.setSize(1280, 800);

		final Box buttonBox = new Box(BoxLayout.X_AXIS);

		buttonBox.add(blank);
		buttonBox.add(step);
		buttonBox.add(run);
		buttonBox.add(quit);
		buttonBox.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 0));

		// Define the scale to be shown on the slide.
		slide.setMajorTickSpacing(2);
		slide.setMinorTickSpacing(1);
		slide.setPaintTicks(true);
		slide.setPaintLabels(true);
		// Create a border around the slider and its scale.
		slide.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

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

	private Color translateCellColor(CellColour modelColor) {
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

	public void disableFields(boolean disable) {
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

	public void createClickableCells(CellMouseAdapter listen) {
		for (int i = 0; i < boardSize; ++i) {
			for (int j = 0; j < boardSize; ++j) {
				CellButton cellButton = new CellButton(j, i);
				cellButton.setBackground(Color.GRAY);
				cellButton.addMouseListener(listen);
				cellGrid[j][i] = cellButton;
			}
		}
	}

	public void setButtonListeners(ActionListener listen) {
		run.addActionListener(listen);
		step.addActionListener(listen);
		blank.addActionListener(listen);
		quit.addActionListener(listen);
	}

	public void setSliderListener(ChangeListener listen) {
		slide.addChangeListener(listen);
	}

	@Override
	public void update(Observable o, Object arg) {
		LifeModel model = (LifeModel) o;
		for (int i = 0; i < boardSize; ++i) {
			for (int j = 0; j < boardSize; ++j) {
				cellGrid[i][j].setBackground(translateCellColor(model.getCell(
						i, j).getColor()));
			}
		}
		turn.setText(Integer.toString(model.getTurn()));
	}

}
