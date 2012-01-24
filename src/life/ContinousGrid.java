package life;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Grid implementation that wraps around
 */
public class ContinousGrid<T> {

	private T[][] grid;
	private int gridSize;

	@SuppressWarnings("unchecked")
	public ContinousGrid(Class<T[][]> clazz, int size) {
		gridSize = size;
		int[] length = { size, size };
		Class<?> coreType = clazz.getComponentType().getComponentType();
	    grid = clazz.cast((Array.newInstance(coreType, length)));  

		for (int y = 0; y < gridSize; ++y) {
			for (int x = 0; x < gridSize; ++x) {
				try {
					grid[x][y] = (T) coreType.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					// should never happen
					e.printStackTrace();
				}
			}
		}
	}

	public T getCell(int x, int y) {
		return grid[mod(y, gridSize)][mod(x, gridSize)];
	}

	public void setCell(int x, int y, T newCell) {
		grid[y][x] = newCell;
	}

	public int getSize() {
		return gridSize;
	}

	public Collection<T> getNeighbours(int x, int y) {
		ArrayList<T> neighbours = new ArrayList<T>();
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
