package hw1;

import java.util.Random;
public class GeneticAlgorithm {

	public static int[] randChromosomes() 
    {
		Random randChromo = new Random(System.currentTimeMillis());
        int genChromosome[] = new int[20];
        int j = 0;
        while(j < 20)
        {
        	genChromosome[j] = randChromo.nextInt()%682 + 1;
        	if((genChromosome[j] > 0) && (genChromosome[j] < 682))
        	{
        		j++;
        	}
        }
        return genChromosome;
    }
	
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
	
	public static int calculateFitness(int chromosome) {
		int fitness = 0, odd = 0, even = 0;
		int oddMask = 0b1010101010;
		int evenMask = 0b0101010101;
		
		odd = chromosome & oddMask;
		even = chromosome & evenMask;
		
		for (int i = 0; i < 10; i++) {
			if ((odd & 1) == 1)
				fitness++;
			else
				odd = odd >> 1;
			
			if ((even & 1) == 0)
				fitness++;
			else
				even = even >> 1;
		}
		
		return fitness;
	}
	
	public static int[] mutatorGenerator(int[] population)
	{
		Random select = new Random(System.currentTimeMillis());
		int mutator = select.nextInt()%20;
		while(mutator < 0)
		{
			mutator = select.nextInt()%20;
		}
		System.out.println("Mutator index: " + mutator);
		int chromosome = population[mutator];
		int mutatedBit = select.nextInt()%10;
		while(mutatedBit < 0)
		{
			mutatedBit = select.nextInt()%10;
		}
		System.out.println("Mutated bit: " + mutatedBit);
		int mask = 1;
		int mutatedChromosome = chromosome ^ (mask << mutatedBit);
		population[mutator] = mutatedChromosome;
		return population;
	}
	
	public static void main(String[] args) {

		int[] population = randChromosomes();
		int[] nextGeneration, mutatedGeneration;
		double pco = 0.7;
		
		System.out.println("Initial population: ");		
		for(int i = 0; i < population.length; i++) {
			System.out.println(String.format("%10s\t", 
								Integer.toBinaryString(population[i])).replace(' ', '0') + calculateFitness(population[i]));
		}
		
		nextGeneration = crossover(population, pco);
		
		System.out.println("Next generation: ");
		for(int i = 0; i < nextGeneration.length; i++) {
			System.out.println(i + String.format("\t%10s", Integer.toBinaryString(nextGeneration[i])).replace(' ', '0'));
		}
		
		mutatedGeneration = mutatorGenerator(nextGeneration);
		System.out.println("Mutated generation: ");
		for(int i = 0; i < mutatedGeneration.length; i++) {
			System.out.println(i + String.format("\t%10s", Integer.toBinaryString(mutatedGeneration[i])).replace(' ', '0'));
		}
	}

}
