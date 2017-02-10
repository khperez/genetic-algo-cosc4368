package hw1;

import org.jfree.ui.RefineryUtilities;

public class GeneticAlgorithm {
			
	public static void main(String[] args) {
		
		int[] popCount = new int[20]; 

		/* 
		 * Experiment 1
		 * Run genetic algorithm 20 times at pco = 0.7
		 * Print average number of generations to reach max
		 * fitness value.
		 *  
		 */
		System.out.println("Experiment 1\n");
		
		//Create 20 randomly-generated populations
		Population[] popEx = new Population[20];
		for(int i = 0 ; i < popEx.length ; i++){
			popEx[i] = new Population(0.7);
			popEx[i].printPopulation();
			// Perform cross-over and mutation until a fitness of 10 is discovered
			while (!popEx[i].maxFitnessAchieved()) {
				popEx[i].crossover();
				if (!popEx[i].maxFitnessAchieved()) {
					popEx[i].mutate();
				}
				popEx[i].printFitnessValues();
			}
			//# of generations until a fitness level of 10 was achieved.
			System.out.println("1010101010 discovered at generation " + popEx[i].getGenCount()); 
			//Used for PopulationPlot, getting # of generations until max.
			popCount[i] = popEx[i].getGenCount(); 
		}
		
		System.out.print("Average gen count for pco " + popEx[0].getPco() + ": ");
		System.out.println(getAverageGenCount(popCount));
		
		//popCount[] passed to generate plot for experiment 1.
		PopulationPlot popChart = new PopulationPlot(
				"Experiment 1", 
				"Experiment 1", popCount);
		
		popChart.pack();
	    popChart.setVisible(true);
	   
		/* 
		 * Experiment 2
		 * Perform experiment 1 with same initial population for each run:
		 * i) pco = 0.3
		 * ii) pco = 0.5
		 * iii) pco = 0.9
		 * iv) pco = 0 (no cross-over)
		 * 
		 */

	    //seedVal is used to ensure the initial population is always used.
		int seedVal = (int) System.currentTimeMillis();
		double[] pco = {0.3, 0.5, 0.9, 0};
		int totalRuns = 20;
		int[][] genCountPerRun = new int[pco.length][totalRuns];
		int[] avgGenPco = new int[pco.length];
		Population[] populations = new Population[4];
		
		for (int i = 0 ; i < pco.length ; i++) {
			System.out.println("====PCO " + pco[i] + "====");
			for (int runCount = 0; runCount < totalRuns; runCount++) {
				//Single seed ensures Random # generator will produce identical initial population
				populations[i] = new Population(seedVal);
				populations[i].setPco(pco[i]);
				if (runCount == 0) {
					populations[i].printPopulation();
				}
				// Perform cross-over and mutation until a fitness of 10 is discovered
				while (!populations[i].maxFitnessAchieved()) {
					// Perform cross-over on populations with pco > 0
					if (pco[i] != 0) {
						populations[i].crossover();
					}
					else {
						populations[i].setGenCount(populations[i].getGenCount() + 1);
					}
					if (!populations[i].maxFitnessAchieved()) {
						populations[i].mutate();
	
					}
				}
				genCountPerRun[i][runCount] = populations[i].getGenCount();
			}
			//# of generations until a fitness level of 10 was achieved.
			System.out.println("1010101010 discovered at generation " + populations[i].getGenCount()); 
		}
		//Array of PCO Generator values to be used in plot
		for (int i = 0; i < pco.length; i++) {
			avgGenPco[i] = getAverageGenCount(genCountPerRun[i]);
		}
		
		for(int a = 0 ; a < pco.length ; a++)
		{
			System.out.print("Average gen count for pco " + pco[a] + ": ");
			System.out.println(avgGenPco[a]);
		}
		
		PopulationPlot[] genCountRunPlot = new PopulationPlot[pco.length];
		String pcoChartString;
		for (int i = 0; i < pco.length; i++) {
			pcoChartString = "Generation Count for PCO " + pco[i];
			genCountRunPlot[i] = new PopulationPlot(
					"Experiment 2",
					pcoChartString,
					genCountPerRun[i]);
		}
		
		PopulationPlot pcoChartAvg = new PopulationPlot(
				"Experiment 2: PCO Averages" , 
				"Experiment 2: PCO Averages", pco, avgGenPco);
		
		pcoChartAvg.pack();
	    RefineryUtilities.centerFrameOnScreen(pcoChartAvg);
	    pcoChartAvg.setVisible(true);
	    
	    for (PopulationPlot chart: genCountRunPlot) {
	    	chart.pack();
	    	RefineryUtilities.centerFrameOnScreen(chart);
	    	chart.setVisible(true);
	    }
	}
	
	private static int getAverageGenCount(int[] pcoGenArr) {
		int average = 0;
		int sum = 0;
		
		for (int genCount: pcoGenArr) {
			sum += genCount;
		}
		
		average = sum/(pcoGenArr.length);
		
		return average;
	}
}
