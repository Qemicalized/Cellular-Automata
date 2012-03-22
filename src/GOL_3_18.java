import java.awt.*;

public class GOL_3_18 {
	
	public static void main (String[] args) {
		//Some nasty lines to get our GUI object:
		EventQueue.invokeLater(new Runnable() {
			public void run() {
					try {
						Engine Engine = new Engine();
						GUI Screen = new GUI(Engine);
						Engine.setGUI(Screen);
						Engine.setNewGrid(Screen.getDimension());
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
	}

}
