
public class GUI {
	/*
	 * Creates GUI and handles user actions
	 * for instance, plays the simulation
	 */
	Grid grid = new Grid();
	boolean play = true;
	int delay;

	public void controlSimulation() {
		while (play) {
			AlgorithmSomeOther.stepForward(grid.getGrid(), 1);
			//wait(delay)
		}
	}
}
