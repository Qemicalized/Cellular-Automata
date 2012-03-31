/**
 * 
 */

/**
 * @author Peppe
 *
 */
public class Generations extends FOTCA {

	private boolean[] survive;
	private boolean[] born;
	
	/*
	 * A couple of examples to clarify:
	 * Generations gameOfLife = new Generations(2, "23", "3")
	 * Has 2 states(colors), live cell survives with 2 or 3 neighbours, a dead cell is reborn if
	 * it has 3 neighbours.
	 * Generations briansBrain = new Generations (3, "", "2");
	 * Has 3 states(colors), a live cell will never survive (ie it will always decay), and a dead
	 * cell will be reborn if it has two neighbours
	 */
	/**
	 * @param numberOfColors Determines the number of colors, or states, that each cell can
	 * have/be in, eg. 2 for Game of Life, 3 for Brian's Brain.
	 * @param strSurvive A string containing ONLY digits [0-8] where every digit represent 
	 * a number of live(high) neighbours that will cause a cell not to decay. eg. "23"
	 * for Game of Life
	 * @param strBorn A string containing ONLY digits [0-8] where every digit represent 
	 * a number of live(high) neighbours that will cause a cell to be reborn if it is dead.
	 * eg. "3" for Game of Life.
	 */
	public Generations(int numberOfColors, String strSurvive, String strBorn) {
		super(numberOfColors);
		this.survive = new boolean[9];
		this.born = new boolean[9];
		char[] s = strSurvive.toCharArray();
		char[] b = strBorn.toCharArray();
		
		for (int i = 0; i < s.length; i++) {
			this.survive[(int)s[i]-48] = true;
		}
		for (int i = 0; i < b.length; i++) {
			this.born[(int)b[i]-48] = true;
		}
	}

	/* (non-Javadoc)
	 * @see FOTCA#applyRule(int, int)
	 */
	@Override
	protected void applyRule(int x, int y) {
		int neighboursCount = countHighNeighbours(x, y);
		if (born[neighboursCount] && isStateLow(x, y)) {
			setStateHigh(x, y);
		}else if (survive[neighboursCount]) {
			carryState(x, y);
		}else {
			decayState(x, y);
		}
	}
}