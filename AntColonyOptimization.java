package final_ants;

public class AntColonyOptimization {
	
	public static int numberOfBins;
	public static int numberOfItems;
	private static int numberOfEvaluations;
	private static int numberOfPaths;
	private static double e; //the evaporation rate
	public static int[] itemArray; //array of all the items
	public static int[] binArray; //array of all the bins
	public static int fitnessCount; // Number of fitness evaluations
	public static double optimalFitness; //current best fitness
	private static Graph graph;
	
	public static void main(String[] args){
		System.out.println("Running, please wait...");
		e = 0.9; //set the desired evaporation rate
		numberOfEvaluations = 10000; //set the desired number of evaluations
		numberOfPaths = 100; // set the desired number of paths
		setBPP1(); //choose BPP1 or BPP2
		optimalFitness = 2147483647; //Set to the 'worst' value possible, the highest
		graph = new Graph(numberOfItems,numberOfBins); //create graph
		
		while(fitnessCount < numberOfEvaluations){
			Ant[] antArray = new Ant[numberOfPaths];
			for(int i=0; i < numberOfPaths ; i++){
				antArray[i] = new Ant();
				antArray[i].calculatePath();
				antArray[i].calculateFitness();
			}
			for(Ant ant : antArray){
				// updates the pheromone tables
				graph.refreshPheromone(ant);
			}
			graph.evaporate(e);
		}
		System.out.println(numberOfEvaluations + " Evaluations");
		System.out.println("Optimal Fitness: " + optimalFitness);
	}
	
	public static void setBPP1(){
		//BPP1
		numberOfItems = 500;
		itemArray = new int[numberOfItems];
		
		numberOfBins = 10;
		binArray = new int[numberOfBins];
		
		for(int i = 1; i <= numberOfItems; i++){
			itemArray[i-1] = i;
		}
	}
	
	public static void setBPP2(){
		//BPP2
		numberOfItems = 500;	
		itemArray = new int[numberOfItems];

		numberOfBins = 50;
		binArray = new int[numberOfBins];
		
		for(int i = 1; i <= numberOfItems; i++){
			itemArray[i-1] = (i*i)/2;
		}
	}
	
	
}
