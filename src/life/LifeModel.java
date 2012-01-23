package life;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;

import life.ICell.CellColor;

public class LifeModel extends Observable implements Observer {

	private IGrid grid;
	private int turn;
	private int rate;

	public LifeModel(int size) {
		grid = new ContinousGrid(size);
		turn = 0;
		rate = 1;
	}


	public int getBoardSize() {
		return grid.getSize();
	}

	
	public void makeStep() {
		int size = getBoardSize();
		IGrid newGrid = new ContinousGrid(size);
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				int[] neighbourCounts = getColorCount(i, j);
				ICell currentCell = getCell(i, j);
				if (currentCell.isAlive()) {
					if (neighbourCounts[2] == 6 || neighbourCounts[2] == 5) {
						newGrid.setCell(i, j, currentCell.getColor());
					}
				} else {
					if (neighbourCounts[2] == 5) {
						CellColor newCellColor = neighbourCounts[0] > neighbourCounts[1] ? CellColor.Green
								: CellColor.Red;
						newGrid.setCell(i, j, newCellColor);
					}
				}
			}
		}
		grid = newGrid;
		++turn;
		setChanged();
		notifyObservers();
	}

	private int[] getColorCount(int x, int y) {
		Collection<ICell> neighbours = grid.getNeighbours(x, y);
		// neighbour cell color counts { Green, Red, Gray };
		int[] colorCounts = { 0, 0, 0 };
		for (ICell neighbour : neighbours) {
			switch (neighbour.getColor()) {
			case Green:
				++colorCounts[0];
				break;
			case Red:
				++colorCounts[1];
				break;
			case Gray:
				++colorCounts[2];
				break;
			}
		}
		return colorCounts;
	}

	
	public int getTurn() {
		return turn;
	}

	public void setRate(int rate) {
		this.rate = rate;
		setChanged();
		notifyObservers();
	}
	
	public int getRate() {
		return rate;
	}
	
	public void setCell(int x, int y, CellColor newColor) {
		grid.setCell(x, y, newColor);
	}

	
	public ICell getCell(int x, int y) {
		return grid.getCell(x, y);
	}

	
	public void clear() {
		int size = getBoardSize();
		for (int i = 0; i < size; ++i) {
			for (int j = 0; j < size; ++j) {
				setCell(i, j, CellColor.Gray);
			}
		}
		turn = 0;
		setChanged();
		notifyObservers();
	}


	@Override
	public void update(Observable o, Object arg) {
		rate = (int)arg;
		setChanged();
		notifyObservers();
	}
}