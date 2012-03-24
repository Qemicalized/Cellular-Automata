import java.util.ArrayList;

/**
 * @author Anders
 *
 */
/*
 * This class does nothing in itself, it is after all abstract, but provides some tools 
 * for quick implementation of further algorithms where the future of one cell only depend
 * on its of state and the number of neighbours (in the closest neighbourhood) of a certain
 * state, such as Game of Life and Brian's Brain.
 * 
 * I would like to point out that this class in in no way final and that it so far has only 
 * been tested with my text output testclass.
 */
public abstract class FOTCA implements Algorithm{
	
	private int high;
	private int low;
	private int size;
	
	private int numberOfRows;
	private int numberOfColumns;
	
	private int currentGeneration;
	
	private int[][] surface;
	private int[][] bouncingWall;
	
	private ArrayList<ChangelogItem> changeLog;
	private boolean changelogEnabled;
	
	public FOTCA(int numberOfColors) {
		this.size = numberOfColors;
		this.high = this.size-1;
		this.low = 0;
		
		this.currentGeneration = 1;
		
		this.numberOfRows = 0;
		this.numberOfColumns = 0;
		this.surface = new int[numberOfRows][numberOfColumns];
		this.bouncingWall = new int[numberOfRows][numberOfColumns];
		
		this.changeLog = new ArrayList<ChangelogItem>();
	}
	
	/* * ** *** ***** ******** ************* *********************
	 * Methods for getting information about the current generation;
	 */
	protected int countHighNeighbours(int row, int column) {
		int state=high;
		int count = 0;
		if (surface[row][column] == state) {
			count--;
		}
		int r = 1;
		for (int i = -r; i <= r; i++) {
			for (int j = -r; j <= r ; j++) {
				if (bouncingWall[(row+i+numberOfRows)%numberOfRows][(column+j+numberOfColumns)%numberOfColumns] == state) {
					count++;
				}
			}
		}
		if (count < 0) {
			return 0;
		}
		return count;
	}
	
	protected int getState(int row, int column) {
		return bouncingWall[row][column];
	}
	
	protected boolean isStateLow(int row, int column) {
		return bouncingWall[row][column] == low;
	}
	
	/* * ** *** ***** ******** ************* *********************
	 * Methods for modifying the state of the next generation
	 */
	protected void carryState(int row, int column) {
		//This method now does nothing as the bouncing wall and the surface start out identical
		//surface[row][column] = bouncingWall[row][column];
		//No change occurs and so nothing is added to the changeLog
	}
	
	protected void decayState(int row, int column) {
		if (bouncingWall[row][column] > 0) {
			surface[row][column] = bouncingWall[row][column]-1;
			if (changelogEnabled) {
				this.changeLog.add(new ChangelogItem(row, column, surface[row][column]));
			}
		}else {
			carryState(row, column);
		}
	}
	
	protected void setStateHigh(int row, int column) {
		surface[row][column] = high;
		if (changelogEnabled) {
			this.changeLog.add(new ChangelogItem(row, column, high));
		}
	}
	
	/* * ** *** ***** ******** ************* *********************
	 * Methods for driving the simulation
	 */
	private void stepForward() {
		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				bouncingWall[i][j] = surface[i][j];
			}
		}
		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				applyRule(i, j);
			}
		}
		currentGeneration++;
	}
	
	/*
	 * I leave this abstract, forcing any subclass to set its own rules.
	 */
	abstract protected void applyRule(int row, int column);
	
	/* * ** *** ***** ******** ************* *********************
	 * Development methods
	 */
	public void print() {
		System.out.println(currentGeneration +":");
		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				System.out.print(String.format("%X", surface[i][j]) + " ");
			}
			System.out.println();
		}
	}
	
	public void resize(int rows, int columns) {
		int[][] temp = surface;
		this.numberOfRows = rows;
		this.numberOfColumns = columns;
		this.surface = new int[numberOfRows][numberOfColumns];
		this.bouncingWall = new int[numberOfRows][numberOfColumns];
		for (int i = 0; i < temp.length && i< numberOfRows; i++) {
			for (int j = 0; j < temp[i].length && j < numberOfColumns; j++) {
				surface[i][j] = temp[i][j];
			}
		}
	}
	
	public void setAllLow() {
		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				surface[i][j] = low;
			}
		}
	}
	
	public void clearChangelog() {
		changeLog.clear();
	}
	
	private void changeState(int row, int column, int state) {
		//Attempts to keep the change log as short as possible
		//if (medium[currentLayer][row][column] != state) {
			surface[row][column] = state;
		//	this.changeLog.add(new ChangelogItem(row, column, state));
		//}else {
		//	carryState(row, column);
		//}
	}
	
	public void addBlock(int row, int column) {
		changeState(row+0, column+0, high);
		changeState(row+0, column+1, high);
		changeState(row+0, column+2, high);
		changeState(row+0, column+3, high);
		
		changeState(row+1, column+0, high);
		changeState(row+1, column+1, high);
		changeState(row+1, column+2, high);
		changeState(row+1, column+3, high);
		
		changeState(row+2, column+0, high);
		changeState(row+2, column+1, high);
		changeState(row+2, column+2, high);
		changeState(row+2, column+3, high);
		
		changeState(row+3, column+0, high);
		changeState(row+3, column+1, high);
		changeState(row+3, column+2, high);
		changeState(row+3, column+3, high);
	}
	
	public void addVerticalComet(int row, int column) {
		//changeState(row+0, column+0, high-1);
		changeState(row+0, column+1, low);
		changeState(row+0, column+2, low);
		//changeState(row+0, column+3, high-1);
		
		changeState(row+1, column+0, high);
		changeState(row+1, column+1, high-1);
		changeState(row+1, column+2, high-1);
		changeState(row+1, column+3, high);
		
		changeState(row+2, column+0, low);
		changeState(row+2, column+1, high);
		changeState(row+2, column+2, high);
		changeState(row+2, column+3, low);
	}
	
	public void addGlider(int row, int column) {
		changeState(row+0, column+0, low);
		changeState(row+0, column+1, high);
		changeState(row+0, column+2, low);
		
		
		changeState(row+1, column+0, low);
		changeState(row+1, column+1, low);
		changeState(row+1, column+2, high);
				
		changeState(row+2, column+0, high);
		changeState(row+2, column+1, high);
		changeState(row+2, column+2, high);
	}
	
	public int[][] getGrid() {
		return surface;
	}
	
	private void setupMedium(int[][] grid) {
		this.numberOfRows = grid.length;
		this.numberOfColumns = grid[0].length;
		this.surface = grid;
		this.bouncingWall = new int[numberOfRows][numberOfColumns];
	}
	

	/* * ** *** ***** ******** ************* *********************
	 * Methods required by the interface
	 */
	
	/* (non-Javadoc)
	 * @see Algorithm#getNextGeneration(Grid)
	 */
	@Override
	public ArrayList<ChangelogItem> getNextGeneration(Grid grid) {
		clearChangelog();
		setupMedium(grid.getSquareArray());
		stepForward();
		return changeLog;
	}

	/* (non-Javadoc)
	 * @see Algorithm#getGridAfterNGenerations(Grid, int)
	 */
	@Override
	public void getGridAfterNGenerations(Grid grid, int n) {
		setupMedium(grid.getSquareArray());
		for (int i = 0; i < n; i++) {
			stepForward();
		}
	}

}
