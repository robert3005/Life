package life;

import java.util.Collection;
import java.util.Observable;

import life.LifeCell.CellColour;

public class LifeModel extends Observable {

	private ContinousGrid<LifeCell> grid;
	private int turn;

	public LifeModel(int size) {
		grid = new ContinousGrid<LifeCell>(LifeCell[][].class, size);
		turn = 0;
	}


	public int getBoardSize() {
		return grid.getSize();
	}

	/**
	 * Applies life rules to the current state
	 */
	public void makeStep() {
		int size = getBoardSize();
		ContinousGrid<LifeCell> newGrid = new ContinousGrid<LifeCell>(LifeCell[][].class, size);
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				int[] neighbourCounts = getColourCount(i, j);
				LifeCell currentCell = getCell(i, j);
				if (currentCell.isAlive()) {
					if (neighbourCounts[2] == 6 || neighbourCounts[2] == 5) {
						newGrid.setCell(i, j, new LifeCell(currentCell.getColor()));
					}
				} else {
					if (neighbourCounts[2] == 5) {
						CellColour newCellColor = neighbourCounts[0] > neighbourCounts[1] ? CellColour.Green
								: CellColour.Red;
						newGrid.setCell(i, j, new LifeCell(newCellColor));
					}
				}
			}
		}
		grid = newGrid;
		++turn;
		setChanged();
		notifyObservers();
	}

	/**
	 * Returns number of neighbours having respective colours
	 * @param x x-coordinate
	 * @param y y-coordinate
	 * @return
	 */
	private int[] getColourCount(int x, int y) {
		Collection<LifeCell> neighbours = (Collection<LifeCell>) grid.getNeighbours(x, y);
		// neighbour cell colour counts { Green, Red, Gray };
		int[] colourCounts = { 0, 0, 0 };
		for (LifeCell neighbour : neighbours) {
			switch (neighbour.getColor()) {
			case Green:
				++colourCounts[0];
				break;
			case Red:
				++colourCounts[1];
				break;
			case Gray:
				++colourCounts[2];
				break;
			}
		}
		return colourCounts;
	}

	
	public int getTurn() {
		return turn;
	}

	public void setCell(int x, int y, LifeCell newCell) {
		grid.setCell(x, y, newCell);
	}

	
	public LifeCell getCell(int x, int y) {
		return grid.getCell(x, y);
	}

	
	public void clear() {
		int size = getBoardSize();
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				setCell(i, j, new LifeCell(CellColour.Gray));
			}
		}
		turn = 0;
		setChanged();
		notifyObservers();
	}

}