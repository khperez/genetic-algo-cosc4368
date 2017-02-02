package hw1;
import java.util.Random;
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
	    int seedVal = (int) System.currentTimeMillis();
		Population[] popEx = new Population[20];
		double[] pco = {0.7,0.3,0.5,0.9,0};
		for(int i = 0 ; i < popEx.length ; i++)
		{
			popEx[i] = new Population(seedVal);
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
			popEx[i].setGenCountAvg(popEx[i].getGenCount());
			popEx[i].getGenCountAvg();
			System.out.println(popEx[i].getMaxFitMethod()+"\n\n\n");
		}
	}

	
	
}
