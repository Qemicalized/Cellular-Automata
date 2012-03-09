/*
 * Once again not a complete class
 */
public class Square implements  InterfaceBooleanProperties, InterfaceSomeOther {

	private int color; //This may or may not be an int, but it serves my demonstrationpurposes.
	
	@Override
	public void setBool(boolean bool) {
		/*
		 * Handles the translation of boolean to the internal representation (Color, int, whatever we decide)
		 */
		int BLACK = 0x000000;
		int WHITE = 0xFFFFFF;
		color = bool ? BLACK : WHITE;

	}

	@Override
	public boolean getBool() {
		int WHITE = 0xFFFFFF;
		return color == WHITE;
	}
}
