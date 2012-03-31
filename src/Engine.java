public class Engine extends Thread{
	
	//Current algorithm:
	private Algorithm alg;
	private int delay = 46;
	private GUI gui;
	private Grid grid;
	private boolean engineShouldRun = false;
	private int type;
	private int noOfColors;
	
	public Engine(int type, int dimension, int noOfColors){ // type is 0 for Game of Life, 1 for Langton's Ant, 2 is arbitrary
		this.type = type;
		this.noOfColors = noOfColors;
		if (type == 0){
			alg = new Generations(noOfColors, "23", "3");
		} else if (type == 1){
			alg = new AlgorithmLangtonsAnt(dimension, noOfColors);
		} else{
			alg = new AlgorithmHodgePodge();
		}
	}
	
	//Assigns a GUI object to the engine:
	public void setGUI(GUI g) {
		gui = g;
	}
	
	//Method for setting a new grid
	//(Will be called whenever the user changes
	//the dimension of the grid in GUI)
	public void setNewGrid(int dimension) {
		grid = new Grid(dimension);
	}
	
	//Setter for setting the delay (pause):
	//(Will be called from GUI (probably
	//from slider listener))
	public void setDelay(int a) {
		delay = a;
	}
	
	//Setter for engine instance variable, that 
	//keeps track of whether engine should run:
	public void setShouldEngineRun(boolean a) {
		engineShouldRun = a;
	}
	
	public boolean isEngineRunning() {
		return engineShouldRun;
	}
	
	public void setAlgorithm(Algorithm x) {
		alg = x;
	}

	@Override
	public void run() {

		// this has to be set using a class variable
		int toGeneration = 1;

		alg.getGridAfterNGenerations(grid, toGeneration - grid.getGeneration());
		gui.paintAllGrid(grid);
		long start;
		long elapsedTimeMillis;
		do {
			start = System.currentTimeMillis();
			gui.plusOneGeneration();
			gui.paintSomeGrid(alg.getNextGeneration(grid));
			elapsedTimeMillis = System.currentTimeMillis() - start;
			try {
				// this will make the current thread to be inactive for a while
				// no resources will be used
				if (delay > elapsedTimeMillis){
					Thread.sleep(delay - elapsedTimeMillis);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} while (engineShouldRun);
	}

	public void runEngine(int toGeneration ) {
		// If the user picked some custom generation that he wants to see:
		if (toGeneration != grid.getGeneration()) {
			int generationDifference;
			//If the generation that user picked customly is a latter generation
			//of our grid:
			if (toGeneration > grid.getGeneration()) {
				generationDifference = toGeneration - grid.getGeneration();
				//We calculate it without delay:
				for (int i = 1; i <= 100; i++){
					alg.getGridAfterNGenerations(grid, generationDifference/100);
					gui.increaseProgressBar();
				}
				gui.increaseProgressBar();
				//Then we present it on the screen:
				gui.paintAllGrid(grid);
				//If the generation, that user picked customly is smaller than current
				//generation of the grid (say the grid is in its 50-th generation and 
				//the user entered "20" to the generation input field) :
			} else {
				generationDifference = toGeneration;
				//We need to get new grid:
				setNewGrid(grid.getDimension());
				//Then we run it (without delay) to the generation that the user entered:
				for (int i = 1; i <= 100; i++){
					alg.getGridAfterNGenerations(grid, generationDifference/100);
					gui.increaseProgressBar();
				}
				gui.increaseProgressBar();
				//Present it on the screen:
				gui.paintAllGrid(grid);
				//After getting some customly inputed generation of the grid, we should turn off the engine:
				setShouldEngineRun(false);
			}
			//If the user did not enter any custom value to the generation input field:
		} else {
			//We get a next generation of the grid and paint on screen only those cells that changed
			//while the user doesn't do any action, that switches the engine off (sets engineShouldRun to false):
			do {
				gui.paintSomeGrid(alg.getNextGeneration(grid));
				//Pause.wait(Delay);
				//(i don't know the proper function for this), like pause(delay);
			} while (engineShouldRun);
		}
	}
	
	public int getNoOfColors(){
		return noOfColors;
	}
	
	public int getType(){
		return type;
	}
}
