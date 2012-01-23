package life;

import java.util.Collection;

import life.ICell.CellColor;

public interface IGrid {

	ICell getCell(int x, int y);
	
	void setCell(int x, int y, CellColor newColor);
	
	Collection<ICell> getNeighbours(int x, int y);

	int getSize();
	
}
