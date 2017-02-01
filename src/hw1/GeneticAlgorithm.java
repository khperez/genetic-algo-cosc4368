package hw1;

import java.util.Random;

public class GeneticAlgorithm {
	
	public static int[] mutatorGenerator(int[] population)
	{
		Random select = new Random(System.currentTimeMillis());
		int mutator = select.nextInt()%20;
		while(mutator < 0)
		{
			mutator = select.nextInt()%20;
		}
//		System.out.println("Mutator index: " + mutator);
		int chromosome = population[mutator];
		int mutatedBit = select.nextInt()%10;
		while(mutatedBit < 0)
		{
			mutatedBit = select.nextInt()%10;
		}
//		System.out.println("Mutated bit: " + mutatedBit);
		int mask = 1;
		int mutatedChromosome = chromosome ^ (mask << mutatedBit);
		population[mutator] = mutatedChromosome;
		return population;
	}
		
	public static void main(String[] args) {
		
		/* 
		 * Experiment 1
		 * Run genetic algorithm 20 times at pco = 0.7
		 * Print average number of generations to reach max
		 * fitness value.
		 *  
		 */
		
		Population popEx1 = new Population();
		int genCount;
		double pco;
		
		popEx1.printChromosomes();		
		popEx1.printFitnessValues();
		
		for (pco = 0.7, genCount = 1; !popEx1.maxFitnessAchieved(); genCount++){
			if (!popEx1.maxFitnessAchieved()){
				popEx1.crossover(pco);
			}
			
		}
		
	}
		
}
