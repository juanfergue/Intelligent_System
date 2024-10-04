package lab1maze;

public class TestMaze {

	public static void main(String[] args) {
		Maze maze = new Maze();
		maze.showMatrix();
		if (maze.haveSolution() == true) {
			System.out.println("tiene solucion");
		} else {
			System.out.println("No tiene solucion");
		}
		
		System.out.println(maze.showOpenAndClose());
		maze.showMatrix();

	}

}
