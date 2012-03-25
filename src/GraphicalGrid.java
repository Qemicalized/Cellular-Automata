import java.awt.Color;
import javax.swing.*;

public class GraphicalGrid {
	
	public JTable gGrid;
	
	public GraphicalGrid (int dimension) {
		gGrid = new JTable(dimension, dimension);
		gGrid.setRowHeight((int) Math.floor(GUI.GRID_SIZE_IN_PIXELS/dimension));
		for (int x = 0 ; x < dimension ; x ++)
			((gGrid.getColumnModel()).getColumn(x)).setMinWidth((int) Math.floor(640/dimension));
		gGrid.setBackground(Color.DARK_GRAY);
		gGrid.setForeground(Color.LIGHT_GRAY);
		gGrid.setEnabled(false);
		gGrid.setGridColor(new Color(51, 51, 51));
	}
	

}
