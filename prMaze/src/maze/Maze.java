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
		generateMainStates();			// 'I' -> Initial state
		setSpaces();
	}                                       // 'G' -> Goal state
	
	private void generateObstacles() {
		float obstacles = (PERC_OBS/100)*ROWS*COLS;
		while(obstacles > 0) {
			int iRand = (int)Math.floor(Math.random()*ROWS);
			int jRand = (int)Math.floor(Math.random()*COLS);
			maze[iRand][jRand] = '#';
			if(maze[iRand][jRand] == '#')	
				--obstacles;  
		}
	}
	
	private void generateMainStates() {
		boolean setI = false;
		boolean setG = false;
		while(!setI || !setG) {
			int iRand = (int)Math.floor(Math.random()*ROWS);
			int jRand = (int)Math.floor(Math.random()*COLS);
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
	
	public void printMaze() {
		for(int i = 0; i < ROWS; ++i) {
			for(int j = 0; j < COLS; ++j) 
				System.out.print(maze[i][j]);
			System.out.println();
		}
	}
	protected void setSpaces() { //colocamos espacios
		for(int i=0;i<ROWS;i++) {
			for(int j=0;j<COLS;j++) {
				if(maze[i][j]!='G' && maze[i][j]!='I' && maze[i][j]!='#') { // comprobamos que no este ocupado el hueco
					maze[i][j]=' ';
				}
			}
		}
	}
}
