package sudoku;

import java.util.ArrayList;

public class Sudoku {
	
	
	private int[][] board;
	private ArrayList<Integer>[] missingValues;
	private final int SIZE = 9;
	
	public Sudoku(int op) {
		board = createMatrix(op);
		missingValues = getValues();
	}
	
	private int[][] createMatrix(int op) {
		int[] array;
		int [][] matrix = new int[SIZE][SIZE];
		
		Creator c = new Creator();
		array = c.create(op);
		
		int cnt = 0;
		for(int i = 0; i < SIZE; ++i) {
			for(int j = 0; j < SIZE; ++j) {
				matrix[i][j] = array[cnt];
				++cnt;
			}
		}
		
		return matrix;
	}
	
	public ArrayList<Integer>[] getMissingValues() {
		return missingValues;
	}
	
	public int[][] getBoard() {
		return board;
	}

	private ArrayList<Integer>[] getValues() { // 
		int[][] other = new int[SIZE][SIZE];
		for (int i = 0; i < SIZE; ++i) {
			for (int j = 0; j < SIZE; ++j) {
				other[i][j] = j+1;
			}
		}
		int v;
		for (int i = 0; i < SIZE; ++i) {
			for (int j = 0; j < SIZE; ++j) {
				v = board[i][j];
				if (v != 0) 
					other[i][v-1] = 0;
			}
		}
		ArrayList<Integer>[] arrayOfMissings = new ArrayList[SIZE];
		for (int i = 0; i < SIZE; i++) {
		    arrayOfMissings[i] = new ArrayList<Integer>();
		}
		for (int i=0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				if (other[i][j] != 0) {
					arrayOfMissings[i].add(other[i][j]);
				}
			}
		}
		return arrayOfMissings;
	}	
	
	public void printMissingValues() {
		System.out.println();
		System.out.println("Missing values: ");
		System.out.println("--------------- ");
		for(int i = 0; i < SIZE; ++i) {
			System.out.print("Row " + i + ": { ");
			for(int j = 0; j < missingValues[i].size(); ++j) {
				System.out.printf(missingValues[i].get(j)+" ");
			}
			System.out.println("}");
		}
	}
	
	public void printSudoku() {
		System.out.println(" ----------------------- ");
		for(int i = 0; i < SIZE; ++i) {
			for(int j = 0; j < SIZE; ++j) {
				if(j == 0)
					System.out.print("| ");
				System.out.print(board[i][j] + " ");
				if((j+1) % 3 == 0)
					System.out.print("| ");
			}
			System.out.println();
			if((i+1) % 3 == 0)
				System.out.println(" ----------------------- ");				
		}
	}
	
}
