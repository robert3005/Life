package life;

public class LifeCell {

	enum CellColor { Green, Red, Gray };
	
	private CellColor cellColor;

	public LifeCell() {
		cellColor = CellColor.Gray;
	}

	public void setColor(CellColor newColor) {
		cellColor = newColor;
	}

	public CellColor getColor() {
		return cellColor;
	}

	public boolean isAlive() {
		return cellColor != CellColor.Gray;
	}

}
