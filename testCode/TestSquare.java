/*
 * Extends ignotas square class to implement BooleanCell interface. I am thus able to treat it the 
 * same way as any other class implementing the same interface.
 */
package testCode;

import java.awt.Color;

import cellularAutomata.Square;
import acm.graphics.GRect;

public class TestSquare extends Square{
	/*
	 * Ignotas square class with minor modifications to make it fit a separate file. Please note that I 
	 * do not make use of this class directly but rather a subclass: SquareBooleanCell.
	 */

	/*
	 * lower case constants oO My bad.
	 */
	final static Color liveColor = Color.black;
	final static Color deadColor = Color.white;
	final static Color defaultFillColor = Color.white;
	final static Color defaultColor = Color.black;
	
	private GRect square; 
	
	public TestSquare (int x, int y, int squareSize, int widthFrameBorder, int heightFrameBorder) {
		square = new GRect(squareSize, squareSize); 
		square.setFilled(true);
		//Setting its location on screen:
		square.setLocation(widthFrameBorder + x * squareSize, heightFrameBorder + y * squareSize);
		//Setting its color:
		square.setColor(defaultColor);
		square.setFillColor(defaultFillColor);
	}
	
	/*
	 * Some automatically generated getters and setters to allow the variables to be private while 
	 * being accessed from outside the class. Really useful feature btw...
	 */
	public GRect getSquare() {
		return square;
	}
	
	public Color getColor () {
		return square.getFillColor();
	}
	
	public void changeColor (Color color) {
		square.setFillColor(color);
	}
	
	public TestSquare () {
		super();
	}

	public void setBool(boolean bool) {
		if (bool) {
			changeColor(liveColor);
		} else {
			changeColor(deadColor);
		}
	}


	public boolean getBool() {
		if (getSquare().getFillColor() == liveColor) {
			return true;
		}else  { // Assume any non live square to be dead for now.
			return false;
		}
	}
	
}
