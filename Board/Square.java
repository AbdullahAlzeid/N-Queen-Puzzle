package nQueens;

public class Square {
	//properties
	private int threats;
	private int placedQueen;
	private int row;
	private int col;

	// Constructor with additional row and col attributes
	public Square(int row, int col, int t, int pq) {
		this.row = row;
		this.col = col;
		this.threats = t;
		this.placedQueen = pq;
	}

	public int getRow() {
		return this.row;
	}

	public int getCol() {
		return this.col;
	}

	public void setThreats(int threats) {
		this.threats = threats;
	}

	public int getThreats() {
		return this.threats;
	}

	public void setPlacedQueen(int placedQueen) {
		this.placedQueen = placedQueen;
	}

	public int getPlacedQueen() {
		return this.placedQueen;
	}

	public String toString() {
		return (Integer.toString(this.placedQueen));
	}
}
