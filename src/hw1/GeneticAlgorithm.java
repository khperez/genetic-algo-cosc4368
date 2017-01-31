package hw1;

import java.util.Random;
import java.util.Stack;

public class GeneticAlgorithm {

	public static int[] crossover(int[] population, double pco) {
		int crossoverTotal = (int) (pco * population.length);
		int crossoverMask = 0b1111100000;
		for (int i = 0; i < crossoverTotal; i = i+2) {
			int chromosomeA, chromosomeB, chromosomeY, chromosomeZ = 0;
			chromosomeA = population[i] & crossoverMask;
			chromosomeB = population[i+1] & ~crossoverMask;
			chromosomeY = population[i] & ~crossoverMask;
			chromosomeZ = population [i+1] & crossoverMask;
			population[i] = chromosomeA | chromosomeB;
			population[i+1] = chromosomeY | chromosomeZ;
		}
		
		return population;
	}
	
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
	
	public static int experiment(double pco) {
		
		int[] nextGeneration = new int[20], mutatedGeneration = new int[20];
		boolean maxFitnessAchieved = false;
		int generationCount = 1;
		
		boolean fitnessMutation = false, fitnessCrossover = false;
		
		System.out.println("Initial Population");
		System.out.println("Chromosome\tFitness value:");	
		for(int i = 0; i < population.length; i++) {
			System.out.println( String.format("%10s\t", 
								Integer.toBinaryString(population[i])).replace(' ', '0') 
								+ calculateFitness(population[i]));
			// Check fitness value
			if (calculateFitness(population[i]) == 10) {
				break;
			}
		}
		
		
		for (generationCount = 2 ; !maxFitnessAchieved; generationCount++) {
			
			nextGeneration = crossover(population, pco);
			
			System.out.println("Generation " + generationCount);
			for(int i = 0; i < nextGeneration.length; i++) {
				System.out.println( String.format("%10s\t", 
									Integer.toBinaryString(nextGeneration[i])).replace(' ', '0') 
									+ calculateFitness(nextGeneration[i]));
				// Check fitness value
				if (calculateFitness(nextGeneration[i]) == 10) {
					maxFitnessAchieved = true;
					fitnessCrossover = true;
					break;
				}
			}
			
			if (!maxFitnessAchieved) {
						
				mutatedGeneration = mutatorGenerator(nextGeneration);
				System.out.println("Mutated generation: ");
				for(int i = 0; i < mutatedGeneration.length; i++) {
					System.out.println( String.format("%10s\t", 
										Integer.toBinaryString(mutatedGeneration[i])).replace(' ', '0') 
										+ calculateFitness(mutatedGeneration[i]));
					// Check fitness value
					if (calculateFitness(mutatedGeneration[i]) == 10) {
						maxFitnessAchieved = true;
						fitnessMutation = true;
						break;
					}
				}
			}
			
			if (!maxFitnessAchieved){
				population = mutatedGeneration;
			}
			else {
				break;			
			}
			
		}
		
		System.out.println("Max fitness achieved at generation " + generationCount);
		if (fitnessMutation) {
			System.out.println("By Mutation");
		}
		else if (fitnessCrossover){
			System.out.println("By Cross-Over");
		}	

		return generationCount;
	}
	
	public static void main(String[] args) {
		
		/* 
		 * Experiment 1
		 * Run genetic algorithm 20 times.
		 * Print average number of generations to reach max
		 * fitness value.
		 *  
		 */
		
		Population population = new Population();
		
		if (!population.maxFitnessAchieved()){
			System.out.println(population.maxFitnessAchieved());
		}
		
	}
		
}
