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
	
	/**
	 * @param numberOfColors
	 */
	public Generations(int numberOfColors) {
		super(numberOfColors);

	}
	
	/**
	 * 
	 * @param numberOfColors Determines the number of colors that are to be used,
	 * eg. 2 for Game of Life, 3 for Brian's Brain.
	 * @param strSurvive A string containing ONLY digits [0-8] where every digit represent 
	 * a number of live(high) neighbours that will cause a cell not to decay. 
	 * @param strBorn A string containing ONLY digits [0-8] where every digit represent 
	 * a number of live(high) neighbours that will cause a cell to be reborn if it is dead.
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