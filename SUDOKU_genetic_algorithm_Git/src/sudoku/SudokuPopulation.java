package sudoku;

import java.util.ArrayList;
import java.util.List;

public class SudokuPopulation {
	
	private final int POPULATION_SIZE = 1000;
	private List<SudokuIndividual> population; // Population of Sudokus that have in common the assigned cells with numbers and each one a different solution proposal 
	private int totalFitness = 0;
	
	public SudokuPopulation() {
		population = new ArrayList<>();		
	}
	
	public void addIndividuals(Sudoku s) {
		for (int i = 0; i < POPULATION_SIZE; ++i)
			population.add(new SudokuIndividual(s));
	}
	
	public List<SudokuIndividual> getPopulation() {
		return population;
	}
	
	public void sumUpAllFitness() {
		for (int i = 0; i < POPULATION_SIZE; ++i)
			totalFitness += population.get(i).getFitness();
	}
	
	public int getTotalFitness(){
		return totalFitness;
	}
	public int getPopulationSize(){
		return POPULATION_SIZE;
	}
	
	public void showPopulation(int n) {
		System.out.println("/-/-/-/-/-/-/-/-/ POPULATION " + n + " /-/-/-/-/-/-/-/-/");
		for (int i = 0; i < POPULATION_SIZE; ++i) {
			System.out.println();
			System.out.println("Individual " + (i+1));
			population.get(i).printIndividual();	
		}
		System.out.println();
		System.out.println("/-/-/-/-/-/-/-/ END POPULATION " + n + " /-/-/-/-/-/-/-/");
		System.out.println();
	}
}
