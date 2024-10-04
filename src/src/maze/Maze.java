package maze;

public class Maze {
	
	private final int ROWS = 60;
	private final int COLS = 80;	
	private final float PERC_OBS = 30;
	private char[][] maze;
	
	public Maze() {
		maze = new char[ROWS][COLS];    //Maze matrix
	}                                       //-----------//
                                                // Symbols meaning:
	public void generateMaze() {            // ' ' -> free gap
		generateObstacles();            // '#' -> obstacle
		generateMainStates();// 'I' -> Initial state // 'G' -> Goal state
		generateSpaces();
	}                                     
	
	private void generateObstacles() {
		float obstacles = (PERC_OBS/100)*ROWS*COLS;
		int iRand,jRand;
		while(obstacles > 0) {
			iRand = (int)Math.floor(Math.random()*ROWS);
			jRand = (int)Math.floor(Math.random()*COLS);
			if(maze[iRand][jRand] != '#')
				maze[iRand][jRand] = '#';
				--obstacles;
		}
	}
	
	private void generateMainStates() {
		boolean setI = false,setG = false;
		int iRand,jRand;
		while(!setI || !setG) {
			iRand = (int)Math.floor(Math.random()*ROWS);
			jRand = (int)Math.floor(Math.random()*COLS);
			if(maze[iRand][jRand] != '#') {
				if(!setI && maze[iRand][jRand] != 'G') {
					maze[iRand][jRand] = 'I';
					setI = true;
				} else if(!setG && maze[iRand][jRand] != 'I') {
					maze[iRand][jRand] = 'G';
					setG = true;
				}
			}
		}
	}
	
	private void generateSpaces() {
		for(int i = 0; i < ROWS; ++i) {
			for(int j = 0; j < COLS; ++j) {
				if(maze[i][j] != 'I' && maze[i][j] != 'G' && maze[i][j] != '#')
					maze[i][j] = ' ';
			}
		}
	}
	
	public void printMaze() {
		for(int i = 0; i < ROWS; ++i) {
			for(int j = 0; j < COLS; ++j) 
				System.out.print(maze[i][j]);
			System.out.println();
		}
	}
	
}
