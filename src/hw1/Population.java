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
	
	public int[] getChromosomes() {
		return this.chromosomes;
	}
}
