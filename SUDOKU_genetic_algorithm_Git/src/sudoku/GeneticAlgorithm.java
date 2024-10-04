package sudoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneticAlgorithm {
	
	private List<SudokuPopulation> poblaciones;
	private final int SIZE = 9;	
	private final int CREATIONS = 1000;	
	
	public SudokuIndividual solvedOne;
	
	private static final Random rnd = new Random();
	
	public GeneticAlgorithm(Sudoku s) {
		poblaciones = new ArrayList<>();
		poblaciones.add(new SudokuPopulation());
	}
	
	public void algorithm(Sudoku s) {
		
		int p = 0;	
		int populationSize = poblaciones.get(p).getPopulationSize();
		
		poblaciones.get(p).addIndividuals(s);
		poblaciones.get(p).sumUpAllFitness();
		// poblaciones.get(p).showPopulation(p);		
		
		while (isSolved(poblaciones.get(p)) == -1 && p < CREATIONS) {
			for (int i = 0; i < populationSize; ++i) {
				
				int randomMode = rnd.nextInt(10); // probabilities
				// int randomMode = 1;
				// int randomMode = 3;
				// int randomMode = 7;
				
				SudokuPopulation poblacion = new SudokuPopulation();
				poblaciones.add(poblacion);
				SudokuIndividual s0 = eligeIndividual(p);
			
				if (randomMode < 2) { // the individual remains the same
					poblaciones.get(p+1).getPopulation().add(s0);
					
				} else if (randomMode >= 2 && randomMode < 5) { // mutation is applied to the individual
					SudokuIndividual nuevo = new SudokuIndividual(s, s0);
					poblaciones.get(p+1).getPopulation().add(nuevo);
					
				} else if (randomMode >= 5) { // crossover is applied to the individual
					SudokuIndividual s2 = eligeIndividual(p);
					
					int row;
					int [][] matrix = new int[SIZE][SIZE];
					row = 1 + rnd.nextInt(SIZE);
					for (int c = 0; c < row; ++c)
						for (int j = 0; j < SIZE; ++j)
							matrix[c][j] = s0.getMatrix()[c][j];
				
					for (int l = row; l < SIZE; ++l)
						for (int m = 0; m < SIZE; ++m) 
							matrix[l][m] = s2.getMatrix()[l][m];

					SudokuIndividual ind1 = new SudokuIndividual(matrix);
					poblaciones.get(p+1).getPopulation().add(ind1);
					
					int [][] matrix2 = new int[SIZE][SIZE];
					for(int c = 0; c < row; ++c)
						for (int j = 0; j < SIZE; ++j)
							matrix2[c][j] = s2.getMatrix()[c][j];
							
					for (int l = row; l < SIZE; ++l)
						for(int m = 0; m < SIZE; ++m) 
							matrix2[l][m] = s0.getMatrix()[l][m];
							
					SudokuIndividual ind2 = new SudokuIndividual(matrix2);
					poblaciones.get(p+1).getPopulation().add(ind2);
				
					++i;
				}
			}
			++p;
			poblaciones.get(p).sumUpAllFitness();
			
			System.out.println("POPULATION " + p);
			System.out.println("Mean fitness of population " + p + ": " + getMeanFitness(poblaciones.get(p)));
			System.out.println("Biggest fitness of population " + p + ": " + getBiggestFitness(poblaciones.get(p)).getFitness());
			System.out.println();
		}
		
		int n = isSolved(poblaciones.get(p));
		if (n != -1) { // sudoku completely SOLVED
			System.out.println("SOLUCION CORECTA:");
			solvedOne = getBiggestFitness(poblaciones.get(p));
			getBiggestFitness(poblaciones.get(p)).printIndividual();
		}
	}
	
	private SudokuIndividual getBiggestFitness(SudokuPopulation onePopulation) {
		int bigger = onePopulation.getPopulation().get(0).getFitness();
		int biggerIndex = 0;
		for (int i = 1; i < onePopulation.getPopulationSize(); ++i) {
			if (onePopulation.getPopulation().get(i).getFitness() > bigger) {
				bigger = onePopulation.getPopulation().get(i).getFitness();
				biggerIndex = i;
			}
		}
		return onePopulation.getPopulation().get(biggerIndex);
	}
	
	private int getMeanFitness(SudokuPopulation onePopulation) {
		return (onePopulation.getTotalFitness() / onePopulation.getPopulationSize());
	}

	private int isSolved(SudokuPopulation sp) {
		boolean ok = false;
		int cnt = 0;
		while (!ok && cnt < sp.getPopulation().size()) {
			if (sp.getPopulation().get(cnt).isSolved())
				ok = true;	
			++cnt;
		}	
		if (!ok)
			cnt = -1;
		return cnt;
	}
	
	public SudokuIndividual eligeIndividual(int p) {
		int randomIndex;
		int length = poblaciones.get(p).getTotalFitness();
		int cnt = 0, i = 0;
		boolean encontrado = false;
		randomIndex = rnd.nextInt(length);
		while (!encontrado && i < poblaciones.get(p).getPopulationSize()) {
			cnt += poblaciones.get(p).getPopulation().get(i).getFitness();
			if (randomIndex < cnt)
				encontrado = true;
			if (!encontrado)
				++i;
		}
		if (i == poblaciones.get(p).getPopulationSize())
			--i;
		return poblaciones.get(p).getPopulation().get(i);
	}
	
	public SudokuIndividual getSolvedOne() {
		return solvedOne;
	}
	
}
