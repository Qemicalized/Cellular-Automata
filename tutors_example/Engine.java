public class Engine extends Thread {

	// Current algorithm:
	private Algorithm alg;
	private int delay = 100;
	private GUI gui;
	private Grid grid;
	private boolean engineShouldRun = false;

	// Constructor:
	public Engine() {
		alg = new AlgorithmGameOfLife();
	}

	// Assigns a GUI object to the engine:
	public void setGUI(GUI g) {
		gui = g;
	}

	// Method for setting a new grid
	// (Will be called whenever the user changes
	// the dimension of the grid in GUI)
	public void setNewGrid(int dimension) {
		grid = new Grid(dimension);
	}

	// Setter for setting the delay (pause):
	// (Will be called from GUI (probably
	// from slider listener))
	public void setDelay(int a) {
		delay = a;
	}

	// Setter for engine instance variable, that
	// keeps track of whether engine should run:
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
		
		do {

			try {
				// this will make the current thread to be inactive for a while
				// no resources will be used
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			gui.paintSomeGrid(alg.getNextGeneration(grid));
		} while (engineShouldRun);
	}

	public void runEngine(int toGeneration) {
		if (toGeneration != grid.getGeneration()) {

			if (toGeneration > grid.getGeneration()) {
				alg.getGridAfterNGenerations(grid,
						toGeneration - grid.getGeneration());
				gui.paintAllGrid(grid);
			} else {
				setNewGrid(grid.getDimension());
				alg.getGridAfterNGenerations(grid,
						toGeneration - grid.getGeneration());
				gui.paintAllGrid(grid);
				setShouldEngineRun(false);
			}

		} else {
			do {
				gui.paintSomeGrid(alg.getNextGeneration(grid));
			} while (engineShouldRun);
		}
	}

}
