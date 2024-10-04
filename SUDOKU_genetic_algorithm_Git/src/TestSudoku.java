import java.io.FileWriter;
import java.io.IOException;

import sudoku.*;

public class TestSudoku {
	
	private final static int SIZE = 9;

	public static void main(String[] args) throws IOException {

		Sudoku s = new Sudoku(1);
		
		s.printSudoku();
		s.printMissingValues();
		System.out.println();
		GeneticAlgorithm algorithm = new GeneticAlgorithm(s);
		algorithm.algorithm(s);
		try {
			SudokuIndividual solution = algorithm.getSolvedOne();
			print(solution, solution.getFitness());
		} catch (Exception e) {
			System.out.println("No solution found.");
		}
		
	}
	
	private static void print(SudokuIndividual s, int fitness) throws IOException {
		try (FileWriter output = new FileWriter ("output.txt")) {
			char val;
			output.write(" ----------------------- " + "\n");
			for(int i = 0; i < SIZE; ++i) {
				for(int j = 0; j < SIZE; ++j) {
					val = s.charAt(i, j);
					if(j == 0)
						output.write("| ");
					output.write(val + " ");
					if((j+1) % 3 == 0)
						output.write("| ");	
				}
				output.write("\n");
				if((i+1) % 3 == 0)
					output.write(" ----------------------- " + "\n");
			}
			output.write("Fitness: " + fitness);
			System.out.println("-> output.txt generated");
		}
	}

}