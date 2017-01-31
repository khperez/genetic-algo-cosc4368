package hw1;

public class GeneticAlgorithm {

	public int genMaker() 
	   {
	        int genChromosome = 1;
	        return genChromosome;
	   }
	
	public static int[] crossover(int[] population, double pco) {
		int crossoverTotal = (int) (pco * population.length);
		int crossoverMask = 0b1111100000;
		for (int i = 0; i < crossoverTotal; i = i+2) {
			int chromosomeA, chromosomeB, chromosomeY, chromosomeZ = 0;
			System.out.println(String.format("%10s", Integer.toBinaryString(population[i])).replace(' ', '0'));
			System.out.println(String.format("%10s", Integer.toBinaryString(population[i+1])).replace(' ', '0'));
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
	public static void main(String[] args) {

		int[] population = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};
		int[] nextGeneration;
		double pco = 0.7;
		
		System.out.println("Initial population: ");		
		for(int i = 0; i < population.length; i++) {
			System.out.println(String.format("%10s\t", 
								Integer.toBinaryString(population[i])).replace(' ', '0') + calculateFitness(population[i]));
		}
		
		nextGeneration = crossover(population, pco);
		
		System.out.println("Next generation: ");
		for(int i = 0; i < nextGeneration.length; i++) {
			System.out.println(String.format("%10s", Integer.toBinaryString(nextGeneration[i])).replace(' ', '0'));
		}
	}

}
