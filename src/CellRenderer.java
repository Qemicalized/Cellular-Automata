
import java.awt.Component;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;


public class CellRenderer extends DefaultTableCellRenderer {
	
	private GUI g;
	
	public void setGUI (GUI gui) {
		this.g = gui;
	}

	private static final long serialVersionUID = 1L;

	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) { // we do nothing with //isSelected & hasFocus, I've also removed the functionality of these
		
		Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		if (value instanceof Integer) {

			cell.setBackground(g.getColorSchemeColor((Integer) value));
			setText("");
		}
		return cell;
	}
}