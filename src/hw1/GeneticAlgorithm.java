package hw1;

import org.jfree.ui.RefineryUtilities;

public class GeneticAlgorithm {
			
	public static void main(String[] args) {
		
		String crossover = "cross-over";
		String mutation = "mutation";
		int counter = 0;
		int total = 0;
		int[] popCount = new int[20];
		int[] pcoCount = new int[4];
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
			popEx[i].setGenCount(0);
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
			counter++;
			total += popEx[i].getGenCount();
			System.out.println("Average Count = " + total/counter);
			System.out.println(popEx[i].getMaxFitMethod()+"\n\n\n");
			popCount[i] = popEx[i].getGenCount();
		}
		
		PopulationPlot popChart = new PopulationPlot(
				"Generations vs Chromosomes", 
				"Number of Generations vs Chromosomes", popCount);
		
		popChart.pack();
	    popChart.setVisible(true);
	   
		/* 
		 * Experiment 2
		 * Perform experiment 1 with same initial population for each run:
		 * i) pco = 0.3
		 * ii) pco = 0.5
		 * iii) pco = 0.9
		 * iv) pco = 0
		 * 
		 */
		counter = 0;
		total = 0;
		int j = 0;
		int pcoCounters = 0;
		int[] avgPco = new int[4];
		int seedVal = (int) System.currentTimeMillis();
		Population[] populations = new Population[20];
		double[] pco = {0.3, 0.5, 0.9, 0};
		for(int pcoLoop = 1 ; pcoLoop < 21 ; pcoLoop++)
		{
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
				counter++;
				total += popEx[i].getGenCount();
				System.out.println("Average Count = " + total/counter);
				System.out.println(populations[i].getMaxFitMethod()+"\n\n\n");
				pcoCount[i] = popEx[i].getGenCount();
				pcoCounters += pcoCount[i];
			}
			System.out.println("j:" + j + " pcoLoop:" + pcoLoop);
			if(pcoLoop%5 == 0)
			{
				avgPco[j] = pcoCounters/20;
				++j;
			}
		}
		for(int a = 0 ; a < 4 ; a++)
		{
			System.out.println(avgPco[a]);
		}
		PopulationPlot pcoChart = new PopulationPlot(
				"Generations vs PCO", 
				"Number of Generations vs PCO", pco, pcoCount);
		
		pcoChart.pack();
	    RefineryUtilities.centerFrameOnScreen(pcoChart);
	    pcoChart.setVisible(true);
	}

	
	
}
