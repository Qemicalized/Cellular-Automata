/*
 * Simple definition of an interface. The point is to allow my GameOfLife class to run on any 
 * grid of Squares as long as they implement this.
 */

package oldSource;

public interface BooleanCell {
	
	public void setLive(boolean bool);
	
	public boolean isLive();
	
}
