
import java.util.ArrayList;


public class AlgorithmLangtonsAnt implements Algorithm {
	
	private Ant ant;
	private int noOfColors;
	
	public AlgorithmLangtonsAnt (int dimension, int noOfColors) {
		ant = new Ant(dimension);
		this.noOfColors = noOfColors;
	}
	
	public ArrayList<ChangelogItem> getNextGeneration(Grid g) {
		//We get some space for storing the row, column and color
		//of each cell, that has to be changed:
		ArrayList<ChangelogItem> arrList = new ArrayList<ChangelogItem>();
		int[][] squareArray = g.getSquareArray();
		ChangelogItem prev, next;
		int newCol;
		//Repainting the red spot, that showed, that ant is on a cell:
		newCol = ant.getNextColor(squareArray[ant.getYPosition()][ant.getXPosition()], noOfColors);
		squareArray[ant.getYPosition()][ant.getXPosition()] = newCol;
		prev = new ChangelogItem(ant.getYPosition(), ant.getXPosition(), newCol);
		//Getting to another cell:
		ant.getNextPosition(newCol, noOfColors);
		next = new ChangelogItem(ant.getYPosition(), ant.getXPosition(), -1);
		arrList.add(prev);
		arrList.add(next);
		//We increase the generation:
			g.setGeneration(g.getGeneration() + 1);
		return arrList;
	}

	
	
	//Basically, this method does the same thing as getNextGeneration method,
	//but this method does not return anything, because it is more efficient
	//just to repaint all grid (therefore after execution of this method, a
	//method paintAllGrid should follow):
	public void getGridAfterNGenerations(Grid g, int n) {
		for (int z = 0; z < n; z++)
			getNextGeneration(g);
	}
	
}