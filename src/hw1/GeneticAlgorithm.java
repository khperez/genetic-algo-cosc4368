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
		double[] pco = {0.7,0.3,0.5,0.9,0};
		for(int i = 0 ; i < popEx.length ; i++)
		{
			popEx[i] = new Population();
			popEx[i].printPopulation();
		
			while (!popEx[i].maxFitnessAchieved()) {
				popEx[i].crossover(pco[1]);
//				popEx[i].printPopulation();
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
			System.out.println(popEx[i].getMaxFitMethod()+"\n\n\n");
			popEx[i].getGenCountAvg(popEx[i].getGenCount());
		}
		
		/*
		 * Experiment 2
		 * Run genetic algorithm with pco:
		 * i) 0.3
		 * ii) 0.5
		 * iii) 0.9
		 * iv) 0
		 * 
		 */
		
		double[] pcoList = {0.3, 0.5, 0.9, 0};
		Population popEx2 = new Population();
		
		Population[] populations = new Population[pcoList.length];
		
		for (int i = 0; i < pcoList.length; i++) {
			popEx2.setPco(pcoList[i]);
			populations[i] = popEx2;
			populations[i].printPopulation();
			System.out.println("pco value: " + populations[i].getPco());
		}
	}
		
}
