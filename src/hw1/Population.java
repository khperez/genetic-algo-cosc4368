package hw1;

import java.util.Random;

public class Population {
	
	private String maxFitMethod;
	private int maxFitGen;
	private int[] chromosomes;

	Population(){
		maxFitMethod = null;
		maxFitGen = 0;
		chromosomes = randChromosomes();
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
	
	public int[] crossover(double pco) {
		int[] population = this.chromosomes;
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
	
	public boolean maxFitnessAchieved() {		
		for (int i = 0; i < chromosomes.length; i++) {
			int fitness = 0;
			for (int count = 0; count < 10; count++) {
				if (count % 2 == 0) {
					if ((chromosomes[i] & 1) == 0) {
						fitness++;
					}
				}
				else
					if ((chromosomes[i] & 1) == 1) {
						fitness++;
					}
				chromosomes[i] = chromosomes[i] >> 1;
			}
			if (fitness == 10) {
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
}
