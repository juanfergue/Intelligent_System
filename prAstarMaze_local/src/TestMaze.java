import java.io.FileWriter;
import java.io.IOException;

import maze.*;

public class TestMaze {

	public static void main(String[] args) throws IOException {

		Astar maze = new Astar();
		maze.generateMaze();
		solve(maze);
	
	}
	
	private static void solve(Astar maze) throws IOException {
		maze.printPath();
		if(!maze.isSolved())
			System.out.println("\nRESULT: NO possible path found ");
		else
			System.out.println("\nRESULT: optimal path FOUND ");
		print(maze); // it prints the output on a file: output.txt
	}
	
	private static void print(Astar maze) throws IOException {
		try (FileWriter output = new FileWriter ("C:/Users/juanm/Documents/GitHub/IntelligentSystems/prAstarMaze_local/output.txt")){
			char val;
			for(int i = 0; i < maze.getROWS(); ++i) {
				for(int j = 0; j < maze.getCOLS(); ++j) {
					val = maze.charAt(i, j);
					output.write(val);	
				}
				output.write('\n');
			}
		}
	}
	
}


