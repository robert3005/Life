package life;

public interface ICell {

	enum CellColor { Green, Red, Gray };
	
	public void setColor(CellColor newColor);
	
	public CellColor getColor();
	
	public boolean isAlive();
	
}
