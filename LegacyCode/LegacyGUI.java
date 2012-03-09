package LegacyCode;

import acm.program.*;

public class LegacyGUI extends GraphicsProgram {
	//--------------CONSTANTS-----------------------------
	final static int APP_WIDTH = 1000;
	final static int APP_HEIGHT = 800;
	final static int GRID_DIMENSION = 200;
	final static int SQUARE_SIZE = 5;
	final static int WIDTH_FRAME_BORDER = (APP_WIDTH - (GRID_DIMENSION * SQUARE_SIZE)) / 2;
	final static int HEIGHT_FRAME_BORDER = (APP_HEIGHT - (GRID_DIMENSION * SQUARE_SIZE)) / 2;

	LegacySquare[][] grid;
	
	public void run () {
		setSize(APP_WIDTH, APP_HEIGHT);
		grid = setUpScreen();
		executeAlgorithm();
	}
	
	private LegacySquare[][] setUpScreen() {
		grid = new LegacySquare[GRID_DIMENSION][GRID_DIMENSION];
		for (int x = 0 ; x < GRID_DIMENSION ; x++)
		{
			for (int y = 0 ; y < GRID_DIMENSION ; y++) {
				grid[x][y] = new LegacySquare(x, y, SQUARE_SIZE, WIDTH_FRAME_BORDER, HEIGHT_FRAME_BORDER);
				add(grid[x][y].getSquare());
			}
		}
		return grid;
	}

	private void executeAlgorithm () {
		LegacyGameOfLife gol = new  LegacyGameOfLife();
		gol.advance(grid, 1);
		gol.placeInfinite(48, 48);
		gol.placeBeacon(10, 10);
		
		for (int i = 0; i < 10000; i++) {
		gol.advance(grid, 1);
		}
	}
}
