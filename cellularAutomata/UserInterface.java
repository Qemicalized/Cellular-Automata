package cellularAutomata;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Canvas;
import java.awt.Panel;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.UIManager;


public class UserInterface {

	private JFrame frame;

	/*
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/*
	 * Create the application.
	 */
	public UserInterface() {
		initialize();
	}

	/*
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 960, 800); // x, y (coordinates of window), width, height (of the window)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(10, 11, 274, 640);
		frame.getContentPane().add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		Label label = new Label("Algorithms");
		label.setAlignment(Label.RIGHT);
		label.setForeground(Color.LIGHT_GRAY);
		label.setFont(new Font("Myriad Pro", Font.PLAIN, 29));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.NORTHWEST;
		gbc_label.insets = new Insets(0, 0, 22, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		panel.add(label, gbc_label);
		
		JButton btnGameOfLife = new JButton("Game of Life");
		GridBagConstraints gbc_btnGameOfLife = new GridBagConstraints();
		gbc_btnGameOfLife.fill = GridBagConstraints.BOTH;
		gbc_btnGameOfLife.insets = new Insets(0, 0, 11, 0);
		gbc_btnGameOfLife.gridx = 0;
		gbc_btnGameOfLife.gridy = 1;
		panel.add(btnGameOfLife, gbc_btnGameOfLife);
		
		JButton btnLangtonsAnt = new JButton("Langton's Ant");
		GridBagConstraints gbc_btnLangtonsAnt = new GridBagConstraints();
		gbc_btnLangtonsAnt.fill = GridBagConstraints.BOTH;
		gbc_btnLangtonsAnt.insets = new Insets(0, 0, 11, 0);
		gbc_btnLangtonsAnt.gridx = 0;
		gbc_btnLangtonsAnt.gridy = 2;
		panel.add(btnLangtonsAnt, gbc_btnLangtonsAnt);
		
		JButton btnRacetrack = new JButton("Racetrack");
		GridBagConstraints gbc_btnRacetrack = new GridBagConstraints();
		gbc_btnRacetrack.fill = GridBagConstraints.BOTH;
		gbc_btnRacetrack.insets = new Insets(0, 0, 11, 0);
		gbc_btnRacetrack.gridx = 0;
		gbc_btnRacetrack.gridy = 3;
		panel.add(btnRacetrack, gbc_btnRacetrack);
		
		JButton btnSeeds = new JButton("Seeds");
		GridBagConstraints gbc_btnSeeds = new GridBagConstraints();
		gbc_btnSeeds.fill = GridBagConstraints.BOTH;
		gbc_btnSeeds.insets = new Insets(0, 0, 11, 0);
		gbc_btnSeeds.gridx = 0;
		gbc_btnSeeds.gridy = 4;
		panel.add(btnSeeds, gbc_btnSeeds);
		
		JButton btnd = new JButton("3D");
		GridBagConstraints gbc_btnd = new GridBagConstraints();
		gbc_btnd.fill = GridBagConstraints.BOTH;
		gbc_btnd.gridx = 0;
		gbc_btnd.gridy = 5;
		panel.add(btnd, gbc_btnd);
		
		Canvas canvas = new Canvas();
		canvas.setBackground(Color.GRAY);
		canvas.setBounds(294, 11, 640, 640);
		frame.getContentPane().add(canvas);
	}
}
