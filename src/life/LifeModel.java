package life;

import java.util.Collection;

import life.ICell.CellColor;

public class LifeModel implements ILifeModel {

	private IGrid grid;
	private int rate;
	private int turn;

	public LifeModel(int size) {
		grid = new ContinousGrid(size);
		rate = 1;
		turn = 0;
	}

	@Override
	public int getBoardSize() {
		return grid.getSize();
	}

	@Override
	public void makeStep() {
		IGrid newGrid = new ContinousGrid(grid.getSize());
		for (int i = 0; i < grid.getSize(); ++i) {
			for (int j = 0; j < grid.getSize(); ++j) {
				int[] neighbourCounts = getColorCount(i, j);
				CellColor cellColour = grid.getCell(i, j).getColor();
				if (cellColour != CellColor.Gray) {
					if (neighbourCounts[2] == 6 || neighbourCounts[2] == 5) {
						newGrid.setCell(i, j, grid.getCell(i, j).getColor());
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

	@Override
	public int getRate() {
		return rate;
	}

	@Override
	public void setRate(int rate) {
		this.rate = rate;
	}

	@Override
	public int getTurn() {
		return turn;
	}

	@Override
	public void setTurn(int turn) {
		this.turn = turn;
	}

	@Override
	public void setCell(int x, int y, CellColor newColor) {
		grid.setCell(x, y, newColor);
	}

	@Override
	public CellColor getCellColor(int x, int y) {
		return grid.getCell(x, y).getColor();
	}

}
