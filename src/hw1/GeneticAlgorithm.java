package hw1;

public class GeneticAlgorithm {
			
	public static void main(String[] args) {
		
		String crossover = "cross-over";
		String mutation = "mutation";
		
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

		while (!popEx1.maxFitnessAchieved()) {
			popEx1.crossover(pco);
			popEx1.printPopulation();
			if (!popEx1.maxFitnessAchieved()) {
				popEx1.mutate();
				if (popEx1.maxFitnessAchieved()){
					popEx1.setMaxFitMethod(mutation);
				}
			}
			else {
				popEx1.setMaxFitMethod(crossover);
			}
			
		}
		System.out.println(popEx1.getGenCount());
		System.out.println(popEx1.getMaxFitMethod());
	}
		
}
