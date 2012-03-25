
public class Ant {
	private int pos_x, pos_y;
	private int dimension;
	private String direction;
	
	public Ant (int dimension) {
		this.dimension = dimension;
		pos_x = dimension/2;
		pos_y = dimension/2;
		direction = "n";
	}
	
	public int getXPosition () {
		return pos_x;
	}
	
	public int getYPosition () {
		return pos_y;
	}
	
	public void getNextPosition(int col, int numOfCol) {
		if (numOfCol == 2) {
			switch (col) {
			case 0: turnLeft();
				break;
			default: turnRight();
				break;
			}
		}
		else if (numOfCol == 3) {
			switch (col) {
			case 0: turnRight();
				break;
			case 1: turnLeft();
				break;
			default: turnRight();
				break;
			}
		}
		else if (numOfCol == 4) {
			switch (col) {
			case 0: turnRight();
				break;
			case 1: turnLeft();
				break;
			case 2: turnLeft();
				break;
			default: turnRight();
				break;
			}
		}
		else if (numOfCol == 5) {
			switch (col) {
			case 0: turnRight();
				break;
			case 1: turnLeft();
				break;
			case 2: turnLeft();
				break;
			default: turnRight();
				break;
			}
		}
		else if (numOfCol == 11) {
			switch (col) {
			case 0: turnRight();
				break;
			case 1: turnLeft();
				break;
			case 2: turnRight();
				break;
			case 3: turnRight();
				break;
			case 4: turnRight();
				break;
			case 5: turnRight();
				break;
			case 6: turnLeft();
				break;
			case 7: turnLeft();
				break;
			case 8: turnLeft();
				break;
			case 9: turnRight();
				break;
			default: turnRight();
				break;
			}
		}
		move();
		
	}
	
	private void turnRight () {
		switch (direction) {
		case "n": direction = "e";
			break;
		case "e": direction = "s";
			break;
		case "s": direction = "w";
			break;
		case "w": direction = "n";
			break;
		}	
	}
	
	private void turnLeft () {
		turnRight();
		turnRight();
		turnRight();
	}
	
	private void move() {
		switch (direction) {
		case "n": pos_y = (dimension + pos_y - 1) % dimension;
			break;
		case "e": pos_x = (dimension + pos_x + 1) % dimension;
			break;
		case "s": pos_y = (dimension + pos_y + 1) % dimension;
			break;
		case "w": pos_x = (dimension + pos_x - 1) % dimension;
			break;
		}	
	}
	
	public int getNextColor(int color, int numOfColors) {
		return ((color + 1) % numOfColors);
	}
	
}
