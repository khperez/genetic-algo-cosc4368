package hw1;

import java.util.Random;
import java.util.TreeMap;
import java.util.Vector;

public class Population {
	
	private int[] chromosomes;
	private int genCount = 1;
	private double pco;
	
	//Whenever Population object is called a new population will be created
	Population(double pco){
		this.pco = pco;
		this.chromosomes = randChromosomes();
	}
		
	//Population for identical seed value
	Population(int randSeed){
		chromosomes = randChromosomes(randSeed);
	}
	
	public static int[] randChromosomes(){
		  Random randChromo = new Random(System.currentTimeMillis());
		  int genChromosome[] = new int[20];
		  int j = 0;
		  while(j < 20)
		  {
			//Converted 10 1010 1010 to decimal
		    genChromosome[j] = randChromo.nextInt()%1023 + 1;
		    if((genChromosome[j] > 0) && (genChromosome[j] < 1023))
		    {
			    j++;
		    }
		  }
		  return genChromosome;
	 }

	public static int[] randChromosomes(int randSeed){
		  Random randChromo = new Random(randSeed);
		  int genChromosome[] = new int[20];
		  int j = 0;
		  while(j < 20)
		  {
		    genChromosome[j] = randChromo.nextInt()%1023 + 1;
		    if((genChromosome[j] > 0) && (genChromosome[j] < 1023))
		    {
			    j++;
		    }
		  }
		  return genChromosome;
	 }
	
	public void crossover() {
		TreeMap<Integer, Integer> chromoCollection = new TreeMap<Integer, Integer>();
		Vector<Integer> chromoVector = new Vector<Integer>();
		Vector<Integer> resultVector = new Vector<Integer>();
		Vector<Integer> mergeVector = new Vector<Integer>();
		genCount++;
		int[] chromo = new int[2];
		double randChromo;
		int chromoVal;
		int chromosomeA, chromosomeB, chromosomeY, chromosomeZ;
		int totalWeight = 0;
		int total = 0;
		int vecIndex = 0;
		int crossoverTotal = (int) (pco * chromosomes.length);
		int crossoverMask = 0b1111100000;
		// Initialize vector with chromosomes
		for (int chromosome: chromosomes) {
			chromoVector.add(chromosome);
			chromoVal = getFitnessValue(chromosome);
			totalWeight += chromoVal;
			chromoCollection.put(totalWeight, chromosome);
		}
		while (total < crossoverTotal){
			if (total != 0) {
				chromoCollection.clear();
				totalWeight = 0;
				for (int chromosome: chromoVector) {
					chromoVal = getFitnessValue(chromosome);
					totalWeight += chromoVal;
					chromoCollection.put(totalWeight, chromosome);
				}
			}
			// Randomly select two chromosomes to apply cross-over
			for (int count = 0; count < 2; count++) {
				randChromo = Math.random() * totalWeight;
				int key = chromoCollection.ceilingKey((int) randChromo);
				chromo[count] = chromoCollection.remove(key);
				chromoCollection.clear();
				totalWeight = 0;
				for (int chromosome: chromoVector) {
					chromoVal = getFitnessValue(chromosome);
					totalWeight += chromoVal;
					chromoCollection.put(totalWeight, chromosome);
				}
				chromoVector.remove((Object) chromo[count]);
			}
			chromosomeA = chromo[vecIndex] & crossoverMask;
			chromosomeB = chromo[vecIndex+1] & ~crossoverMask;
			chromosomeY = chromo[vecIndex] & ~crossoverMask;
			chromosomeZ = chromo[vecIndex+1] & crossoverMask;
			resultVector.add(chromosomeA | chromosomeB);
			resultVector.add(chromosomeY | chromosomeZ);
			total += 2;
		}
		// Merge chromosomes unaffected by cross-over
		mergeVector = mergeVectors(resultVector, chromoVector);
		for (int i = 0; i < chromosomes.length; i++) {
			chromosomes[i] = mergeVector.get(i);
		}
	}
	
	private static Vector<Integer> mergeVectors(Vector<Integer> vectorA, Vector<Integer> vectorB){
		Vector<Integer> merge = new Vector<Integer>();
		merge.addAll(vectorA);
		merge.addAll(vectorB);
		return merge;
	}
	
	public void mutate(){
		Random select = new Random();
		//mutator is used to select which chromosome to mutate
		int mutator = select.nextInt()%20;
		while(mutator < 0){
			//mutator must be between 0-19
			mutator = select.nextInt()%20;
		}

		int chromosome = chromosomes[mutator];
		//mutatedBit is used to select which bit to convert
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
	
	public void setPco(double pco) {
		this.pco = pco;
	}
	
	public double getPco() {
		return pco;
	}
	
	public void setGenCount(int value) {
		this.genCount = value;
	}
	

}
