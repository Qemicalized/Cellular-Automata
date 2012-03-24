import java.util.ArrayList;


public interface Algorithm {
	
	//Methods:
	
	//Gets next generation of the grid:
	public ArrayList<ChangelogItem> getNextGeneration(Grid g);
	
	//Gets n-th generation of the grid:
	public void getGridAfterNGenerations(Grid g, int n);

}
