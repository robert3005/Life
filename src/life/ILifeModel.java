package life;

import life.ICell.CellColor;

public interface ILifeModel {

	void makeStep();
	
	void setCell(int x, int y, CellColor newColor);

	CellColor getCellColor(int x, int y);
	
	int getBoardSize();

	int getRate();

	void setRate(int rate);

	int getTurn();

	void setTurn(int turn);

}