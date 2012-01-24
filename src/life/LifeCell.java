package life;

/**
 * Class representing cell in the model
 */
public class LifeCell {

	enum CellColour { Green, Red, Gray };
	
	private CellColour cellColour;

	public LifeCell(CellColour colour) {
		cellColour = colour;
	}

	public LifeCell() {
		cellColour = CellColour.Gray;
	}

	public void setColor(CellColour newColor) {
		cellColour = newColor;
	}

	public CellColour getColor() {
		return cellColour;
	}

	public boolean isAlive() {
		return cellColour != CellColour.Gray;
	}

}
