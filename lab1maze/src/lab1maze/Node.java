package lab1maze;

public class Node {
	private int num_row;
	private int num_col;
	private int father_col;
	private int father_row;
	private int g;  // numero de pasos dados desde i
	private double f;
	
	public Node (int r, int c, int g) {
		this.num_row = r;
		this.num_col = c;
		this.g = g;
	}

	
	public int getNum_row() {
		return num_row;
	}


	public int getNum_col() {
		return num_col;
	}
	
	public int getG() {
		return g;
	}

	public double getF() {
		return f;
	}
	
	public void setF(double f)	{
		this.f = f;
	}


	public void setNum_row(int num_row) {
		this.num_row = num_row;
	}


	public void setNum_col(int num_col) {
		this.num_col = num_col;
	}


	public void setG(int g) {
		this.g = g;
	}
	
	public String toString() {
		return "[" + num_row + ", " + num_col + ", " + g + "]";
	}
	
	
}
