package maze;

import java.util.Random;

public class Maze {
	
	protected final int ROWS = 60;
	protected final int COLS = 80;	
	private final float PERC_OBS = 30;
	private int rowI;
	private int colI;
	private int rowG;
	private int colG;
	protected char[][] maze;
	
	public Maze() {
		maze = new char[ROWS][COLS];	//Maze matrix
	}                                   //-----------//
                                        // Symbols meaning:
	public void generateMaze() { 		// '+' -> Path
		generateObstacles();			// ' ' -> free gap
		generateMainStates();           // '#' -> obstacle
		generateSpaces();           	// 'I' -> Initial state              
	} 									// 'G' -> Goal state
	
	//Configure the Obstacles(#)
	private void generateObstacles() {
		float obstacles = (PERC_OBS/100)*ROWS*COLS;
		int iRand, jRand;
		while(obstacles > 0) {
			iRand = (int)Math.floor(Math.random()*ROWS);
			jRand = (int)Math.floor(Math.random()*COLS);
			if(maze[iRand][jRand] != '#') {
				maze[iRand][jRand] = '#';
				--obstacles;
			}
		}
	}
	
	//Configure the Initial (I) and the Goal (G) State
	private void generateMainStates() {
		Random random= new Random();
		rowI = random.nextInt(ROWS);
        colI = random.nextInt(COLS);		
        rowG = random.nextInt(ROWS);
        colG = random.nextInt(COLS);
        // Check that initial and goal states are not obstacles
        if (maze[rowI][colI] == '#' || maze[rowG][colG] == '#')
            throw new RuntimeException("Initial or Goal state is an obstacle.");
        // Set initial and goal states in the maze
        maze[rowI][colI] = 'I';
        maze[rowG][colG] = 'G';
	}
	
	//Get Initial Row
	public int  getRowI() {
		return rowI;
	}
	
	//Get Initial Column
	public int  getColI() {
		return colI;
	}
	
	//Get Goal Row
	public int  getRowG() {
		return rowG;
	}
	
	//Get Goal Column
	public int  getColG() {
		return colG;
	}
	
	//Fill the rest of the matrix with spaces
	private void generateSpaces() {
		for(int i = 0; i < ROWS; ++i) {
			for(int j = 0; j < COLS; ++j) {
				if(maze[i][j] != 'I' && maze[i][j] != 'G' && maze[i][j] != '#')
					maze[i][j] = ' ';
			}
		}
	}
	
	//Show the matrix
	public void printMaze() {
		for(int i = 0; i < ROWS; ++i) {
			for(int j = 0; j < COLS; ++j) 
				System.out.print(maze[i][j]);
			System.out.println();
		}
	}
	
	public int getROWS() {
		return ROWS;
	}
	
	public int getCOLS() {
		return COLS;
	}
	
	public char charAt(int i, int j) {
		return maze[i][j];
	}
	
}
