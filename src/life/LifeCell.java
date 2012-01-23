package life;

public class LifeCell implements ICell {

	private CellColor cellColor;

	public LifeCell() {
		cellColor = ICell.CellColor.Gray;
	}

	@Override
	public void setColor(CellColor newColor) {
		cellColor = newColor;
	}

	@Override
	public CellColor getColor() {
		return cellColor;
	}

	@Override
	public boolean isAlive() {
		return cellColor != CellColor.Gray;
	}

}
