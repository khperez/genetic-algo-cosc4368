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
	  
		Population[] popEx = new Population[20];
		for(int i = 0 ; i < popEx.length ; i++)
		{
			popEx[i] = new Population(0.7);
			popEx[i].printPopulation();
		
			while (!popEx[i].maxFitnessAchieved()) {
				popEx[i].crossover();
				if (!popEx[i].maxFitnessAchieved()) {
					popEx[i].mutate();
					if (popEx[i].maxFitnessAchieved()){
						popEx[i].setMaxFitMethod(mutation);
					}
				}
				else {
					popEx[i].setMaxFitMethod(crossover);
				}
				
			}
			System.out.println(popEx[i].getGenCount());
			popEx[i].setGenCountAvg(popEx[i].getGenCount());
			popEx[i].getGenCountAvg();
			System.out.println(popEx[i].getMaxFitMethod()+"\n\n\n");
		}
		
		/* 
		 * Experiment 2
		 * Perform experiment 1 with same initial population for each run:
		 * i) pco = 0.3
		 * ii) pco = 0.5
		 * iii) pco = 0.9
		 * iv) pco = 0
		 * 
		 */
		
		int seedVal = (int) System.currentTimeMillis();
		Population[] populations = new Population[20];
		double[] pco = {0.3, 0.5, 0.9, 0};
		for(int i = 0 ; i < pco.length ; i++)
		{
			populations[i] = new Population(seedVal);
			populations[i].setPco(pco[i]);
			System.out.println("====PCO " + populations[i].getPco() + "====");
			
			populations[i].printPopulation();
		
			while (!populations[i].maxFitnessAchieved()) {
				populations[i].crossover();
				if (!populations[i].maxFitnessAchieved()) {
					populations[i].mutate();
					if (populations[i].maxFitnessAchieved()){
						populations[i].setMaxFitMethod(mutation);
					}
				}
				else {
					populations[i].setMaxFitMethod(crossover);
				}
				
			}
			System.out.println(populations[i].getGenCount());
			populations[i].setGenCountAvg(populations[i].getGenCount());
			populations[i].getGenCountAvg();
			System.out.println(populations[i].getMaxFitMethod()+"\n\n\n");
		}
	}

	
	
}
