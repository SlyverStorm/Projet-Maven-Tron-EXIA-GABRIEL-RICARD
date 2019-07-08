package Exia2019.Tron.model;

public class LightSquare {
	
	private int x;
	
	private int y;
	

	
	private LightSquareType type;

	public LightSquareType getType() {
		return type;
	}

	public void setType(LightSquareType type) {
		this.type = type;
	}
	
	
	
	
	public LightSquare(final int squareX, final int squareY) {
		
		this.setX(squareX);
		this.setY(squareY);
		
		this.setType(LightSquareType.VOID);
	}
	
	public LightSquare(final LightSquareType squareType, final int squareX, final int squareY) {
		
		this.setX(squareX);
		this.setY(squareY);
		
		this.setType(squareType);
	}
	
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}



}
