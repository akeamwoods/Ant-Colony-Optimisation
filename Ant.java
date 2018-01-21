package final_ants;

public class Ant {
	
	public double currentFitness;
	
	public int[] selectedBins = new int[AntColonyOptimization.numberOfItems];
	private int[] weightsOfBins = new int[AntColonyOptimization.numberOfBins];
	double[][] pheromoneValues = Graph.pheromoneValues;
	
	private int selectBin(double[] binList, double sum){
		// Method to choose a random bin
		int rIndex = -1;
		double r = Math.random() * sum;
		for (int i = 0; i < binList.length; ++i){
		    r -= binList[i];
		    if (r <= 0.0d){
		        rIndex = i;
		        break;
		    }
		}
		return rIndex;
	}
	
	public void calculatePath() {
		// Method used to calculate the ants paths
		for(int i = 0; i < pheromoneValues.length; i++){
			double sum = 0.0;
			double[] trails = pheromoneValues[i];
			for(double count : trails){
				sum+=count;
			}
			selectedBins[i] = selectBin(trails, sum);
		}
	}
	
	public void calculateFitness(){
		// Method used to calculate the fitness
		int maxWeight = 0;
		int minWeight = 2147483647;
		
		for(int i = 0; i < selectedBins.length; i++){
			weightsOfBins[selectedBins[i]] += AntColonyOptimization.itemArray[i];
		}
		for(int i = 0; i < weightsOfBins.length; i++){
			if( weightsOfBins[i] > maxWeight ){
				maxWeight = weightsOfBins[i];
			}
			if(weightsOfBins[i] < minWeight){
				minWeight = weightsOfBins[i];
			}
		}
		currentFitness = maxWeight-minWeight;
		AntColonyOptimization.fitnessCount++;
		
		if(AntColonyOptimization.optimalFitness > currentFitness){
			AntColonyOptimization.optimalFitness = currentFitness;
		}
	}

	
}
