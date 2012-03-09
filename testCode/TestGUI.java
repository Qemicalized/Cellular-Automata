/*
 * I tried modifying Ignotas code to emulate our new design in order to see if my GameOfLife works,
 * even though it looks like a contribution to the WCC in code obfuscation. See comments below for instructins for use.
 */

package testCode;

import acm.program.*;

public class TestGUI extends GraphicsProgram {
	final static int APP_WIDTH = 1000;
	final static int APP_HEIGHT = 800;
	final static int GRID_DIMENSION = 200;
	final static int SQUARE_SIZE = 5;
	final static int WIDTH_FRAME_BORDER = (APP_WIDTH - (GRID_DIMENSION * SQUARE_SIZE)) / 2;
	final static int HEIGHT_FRAME_BORDER = (APP_HEIGHT - (GRID_DIMENSION * SQUARE_SIZE)) / 2;

	TestSquare[][] grid;
	TestGeneralAlgorithm algorithm;
	
	public void run () {
		setSize(APP_WIDTH, APP_HEIGHT);
		grid = setUpScreen();
		
		/*
		 * Initialize your algorithm below.
		 */
		algorithm = new TestGameOfLife();
		executeAlgorithm();
	}
	
	private TestSquare[][] setUpScreen() {
		grid = new TestSquare[GRID_DIMENSION][GRID_DIMENSION];
		for (int x = 0 ; x < GRID_DIMENSION ; x++)
		{
			for (int y = 0 ; y < GRID_DIMENSION ; y++) {
				grid[x][y] = new TestSquare(x, y, SQUARE_SIZE, WIDTH_FRAME_BORDER, HEIGHT_FRAME_BORDER);
				add(grid[x][y].getSquare());
			}
		}
		return grid;
	}

	private void executeAlgorithm () {
		/*
		 * Modify this section to suit your testing. My code is bugged so I must advance my 
		 * simulation to connect it to the grid, before seting up the grid with patterns
		 * and lastly performing the simulation. 
		 */
		algorithm.advance(grid, 1);
		((TestGameOfLife) algorithm).placeInfinite(48, 48);
		((TestGameOfLife) algorithm).placeBeacon(10, 10);
		
		for (int i = 0; i < 10000; i++) {
			algorithm.advance(grid, 1);
			//add wait statement here.
		}
	}
}
