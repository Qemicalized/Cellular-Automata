/*
 * This whole class is a quick remake of a previous implementation and is a bit hacked.
 */
package testCode;

public class TestGameOfLife extends TestGeneralAlgorithm{
	final private int LAYERS = 2;

	private int width;
	private int height;
	
	private int currentGeneration=0;
	private int currentLayer=0;
	private int nextLayer = 1;

	private boolean[][][] grid;
	
	private TestSquare[][] surface;
	
	/**
	 * Initializes the GameOfLife simulation to match the provided fixed size grid. 
	 * @param surface A two-dimensional array of any object that implements BooleanCell. Can be any size.
	 */
	public void advance(TestSquare[][] surface, int count) {
		this.width = surface.length;
		this.height = surface.length;
		this.grid = new boolean[LAYERS][width][height];
		this.surface = surface;
		
		mirrorSurface();
		
		if (count <= 1) {
			stepForward();
		}else {
			fastForward(count);
		}
		
		
	}
	
	private void mirrorSurface() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				grid[currentLayer][i][j] = surface[i][j].getBool();
			}
		}
	}
	
	private void setCell(int x, int y, boolean bool) {
		grid[nextLayer][x][y] = bool;
		surface[x][y].setBool(bool);
	}
	
	private void carryCell(int x, int y) {
		grid[nextLayer][x][y] = grid[currentLayer][x][y];
	}
	
	private void incrementGen() {
		currentGeneration++;
		currentLayer = nextLayer;
		nextLayer = (nextLayer+1)%LAYERS;
	}
	
	private int countLiveNeighbours(int x, int y) {
		int count = (grid[currentLayer][x][y])? -1 : 0;
		x = (x+width-1)%width;
		y = (y+height-1)%height;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				count += (grid[currentLayer][(x+i)%width][(y+j)%height])? 1 : 0;
			}
		}
		return count;
	}
	
	/**
	 * Steps the simulation forward one generation updating the 'surface'.
	 */
	public void stepForward() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int liveNeighbours = countLiveNeighbours(i, j);
				if (liveNeighbours < 2) {
					setCell(i, j, false);
				}else if (liveNeighbours == 3) {
					setCell(i, j, true);
				}else if (liveNeighbours > 3) {
					setCell(i, j, false);
				}else {
					carryCell(i, j);
				}
			}
		}
		incrementGen(); //Don't forget to keep track of what layers to read and write.
	}
	
	/**
	 * Steps the simulation forward a number of generations, updating the 'surface' only after the last step. Faster than using step forward an equivalent number of times.
	 * @param count The number of generations to step through.
	 */
	public void fastForward(int count) {
		for (int k = 1; k < count; k++) {
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					int liveNeighbours = countLiveNeighbours(i, j);
					if (liveNeighbours < 2) {
						grid[nextLayer][i][j] = false;
					}else if (liveNeighbours == 3) {
						grid[nextLayer][i][j] = true;
					}else if (liveNeighbours > 3) {
						grid[nextLayer][i][j] = false;
					}else {
						carryCell(i, j);
					}
				}
			}
			incrementGen();
		}
		stepForward();
	}
	
	/**
	 * Sets the state of the cell at the provided coordinate to either live or dead.
	 * @param x The x coordinate of the cell.
	 * @param y The y coordinate of the cell.
	 * @param state The state to which the cell is set: true for live; false for dead;
	 */
	public void safeSetCell(int x, int y, boolean state) {
		x = (x +width)%width;
		y = (y +height)%height;
		grid[currentGeneration][x][y] = state;
		surface[x][y].setBool(state);
	}
		
	/**
	 * 
	 * Places a pattern that will expand infinitely, provided an infinite grid, at position provided. Wraps around if the values are out of bound.
	 * 
	 * @param x	The x coordinate of the leftmost part of the pattern.
	 * @param y The y coordinate of the topmost part of the pattern.
	 */
	public void placeInfinite(int x, int y) {
		safeSetCell(x, y, true);
		safeSetCell(x+1, y, true);
		safeSetCell(x+2, y, true);
		safeSetCell(x+4, y, true);
		
		safeSetCell(x, y+1, true);
		
		safeSetCell(x+3, y+2, true);
		safeSetCell(x+4, y+2, true);
		
		safeSetCell(x+1, y+3, true);
		safeSetCell(x+2, y+3, true);
		safeSetCell(x+4, y+3, true);
		
		safeSetCell(x, y+4, true);
		safeSetCell(x+2, y+4, true);
		safeSetCell(x+4, y+4, true);
	}
	
	/**
	 * 
	 * Places a still block pattern at position provided. Wraps around if the values are out of bound.
	 * 
	 * @param x	The x coordinate of the leftmost part of the pattern.
	 * @param y The y coordinate of the topmost part of the pattern.
	 */
	public void placeBlock(int x, int y) {
		safeSetCell(x, y, true);
		safeSetCell(x, y+1, true);
		safeSetCell(x+1, y, true);
		safeSetCell(x+1, y+1, true);
	}
	
	/**
	 * 
	 * Places an oscillating pattern at position provided. Wraps around if the values are out of bound.
	 * 
	 * @param x	The x coordinate of the leftmost part of the pattern.
	 * @param y The y coordinate of the topmost part of the pattern.
	 */
	public void placeBeacon(int x, int y) {
		placeBlock(x, y);
		placeBlock(x+2, y+2);
	}
	
	/**
	 * 
	 * Places an oscillating blinker pattern (horizontal) at position provided. Wraps around if the values are out of bound.
	 * 
	 * @param x	The x coordinate of the leftmost part of the pattern.
	 * @param y The y coordinate of the topmost part of the pattern.
	 */	
	public void placeHBlinker(int x, int y) {
		safeSetCell(x, y, true);
		safeSetCell(x+1, y, true);
		safeSetCell(x+2, y, true);
	}
	
	/**
	 * 
	 * Places a spaceship glider pattern at position provided. Wraps around if the values are out of bound.
	 * 
	 * @param x	The x coordinate of the leftmost part of the pattern.
	 * @param y The y coordinate of the topmost part of the pattern.
	 */
	public void placeGlider(int x, int y) {
		placeHBlinker(x, y);
		safeSetCell(x+2, y-1, true);
		safeSetCell(x+1, y-2, true);
	}
	
	/**
	 * 
	 * Places an oscillating toad pattern (horizontal) at position provided. Wraps around if the values are out of bound.
	 * 
	 * @param x	The x coordinate of the leftmost part of the pattern.
	 * @param y The y coordinate of the topmost part of the pattern.
	 */
	public void placeToad(int x, int y) {
		placeHBlinker(x+1, y);
		placeHBlinker(x, y+1);
	}
}
