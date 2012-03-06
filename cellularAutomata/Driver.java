/*
 * 	Based of Ignotas original code this is the class that is executed and which handles the GUI and 
 * 'drives' the algorithms, ie sets them up, controls stepping forward, timing etc. To be replaced
 * by Silvers GUI.
 * 
 * 	My changes are limited to separating and extending the Square class, adding a method to drive my
 * algorithm and changing the default case for which algorith is run.
 */

package cellularAutomata;

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class Driver extends GraphicsProgram {
	//--------------CONSTANTS-----------------------------
	final static int APP_WIDTH = 1000;
	final static int APP_HEIGHT = 800;
	final static int GRID_DIMENSION = 200;
	final static int SQUARE_SIZE = 5;
	final static int WIDTH_FRAME_BORDER = (APP_WIDTH - (GRID_DIMENSION * SQUARE_SIZE)) / 2;
	final static int HEIGHT_FRAME_BORDER = (APP_HEIGHT - (GRID_DIMENSION * SQUARE_SIZE)) / 2;

	public void run () {
		//At first we create the graphics window:
		setSize(APP_WIDTH, APP_HEIGHT);
		//Setting up the screen and getting the array of squares,
		//which we will later manipulate:
		SquareBooleanCell[][] grid = new SquareBooleanCell[GRID_DIMENSION][GRID_DIMENSION];
		grid = setUpScreen();
		//At this point we should start executing an algorithm,
		//that "paints" the grid.
		executeAlgorithm(1, grid);
	}
	//-------------METHODS---------------------------------
	
	//Setting up the screen: filling it with white squares:
	private SquareBooleanCell[][] setUpScreen() {
		SquareBooleanCell[][] grid = new SquareBooleanCell[GRID_DIMENSION][GRID_DIMENSION];
		for (int x = 0 ; x < GRID_DIMENSION ; x++)
		{
			for (int y = 0 ; y < GRID_DIMENSION ; y++) {
				//Creating the square:
				grid[x][y] = new SquareBooleanCell(x, y, SQUARE_SIZE, WIDTH_FRAME_BORDER, HEIGHT_FRAME_BORDER);
				/*
				 * Moved to the Square constructor if I remember correctly. I really should start 
				 * commenting when I make changes, not two weeks later ... 
				grid[x][y].square.setFilled(true);
				//Setting its location on screen:
				grid[x][y].square.setLocation(WIDTH_FRAME_BORDER + x * SQUARE_SIZE, HEIGHT_FRAME_BORDER + y * SQUARE_SIZE);
				//Setting its color:
				grid[x][y].square.setColor(Color.black);
				grid[x][y].square.setFillColor(Color.white);
				*/
				add(grid[x][y].getSquare());
			}
		}
		return grid;
	}
	

	private void executeAlgorithm (int x, SquareBooleanCell[][] grid)
	{
		//Here we use switch to execute the algorithm,
		//that was chosen:
		switch (x) {
		 case 0:
			 //langtonsAnt(grid);
			 break;
		 case 1:
			 driveGameOfLife(grid);
			 break;
		 //New cases with algorithms will be added here:
		 //
		 //
		 //
		}
	}
	
	private void driveGameOfLife(SquareBooleanCell[][] grid) {
		 GameOfLife gol = new GameOfLife(grid);
		 gol.placeInfinite(48, 48);
		 gol.placeBeacon(10, 10);
		 
		 for (int i = 0; i < 10000; i++) {
				gol.stepForward();
		 }
	}
}
