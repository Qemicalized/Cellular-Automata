
public class ChangelogItem {

	// The members are left public for ease of access.
	public int row;
	public int column;
	public int newState;
	
	/**
	 * 
	 * @param row The number of the row (y-coordinate) on which the change occurs.
	 * @param column  The number of the column (x-coordinate) on which the change occurs.
	 * @param newState The new state of the cell at the given coordinates.
	 * @return
	 */
	public ChangelogItem(int row, int column, int newState) {
		this.row = row;
		this.column = column;
		this.newState = newState;
	}
	
	/**
	 * A no parameter constructor that leaves all values to their default (0)
	 * Use when specifying the values at initialisation is impractical
	 */
	public ChangelogItem() {
		
	}
	
	public int getRow() {
		return row;
	}
	/**
	 * @param set the row(y-coordinate) to row
	 */

	public void setRow(int row) {
		this.row = row;
	}
	/**
	 * @return the column(x-coordinate)
	 */
	public int getColumn() {
		return column;
	}
	/**
	 * @param set the column (x-coordinate) to column
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	/**
	 * @return the newState
	 */
	public int getNewState() {
		return newState;
	}
	/**
	 * @param set the newState to newState
	 */
	public void setNewState(int newState) {
		this.newState = newState;
	}
	
}
