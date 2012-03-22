package cellularAutomata;

public class Grid {
	//Each grid has its 2d array of squares:
	private Square[][] grid;
	//Each grid has its current generation number:
	private int generation = 0;
	//Grid's current color scheme:
	private int colorScheme;
	//Number of colors picked to be used in chosen algorithm:
	private int numberOfColors;
	
	//Constructor for new grid object
	public Grid () {
		//Basically, there should be a loop, that instantiates all of the square objects
		//that we need for our grid object
	}
	
	
	//Getter for the grid of this object:
	public Square[][] getGrid() {
		return grid; 
	}
	public void changeDimension() {
		/*
		 * Changes the dimension of the grid in whatever way we decide is good
		 * if at all we decide we want this feature
		 * !!! We must remember, that when the dimension is changed, we need to throw away the old grid
		 * ,wipe out every square piece that was drawn on screen and get an instance of new grid
		 * with proper dimension */
	}
	
	public void randomise() {
		//We should have the method, that randomises the colors of squares, because
		//that is the way some algorithms start off their execution
	}
	
	
	public void redrawItself () {
		//A grid should know how to redraw itself
		//If we are calling this method on some grid and not specifying any parameters,
		//then it should completely redraw itself (set colors of ALL squares to their current value)
	}
	
	public void redrawItself (int array) {
		//This function does the same thing as previously described function, but it is
		//setting colors of some specific squares indicated in "array". It is practical to use
		//this function in most cases, because usually only a small number of squares change color
		//in each generation, so by specifying only those squares that need to be redrawn, we
		//save a lot of computing resources
	}
	
	//Getter method for giving grid's current generation:
	public int getGeneration () {
		return generation;
	}
	
	//Getter method for giving grid's color scheme 
	public int getNumberOfColors () {
		return numberOfColors;
	}
}
