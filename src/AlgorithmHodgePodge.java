import java.util.ArrayList;

/**
 * @author Daniel
 */
/*
 * This is the algorithm described here http://www.diga.me.uk/hodgepodge.html
 */

public class AlgorithmHodgePodge implements Algorithm {

	private int localInfectionRate; // How much "sicker" a cell gets by itself.
	private int surroundingInfectionRate; // How much a sick sell sickens
											// another cell.
	private int range; // The range of infection, always goes from 0 to range,
						// where 0 is healthy and range is sick.
	private int threshold; // The threshold for a healthy cell to become ill.

	// Constructor that takes arguments, may never be used if interface doesn't
	// allow it
	public AlgorithmHodgePodge(int localInfectionRate,
			int surroundingInfectionRate, int range, int threshold) {
		this.localInfectionRate = localInfectionRate;
		this.surroundingInfectionRate = surroundingInfectionRate;
		this.range = range;
		this.threshold = threshold;
	}

	// Constructor that sets based on default values
	public AlgorithmHodgePodge() {
		localInfectionRate = 4;
		surroundingInfectionRate = 3;
		range = 16;
		threshold = 5;
	}

	// Algorithm to get the next generation
	public ArrayList<ChangelogItem> getNextGeneration(Grid g) { // unfinished
		ArrayList<ChangelogItem> arrList = new ArrayList<ChangelogItem>(); //Stores all the changes
		ChangelogItem c;
		int infectiontotal; //Total infection level for a cell
		int state = 0; // The infection level in the squareArray
		int[][] squareArray = g.getSquareArray();

		if (g.getGeneration() == 0) { //Randomises the grid if it is the first gen.
			g.setGridRandomColors(2);
		}
		
		for (int y = 0; y < g.getDimension(); y++) {
			for (int x = 0; x < g.getDimension(); x++) { //Loops through the squares
				infectiontotal = getInfectionTotal(y, x, g, squareArray); //Gets the infection level around the cell
				state = squareArray[x][y]; //Gets the state of that cell

				if (state == range) { //If the cell is fully ill, make it better
					c = new ChangelogItem(y, x, 0);
					arrList.add(c);
				} else if (state > 0 && state < range) { //If the cell is is partially ill, make it more sick
					infectiontotal = infectiontotal + localInfectionRate;
					c = new ChangelogItem(y, x, setInfection(infectiontotal));
					arrList.add(c);
				} else { //If the cell is healthy, see if the neighbouring infection is enough to make it ill
					if (infectiontotal > threshold) {
						c = new ChangelogItem(y, x,
								setInfection(infectiontotal));
						arrList.add(c);
					}
				}

			}
		}

		for (int u = 0; u < arrList.size(); u++) { //Updates the grid
			c = arrList.get(u);
			g.setSquareArray(c.getRow(), c.getColumn(), c.getNewState());
		}

		g.setGeneration(g.getGeneration() + 1); //Advances the generation
		return arrList;
	}

	// Algorithm to calculate the total infection value taken on from those
	// around it
	private int getInfectionTotal(int y, int x, Grid g, int[][] s) {
		int total = 0;
		int state = 0; // this will be the retrieved state from the grid

		for (int u = -1; u < 2; u++) {
			for (int i = -1; i < 2; i++) { //Loop around the square
				state = s[(y + u + g.getDimension()) % g.getDimension()][(x + i + g
						.getDimension()) % g.getDimension()];
				if (state > 0) { //If a neighbouring cell is ill, then increase the total infection for the cell by the infection rate
					total = total + surroundingInfectionRate;
				}
			}
		}

		return total;
	}

	// This sets the infection "nicely" , stopping it from going above the range
	private int setInfection(int level) {
		if (level > range) {
			level = range;
		}
		return level;
	}

	public int getLocalInfectionRate() {
		return localInfectionRate;
	}

	public void getGridAfterNGenerations(Grid g, int n) {
		for (int z = 0; z < n; z++)
			getNextGeneration(g);
	}

	public void setLocalInfectionRate(int localInfectionRate) {
		this.localInfectionRate = localInfectionRate;
	}

	public int getSurroundingInfectionRate() {
		return surroundingInfectionRate;
	}

	public void setSurroundingInfectionRate(int surroundingInfectionRate) {
		this.surroundingInfectionRate = surroundingInfectionRate;
	}

	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(int threshold) {

		this.threshold = threshold;
	}

}
