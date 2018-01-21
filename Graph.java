package final_ants;

public class Graph {
	public static double [][] pheromoneValues;
	
	public Graph(int numberOfItems, int numberOfBins){
		// creates a 2d array
		pheromoneValues = new double[numberOfItems][numberOfBins];
		for(int i = 0; i < numberOfItems; i++){
			for(int j = 0; j < numberOfBins; j++){	
				double r = Math.random();
				pheromoneValues[i][j] = r;
				
			}
		}	
	}
	
	public void evaporate(double e){
		// e = evaporation rate
		for(int i = 0; i < pheromoneValues.length; i++){
			for(int j = 0; j < pheromoneValues[i].length; j++){
				double pCount = pheromoneValues[i][j]*e;
				pheromoneValues[i][j] = pCount;
			}
		}
	}
	
	public void refreshPheromone(Ant ant) {
		// used to refresh the pheromones
		for(int i = 0; i < pheromoneValues.length; i++){
			int bin = ant.selectedBins[i];
			pheromoneValues[i][bin] += ( (100)/(ant.currentFitness) );
		}
	}
	
}
