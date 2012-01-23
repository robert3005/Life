package life;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ContinousGrid implements IGrid {

	private ICell[][] grid;
	private int gridSize;
	
	public ContinousGrid(int size) {
		gridSize = size;
		grid = new LifeCell[gridSize][gridSize];
		for(int y = 0; y < gridSize; ++y) {
			for(int x = 0; x < gridSize; ++x) {
				grid[x][y] = new LifeCell();
			}
		}
	}
	
	@Override
	public ICell getCell(int x, int y) {
		return grid[mod(y,gridSize)][mod(x,gridSize)];
	}

	@Override
	public void setCell(int x, int y, ICell.CellColor newColor) {
		grid[y][x].setColor(newColor);
	}

	@Override
	public int getSize() {
		return gridSize;
	}
	
	@Override
	public Collection<ICell> getNeighbours(int x, int y) {
		ArrayList<ICell> neighbours = new ArrayList<ICell>();
		neighbours.add(getCell(x-1,y+1));
		neighbours.add(getCell(x-1,y));
		neighbours.add(getCell(x-1,y-1));
		neighbours.add(getCell(x,y-1));
		neighbours.add(getCell(x,y+1));
		neighbours.add(getCell(x+1,y-1));
		neighbours.add(getCell(x+1,y));
		neighbours.add(getCell(x+1,y+1));
		return neighbours;
	}

	private int mod(int x, int a) {
		while( x < 0 ) {
			x += a;
		}
		return x % a;
	}
	
}
