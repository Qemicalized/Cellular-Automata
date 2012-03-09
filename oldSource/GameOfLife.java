/*
 * 	The idea of OOP is that you should neither have to modify this file nor read it. But seeing that
 * we all have much to learn, please read it through and tell me if I'm doing anything in an unclear
 * manner (eg commenting hehe).
 * 
 * 	A class that once initialized handles the simulation of a game of life.
 * 	Takes a two-dimensional array of objects (cells) that implements BooleanCell. Lacking a better 
 * word I'm calling this grid 'surface' for now.
 * 
 * 	Once initialized the class allow the user to step the simulation forward and to modify the world
 * by setting the state of cells and adding predefined patterns
 */
package oldSource;

public class GameOfLife {
	final private int LAYERS = 2;
	/*
	 * 	Currently used as a constant.
	 * 	The idea of layers is to have the changes hopping inbetween them, 
	 * changing the surface as they go.
	 * 	Can be increased if backtracking or history is to be implemented.
	 * 	Potentially useful for threading?
	 */
	
	private int width;
	private int height;
	
	private int currentGeneration=0;
	private int currentLayer=0;
	private int nextLayer = 1;
	/*
	 * 	I use these variables to keep track of what layer to read from and what layer to write to,
	 * recalculating them only once per generation.
	 */
	
	private boolean[][][] grid;
	/*
	 * The internal grid that will represent the world. First dimension is of the different layers.
	 */
	
	private BooleanCell[][] surface;
	/*
	 * 	The grid that will be shared with the GUI and which will be the only mean of output for the 
	 * class. Keep in mind that it is passed by reference! BooleanCell is the interface that every 
	 * object (cell) of in the grid will need to implement.
	 */
	
	/**
	 * Initializes the GameOfLife simulation to match the provided fixed size grid. 
	 * @param surface A two-dimensional array of any object that implements BooleanCell. Can be any size.
	 */
	public GameOfLife(BooleanCell[][] surface) {
		/*
		 * 	Oups, the constructor assumes that the grid passed in is empty and this whole class will
		 * act as if that was the case. Only effect is that cells that appear to exist doesn't, ie
		 * it won't affect stability.
		 * 
		 * 	The rest should be obvious, its pretty much the same stuff as what eclipse does automatically
		 * for you...
		 */
		this.width = surface.length;
		this.height = surface.length;
		this.surface = surface;
		this.grid = new boolean[LAYERS][width][height];
	}
	
	private void setCell(int x, int y, boolean bool) {
		/*
		 * Not failsafe, thus private, method to change state of cell at given coordinates.
		 * Changes the state of the cell both internally and on the surface.
		 */
		grid[nextLayer][x][y] = bool;
		surface[x][y].setLive(bool);
	}
	
	private void carryCell(int x, int y) {
		//... oh bother.
		grid[nextLayer][x][y] = grid[currentLayer][x][y];
	}
	
	private void incrementGen() {
		/*
		 * Increments the generation, and updates layer variables accordingly. Called every time 
		 * the simulation advances one generation.
		 */
		currentGeneration++;
		currentLayer = nextLayer;
		nextLayer = (nextLayer+1)%LAYERS;
	}
	
	private int countLiveNeighbours(int x, int y) {
		/*
		 * 	Counts the number of live neighbours to the cell at coordinates provided. I might have 
		 * sacrificed some computational speed for code lines.
		 * 	Does not try to access squares outside of the grid defines by [width][height]
		 *	 Counts [0] as adjacent to [width]
		 */
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
		/*
		 * Advances the simulation one generation using standard game of life rules. Changes are 
		 * made both to internal grid and to the surface.
		 */
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
	public void fasForward(int count) {
		/*
		 * Pretty much the same code as stepForward(), bad practice? Can't think of another way atm.
		 * FastForward should speed the simulation up pretty good since it only updates the surface
		 * once.
		 */
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
		/*
		 * 	Same as set cell, but with build in safe guard for bad values: too high or low values
		 * wraps around and are kept within bound.
		 */
		x = (x +width)%width;
		y = (y +height)%height;
		grid[currentGeneration][x][y] = state;
		surface[x][y].setLive(state);
	}
		
	/**
	 * 
	 * Places a pattern that will expand infinitely, provided an infinite grid, at position provided. Wraps around if the values are out of bound.
	 * 
	 * @param x	The x coordinate of the leftmost part of the pattern.
	 * @param y The y coordinate of the topmost part of the pattern.
	 */
	public void placeInfinite(int x, int y) {
		/*
		 * 	I wanted to try out my class on more than just blank backrgounds so I made a couple of
		 * patterns. See wikipedia.
		 */
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
