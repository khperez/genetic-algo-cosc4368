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
		Random mateTime = new Random(System.currentTimeMillis());
		for (int i = 0; i < crossoverTotal; i = i+2) {
			int chromosomeA, chromosomeB, chromosomeY, chromosomeZ = 0;
			chromosomeA = population[i] & crossoverMask;
			chromosomeB = population[i+1] & ~crossoverMask;
			chromosomeY = population[i] & ~crossoverMask;
			chromosomeZ = population [i+1] & crossoverMask;
			
			int chromoOne = mateTime.nextInt()%20;
			while(chromoOne < 0)
			{
				chromoOne = mateTime.nextInt()%20;
			}
			population[chromoOne] = chromosomeA | chromosomeB;
			int chromoTwo = mateTime.nextInt()%20;
			while((chromoTwo < 0) || (chromoOne == chromoTwo))
			{
				chromoTwo = mateTime.nextInt()%20;	
			}
			population[chromoTwo] = chromosomeY | chromosomeZ;	
		}
		
		return population;
	}
	
	public static int calculateFitness(int chromosome) {
		int fitness = 0;
		
		for (int count = 0; count < 10; count++) {
			if (count % 2 == 0) { 
				if ((chromosome & 1) == 0)
					fitness++;
			}
			else
				if ((chromosome & 1) == 1)
					fitness++;
			chromosome = chromosome >> 1;
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
		double pco = 0;
		int[] nPco = new int[5];
		String[] sPco = new String[5];
		String mutStr = "";
		String crossStr = "";
		int[] population = randChromosomes();
		for(int ii = 0 ; ii < 5 ; ii++)
		{
			switch(ii)
			{
				case 0:System.out.println("case: " + ii);
						pco = 0.7;
				break;
				case 1:System.out.println("case: " + ii);
						pco = 0.3;
				break;
				case 2:System.out.println("case: " + ii);
						pco = 0.5;
				break;
				case 3:System.out.println("case: " + ii);
						pco = 0.9;
				break;
				case 4:System.out.println("case: " + ii);
						pco = 0;
				break;
				default:System.out.println("Error");
				break;
			}
		
			int[] nextGeneration = new int[20], mutatedGeneration = new int[20];
//			double pco = 0.7;
			boolean maxFitnessAchieved = false;
			int generationCount = 1;
			
			boolean fitnessMutation = false, fitnessCrossover = false;
			
//			System.out.println("Initial Population");
//			System.out.println("Chromosome\tFitness value:");	
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
				
//				System.out.println("Generation " + generationCount);
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
//					System.out.println("Mutated generation: ");
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
				mutStr = "Mutation";
				sPco[ii] = mutStr;
			}
			else if (fitnessCrossover){
				System.out.println("By Cross-Over");	
				crossStr = "Cross-Over";
				sPco[ii] = crossStr;
			}	
			System.out.println("\n\n\n\n\n\n");
			nPco[ii] = generationCount;
		}
		for(int i = 0 ; i < 5 ; i++)
		{
			System.out.println("mPco" + i + ": " + nPco[i]);
			System.out.println("sPco: " + sPco[i]);
		}
	}
}
