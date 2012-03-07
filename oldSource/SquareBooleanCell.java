/*
 * Extends ignotas square class to implement BooleanCell interface. I am thus able to treat it the 
 * same way as any other class implementing the same interface.
 */
package oldSource;

public class SquareBooleanCell extends Square implements BooleanCell{
	public SquareBooleanCell (int x, int y, int squareSize, int widthFrameBorder, int heightFrameBorder) {
		super(x, y, squareSize, widthFrameBorder, heightFrameBorder);
	}
	
	public SquareBooleanCell () {
		super();
	}

	@Override
	public void setLive(boolean bool) {
		if (bool) {
			changeColor(liveColor);
		} else {
			changeColor(deadColor);
		}
	}


	@Override
	public boolean isLive() {
		if (getSquare().getFillColor() == liveColor) {
			return true;
		}else  { // Assume any non live square to be dead for now.
			return false;
		}
	}
	
}
