/*
 * I believe this is somewhat like how we want the GUI to handle the algorithms.
 */
package cellularAutomata;

public class NoteOnGUI {

	private GeneralAlgorithm[] algorithms;
	
	Grid grid = new Grid();
	boolean play = false;
	int delay = 1000;

	public void controlSimulation(int selection) {
		while (play) {
			algorithms[selection].advance(grid.getGrid(), 1);
			//wait(delay)
		}
	}
}
