import java.awt.*;

public class CellularAutomata24_03 {
	
	public static void main (String[] args) {
		//Some nasty lines to get our GUI object:
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					try {
						Engine engine = new Engine(1, GUI.INITIAL_DIMENSION, 11); // initial state
						GUI Screen = new GUI(engine);
						engine.setGUI(Screen);
						engine.setNewGrid(Screen.getDimension());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}

}
