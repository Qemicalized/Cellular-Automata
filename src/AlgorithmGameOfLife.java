import java.util.ArrayList;


public class AlgorithmGameOfLife implements Algorithm {
	
	public ArrayList<ChangelogItem> getNextGeneration(Grid g) {
		//For classic game of life algorithm we need to have
		//a randomised grid at the beginning or else nothing happens:
		if (g.getGeneration() == 0)
			//g.setGridRandomColors(2);
			g.setGridLineOfTen();
		//We get some space for storing the row, column and color
		//of each cell, that has to be changed:
		ArrayList<ChangelogItem> arrList = new ArrayList<ChangelogItem>();
		ChangelogItem a;
		int liveSquares;
		//We loop over every cell:
		for (int y = 0 ; y < g.getDimension() ; y++ )
			for (int x = 0 ; x < g.getDimension() ; x++)
			{
				//We get the number of live squares, surrounding
				//the square in y-th row and x-th column of grid g:
				liveSquares = countLiveSquares(g, y, x);
				//If it's off:
				if (g.getSquareArray()[y][x] == 0) { 
					//But should be on:
					if (liveSquares == 2 || liveSquares == 3) {
						a = new ChangelogItem(y, x, 1);
						arrList.add(a);
					}
				}
				//If it's on:
				else
					//And should be off:
					if (liveSquares != 2 && liveSquares != 3) {
						a = new ChangelogItem(y, x, 0);
						arrList.add(a);
					}
			}
		//Updating our grid:
		for (int u = 0 ; u < arrList.size() ; u++) {
			a = arrList.get(u);
			g.setSquareArray(a.getRow(), a.getColumn(), a.getNewState());
		}
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
	
	//Method, that counts live squares:
	private int countLiveSquares(Grid g, int y, int x) {
		int[][] squares = g.getSquareArray();
		int count = - squares[y][x];
		for (int u = -1 ; u < 2 ; u++)
			for (int i = -1; i < 2 ; i++)
				if (squares[(y + u + g.getDimension()) % g.getDimension()][(x + i + g.getDimension()) % g.getDimension()] == 1)
					count++;
		return count;
	}
}
