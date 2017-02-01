package hw1;

public class GeneticAlgorithm {
			
	public static void main(String[] args) {
		
		/* 
		 * Experiment 1
		 * Run genetic algorithm 20 times at pco = 0.7
		 * Print average number of generations to reach max
		 * fitness value.
		 *  
		 */
		
		Population popEx1 = new Population();
		
		double pco = 0.7;
		
		popEx1.printPopulation();
		
		for (int i = 1; i < 20; i++) {
			popEx1.crossover(pco);
			popEx1.printPopulation();
		}
		
		System.out.println(popEx1.getGenCount());
		
	}
		
}
