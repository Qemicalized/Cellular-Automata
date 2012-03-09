/*
 * The class that contains everything that all algorithm classes must have in common
 */
package cellularAutomata;

public abstract class GeneralAlgorithm {
	public abstract void advance(Square[][] grid, int count);
}
