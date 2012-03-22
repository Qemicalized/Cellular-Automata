import java.util.Random;




public class Grid {
	//Each grid has its 2d array of squares:
	private int[][] squareArray;
	//Each grid has its current generation number:
	private int generation = 0;
	private int dimension;
	
	//Constructor for new grid object
	public Grid (int dimension) {
		//Basically, there should be a loop, that instantiates all of the square objects
		//that we need for our grid object
		this.dimension = dimension;
		squareArray = new int[dimension][dimension];
	}
	
	
	//Method for setting values of individual squares in array:
	//(Invoked by Algorithm):
	public void setSquareArray(int y, int x, int z) {
		squareArray[y][x] = z;
	}
	
	//Method for randomizing the grid:
	public void setGridRandomColors(int c) {
		Random rGen = new Random();
		for (int y = 0 ; y < dimension ; y++)
			for (int x = 0 ; x < dimension ; x++)
				squareArray[y][x] = rGen.nextInt(c);
	}
	
	
	//Sets plain grid - every entry contains a 0:
	public void setGridPlain() {
		for (int y = 0 ; y < dimension ; y++)
			for (int x = 0 ; x < dimension ; x++)
				squareArray[y][x] = 0;
	}

	
	
	//Setter for the current generation:	
	public void setGeneration (int g) {
		generation = g;
	}
	
	
	
	//Getter method for giving grid's current generation:
	public int getGeneration () {
		return generation;
	}
	
	//Getter for the grid of this object:
	public int[][] getSquareArray() {
		return squareArray; 
	}
	
	//Getter for the dimension of this grid:
	public int getDimension () {
		return dimension;
	}

}