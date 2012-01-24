package life;

import java.util.ArrayList;
import java.util.Collection;

import life.LifeCell.CellColor;

public class ContinousGrid {

	private LifeCell[][] grid;
	private int gridSize;

	public ContinousGrid(int size) {
		gridSize = size;
		grid = new LifeCell[gridSize][gridSize];
		for (int y = 0; y < gridSize; ++y) {
			for (int x = 0; x < gridSize; ++x) {
				grid[x][y] = new LifeCell();
			}
		}
	}

	public LifeCell getCell(int x, int y) {
		return grid[mod(y, gridSize)][mod(x, gridSize)];
	}

	public void setCell(int x, int y, CellColor newColor) {
		grid[y][x].setColor(newColor);
	}

	public int getSize() {
		return gridSize;
	}

	public Collection<LifeCell> getNeighbours(int x, int y) {
		ArrayList<LifeCell> neighbours = new ArrayList<LifeCell>();
		neighbours.add(getCell(x - 1, y + 1));
		neighbours.add(getCell(x - 1, y));
		neighbours.add(getCell(x - 1, y - 1));
		neighbours.add(getCell(x, y - 1));
		neighbours.add(getCell(x, y + 1));
		neighbours.add(getCell(x + 1, y - 1));
		neighbours.add(getCell(x + 1, y));
		neighbours.add(getCell(x + 1, y + 1));
		return neighbours;
	}

	private int mod(int x, int a) {
		while (x < 0) {
			x += a;
		}
		return x % a;
	}

}
