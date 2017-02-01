package hw1;

import java.util.Random;

public class Population {
	
	private String maxFitMethod;
	private int[] chromosomes;
	private static int genCount;
	private static int counter=0;
	private static int genCountAvg=0;
	Population(){
		maxFitMethod = null;
		chromosomes = randChromosomes();
		genCount = 1;
	}
	
	private int[] randChromosomes() 
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
	
	private static void shuffleArray(int[] array)
	{
	    int index;
	    Random random = new Random();
	    for (int i = array.length - 1; i > 0; i--)
	    {
	        index = random.nextInt(i + 1);
	        if (index != i)
	        {
	            array[index] ^= array[i];
	            array[i] ^= array[index];
	            array[index] ^= array[i];
	        }
	    }
	}
	
	public void crossover(double pco) {
		for (int i = 0; i < chromosomes.length; i++) {
			shuffleArray(chromosomes);
		}
		genCount++;
		int crossoverTotal = (int) (pco * chromosomes.length);
		int crossoverMask = 0b1111100000;
		for (int i = 0; i < crossoverTotal; i = i+2) {
			int chromosomeA = 0, chromosomeB = 0, chromosomeY = 0, chromosomeZ = 0;
			chromosomeA = chromosomes[i] & crossoverMask;
			chromosomeB = chromosomes[i+1] & ~crossoverMask;
			chromosomeY = chromosomes[i] & ~crossoverMask;
			chromosomeZ = chromosomes[i+1] & crossoverMask;
			chromosomes[i] = chromosomeA | chromosomeB;
			chromosomes[i+1] = chromosomeY | chromosomeZ;
		}
	}
	
	public void mutate(){
		Random select = new Random(System.currentTimeMillis());
		int mutator = select.nextInt()%20;
		while(mutator < 0){
			mutator = select.nextInt()%20;
		}
		int chromosome = chromosomes[mutator];
		int mutatedBit = select.nextInt()%10;
		while(mutatedBit < 0){
			mutatedBit = select.nextInt()%10;
		}
		int mask = 1;
		int mutatedChromosome = chromosome ^ (mask << mutatedBit);
		chromosomes[mutator] = mutatedChromosome;
	}

	
	public boolean maxFitnessAchieved() {		
		for (int i = 0; i < chromosomes.length; i++) {
			if (getFitnessValue(chromosomes[i]) == 10) {
				return true;
			}
		}
		
		return false;
	}
	
	private int getFitnessValue(int chromosome) {
		int fitness = 0;
		for (int count = 0; count < 10; count++) {
			if (count % 2 == 0) {
				if ((chromosome & 1) == 0) {
					fitness++;
				}
			}
			else
				if ((chromosome & 1) == 1) {
					fitness++;
				}
			chromosome = chromosome >> 1;
		}
		
		return fitness;
	}
	
	public int[] getChromosomes() {
		return this.chromosomes;
	}
	
	public void setMaxFitMethod(String maxFitMethod) {
		this.maxFitMethod = maxFitMethod;
	}
	
	public String getMaxFitMethod() {
		return maxFitMethod;
	}
	
	public void printChromosomes() {
		System.out.println("Chromosome:");
		for (int i = 0; i < chromosomes.length; i++) {
			System.out.println(String.format("%10s\t", 
					Integer.toBinaryString(chromosomes[i])).replace(' ', '0'));
		}
	}
	
	public void printFitnessValues() {
		System.out.println("Fitness Values: ");
		for (int i = 0; i < chromosomes.length; i++) {
			System.out.println(getFitnessValue(chromosomes[i]));
		}
	}
	
	public void printPopulation() {
		System.out.println("Chromosome:\tFitness Value:");
		for (int i = 0; i < chromosomes.length; i++) {
			System.out.println(String.format("%10s\t", 
					Integer.toBinaryString(chromosomes[i])).replace(' ', '0')
					+ getFitnessValue(chromosomes[i]));
		}
	}
	
	public int getGenCount() {
		return genCount;
	}
	
	public int getGenCountAvg(int genCount)
	{
		genCountAvg += genCount/counter;
		counter++;
		return genCountAvg;
	}
}
