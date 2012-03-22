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

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.JToggleButton;
import javax.swing.JSlider;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import javax.swing.JRadioButton;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;


public class GUI {

	public static final int GRID_SIZE_IN_PIXELS = 640;
	private Engine eng; 
	private JFrame frame;
	private JTextField dimensionTF;
	private JTextField generationTF;
	private GraphicalGrid graphicalG;
	private int dimension = 160;
	private int generation = 0;
	private int colorScheme = 1;

	/*
	 * Create the application.
	 */
	public GUI(Engine eng) {
		this.eng = eng;
		graphicalG = new GraphicalGrid(dimension);
		initialize();
		initialiseGridOnScreen();
		frame.setVisible(true);
	}

	/*
	 * Initialise the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.setBounds(100, 100, 960, 800); // x, y (coordinates of window), width, height (of the window)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel algorithmsPanel = new JPanel();
		algorithmsPanel.setBackground(Color.DARK_GRAY);
		algorithmsPanel.setBounds(10, 11, 274, 640);
		frame.getContentPane().add(algorithmsPanel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		algorithmsPanel.setLayout(gbl_panel);
		
		Label label = new Label("Algorithms");
		label.setAlignment(Label.RIGHT);
		label.setForeground(Color.LIGHT_GRAY);
		label.setFont(new Font("Myriad Pro", Font.PLAIN, 29));
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.NORTHWEST;
		gbc_label.insets = new Insets(0, 0, 22, 0);
		gbc_label.gridx = 0;
		gbc_label.gridy = 0;
		algorithmsPanel.add(label, gbc_label);
		
		/*
		 * Loads all of the pictures for buttons.
		 */
		ClassLoader cl = this.getClass().getClassLoader();
		ImageIcon gameOfLife = new ImageIcon(cl.getResource("gameoflife.png"));
		ImageIcon langtonsAnt = new ImageIcon(cl.getResource("langton.png"));
		ImageIcon racetrack = new ImageIcon(cl.getResource("racetrack.png"));
		ImageIcon seeds = new ImageIcon(cl.getResource("seeds.png"));
		ImageIcon d = new ImageIcon(cl.getResource("3d.png"));
		ImageIcon enabled_temp = new ImageIcon(cl.getResource("deselect_temp.jpg"));
		
		/*
		 * Initialises buttons.
		 */
		final JRadioButton rdbtnGameOfLife = new JRadioButton(gameOfLife);
		rdbtnGameOfLife.setSelectedIcon(enabled_temp);
		rdbtnGameOfLife.setText("Game of Life");
		rdbtnGameOfLife.setSelected(true);
		GridBagConstraints gbc_rdbtnGameOfLife = new GridBagConstraints();
		gbc_rdbtnGameOfLife.fill = GridBagConstraints.BOTH;
		gbc_rdbtnGameOfLife.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnGameOfLife.gridx = 0;
		gbc_rdbtnGameOfLife.gridy = 1;
		
		final JRadioButton rdbtnLangtonsAnt = new JRadioButton(langtonsAnt);
		rdbtnLangtonsAnt.setSelectedIcon(enabled_temp);
		rdbtnLangtonsAnt.setText("Langton's Ant");
		GridBagConstraints gbc_rdbtnLangtonsAnt = new GridBagConstraints();
		gbc_rdbtnLangtonsAnt.fill = GridBagConstraints.BOTH;
		gbc_rdbtnLangtonsAnt.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnLangtonsAnt.gridx = 0;
		gbc_rdbtnLangtonsAnt.gridy = 2;
		
		final JRadioButton rdbtnRacetrack = new JRadioButton(racetrack);
		rdbtnRacetrack.setSelectedIcon(enabled_temp);
		rdbtnRacetrack.setText("Racetrack");
		GridBagConstraints gbc_rdbtnRacetrack = new GridBagConstraints();
		gbc_rdbtnRacetrack.fill = GridBagConstraints.BOTH;
		gbc_rdbtnRacetrack.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnRacetrack.gridx = 0;
		gbc_rdbtnRacetrack.gridy = 3;
		
		final JRadioButton rdbtnSeeds = new JRadioButton(seeds);
		rdbtnSeeds.setSelectedIcon(enabled_temp);
		rdbtnSeeds.setText("Seeds");
		GridBagConstraints gbc_rdbtnSeeds = new GridBagConstraints();
		gbc_rdbtnSeeds.fill = GridBagConstraints.BOTH;
		gbc_rdbtnSeeds.insets = new Insets(0, 0, 5, 0);
		gbc_rdbtnSeeds.gridx = 0;
		gbc_rdbtnSeeds.gridy = 4;
		
		final JRadioButton rdbtnd = new JRadioButton(d);
		rdbtnd.setSelectedIcon(enabled_temp);
		rdbtnd.setText("3D");
		GridBagConstraints gbc_rdbtnd = new GridBagConstraints();
		gbc_rdbtnd.fill = GridBagConstraints.BOTH;
		gbc_rdbtnd.gridx = 0;
		gbc_rdbtnd.gridy = 5;
		
		/*
		 * Adds the buttons to a group for correct radio button behaviour.
		 */
		 ButtonGroup algorithmGroup = new ButtonGroup();
		    algorithmGroup.add(rdbtnGameOfLife);
		    algorithmGroup.add(rdbtnLangtonsAnt);
		    algorithmGroup.add(rdbtnRacetrack);
		    algorithmGroup.add(rdbtnSeeds);
		    algorithmGroup.add(rdbtnd);
		
		/*
		 * Adds the action listeners to the whole group.
		 */
		    rdbtnGameOfLife.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
		    rdbtnLangtonsAnt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			rdbtnRacetrack.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			rdbtnSeeds.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			rdbtnd.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});

		/*
		 * Adds buttons to the canvas.
		 */
		algorithmsPanel.add(rdbtnGameOfLife, gbc_rdbtnGameOfLife);
		algorithmsPanel.add(rdbtnLangtonsAnt, gbc_rdbtnLangtonsAnt);
		algorithmsPanel.add(rdbtnRacetrack, gbc_rdbtnRacetrack);
		algorithmsPanel.add(rdbtnSeeds, gbc_rdbtnSeeds);
		algorithmsPanel.add(rdbtnd, gbc_rdbtnd);
		
		/*
		 * Creates the panel & content pane for play/pause/stop buttons.
		 */
		JPanel playPauseStopPanel = new JPanel();
		playPauseStopPanel.setBackground(Color.DARK_GRAY);
		playPauseStopPanel.setBounds(10, 662, 274, 91);
		frame.getContentPane().add(playPauseStopPanel);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		playPauseStopPanel.setLayout(gbl_panel_1);
		
		/*
		 * Initialises play/pause/stop buttons.
		 */
		final JButton btnPlay = new JButton("Play");
		btnPlay.setPreferredSize(new Dimension(80, 49));
		GridBagConstraints gbc_btnPlay = new GridBagConstraints();
		gbc_btnPlay.insets = new Insets(0, 0, 0, 5);
		gbc_btnPlay.gridx = 0;
		gbc_btnPlay.gridy = 0;
		
		final JButton btnPause = new JButton("Pause");
		btnPause.setPreferredSize(new Dimension(80, 49));
		GridBagConstraints gbc_btnPause = new GridBagConstraints();
		gbc_btnPause.insets = new Insets(0, 0, 0, 5);
		gbc_btnPause.gridx = 1;
		gbc_btnPause.gridy = 0;
		
		final JButton btnStop = new JButton("Stop");
		btnStop.setPreferredSize(new Dimension(80, 49));
		GridBagConstraints gbc_btnStop = new GridBagConstraints();
		gbc_btnStop.gridx = 2;
		gbc_btnStop.gridy = 0;
		
		/*
		 * Adds action listeners to play/pause/stop buttons.
		 */
		//When play button is pressed, the engine should start running:
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//If engine is not already running:
				if (!eng.isEngineRunning()) {
					eng.setShouldEngineRun(true);
					int toGeneration = Integer.parseInt(generationTF.getText());
					// eng.runEngine(toGeneration);

					// the invocation of this method
					// initiates the run() method in Engine class
					// the run() method will run in a different thread
					if (!eng.isAlive())
						eng.start();
					else
						eng.resume();
				} else{ // !! I, Silver, added the else statement, but am
						// !! not sure whether that's a good idea
					if (!eng.isAlive())
						eng.start();
					else
						eng.resume();
				}
			}
		});
		//When pause button is pressed, engine is halted:
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eng.suspend();
				eng.setShouldEngineRun(false);
			}
		});
		
		// TODO Stop button should halt the engine and clear the gGrid to its initial generation, allowing the user to start over.
		// an easy implementation of this would be to simply use the button to reselect the algorithm one is currently on, but pressing the algorithm button instead doesnt do anything (for safety)
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eng.setShouldEngineRun(false);
				removeGridFromScreen();
				initialiseGridOnScreen();
			}
		});
		/*
		 * Adds play/pause/stop buttons to the according panel.
		 */
		playPauseStopPanel.add(btnPlay, gbc_btnPlay);
		playPauseStopPanel.add(btnPause, gbc_btnPause);
		playPauseStopPanel.add(btnStop, gbc_btnStop);
		
		/*
		 * Everything to do with the speed slider.
		 */
		JSlider slider = new JSlider();
		slider.setMinimum(-20);
		slider.setMaximum(1000);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(50);
		slider.setInverted(true);
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider)e.getSource();
				if (!source.getValueIsAdjusting()) {
					// Here's our code for what the slider does after not sliding anymore.
					eng.setDelay(source.getValue());
			    }
			}
		});
		slider.setBounds(304, 662, 620, 23);
		frame.getContentPane().add(slider);
		
		/*
		 * Panel for the properties such as colours, dimension, generation.
		 */
		JPanel propertyPanel = new JPanel();
		propertyPanel.setBackground(Color.DARK_GRAY);
		propertyPanel.setBounds(304, 696, 620, 57);
		frame.getContentPane().add(propertyPanel);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel_2.rowHeights = new int[]{0, 0, 0};
		gbl_panel_2.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_2.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		propertyPanel.setLayout(gbl_panel_2);
		
		JLabel lblColourScheme = new JLabel("Colour scheme");
		lblColourScheme.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_lblColourScheme = new GridBagConstraints();
		gbc_lblColourScheme.insets = new Insets(0, 75, 5, 75);
		gbc_lblColourScheme.gridx = 0;
		gbc_lblColourScheme.gridy = 0;
		propertyPanel.add(lblColourScheme, gbc_lblColourScheme);
		
		JLabel lblResolution = new JLabel("Dimension");
		lblResolution.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_lblResolution = new GridBagConstraints();
		gbc_lblResolution.insets = new Insets(0, 0, 5, 5);
		gbc_lblResolution.gridx = 1;
		gbc_lblResolution.gridy = 0;
		propertyPanel.add(lblResolution, gbc_lblResolution);
		
		JLabel lblGeneration = new JLabel("Generation");
		lblGeneration.setForeground(Color.LIGHT_GRAY);
		GridBagConstraints gbc_lblGeneration = new GridBagConstraints();
		gbc_lblGeneration.insets = new Insets(0, 0, 5, 0);
		gbc_lblGeneration.gridx = 2;
		gbc_lblGeneration.gridy = 0;
		propertyPanel.add(lblGeneration, gbc_lblGeneration);
		
		/*
		 * Combo box for the colour scheme.
		 */
        String[] colourSchemes = { "Colour Scheme One", "Colour Scheme Two", "Colour Scheme Three", "Colour Scheme Four" };
		JComboBox colourSchemeCB = new JComboBox(colourSchemes);
		colourSchemeCB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JComboBox colourSchemeCB = (JComboBox)e.getSource();
		        String selectedColourScheme = (String)colourSchemeCB.getSelectedItem();
		        if (selectedColourScheme.equals("Colour Scheme One")){
		        	colorScheme = 1;
		        } else if (selectedColourScheme.equals("Colour Scheme Two")){
		        	colorScheme = 2;
		        } else if (selectedColourScheme.equals("Colour Scheme Three")){
		        	colorScheme = 3;
		        } else if (selectedColourScheme.equals("Colour Scheme Four")){
		        	colorScheme = 4;
		        } else{
		        	colorScheme = 1;
		        }
			}
		});
		GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
		gbc_comboBox_1.anchor = GridBagConstraints.NORTH;
		gbc_comboBox_1.insets = new Insets(0, 75, 0, 75);
		gbc_comboBox_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox_1.gridx = 0;
		gbc_comboBox_1.gridy = 1;
		propertyPanel.add(colourSchemeCB, gbc_comboBox_1);
		
		/*
		 * Text field for showing and changing the dimension of the shown grid.
		 */
		dimensionTF = new JTextField();
		dimensionTF.setText(Integer.toString(dimension));
		dimensionTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int text = roundToBetterNumber(Integer.parseInt(dimensionTF.getText()), GRID_SIZE_IN_PIXELS);
				dimensionTF.setText(Integer.toString(text));
				//We stop our execution of engine:
				eng.setShouldEngineRun(false);
				eng.suspend();
				//Changing the dimension of our graphical representation of the grid:
				changeDimension(text);
				//Changing the dimension of our grid, that algorithms have to deal with:
				eng.setNewGrid(dimension);
			}
		});
		dimensionTF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// This should stop the new iterations of generations so the text box can be modified.
				// For friendlier implementation, we could only pause it until the value has not been changed.
				eng.setShouldEngineRun(false);
				eng.suspend();
			}
		});
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.VERTICAL;
		gbc_textField.anchor = GridBagConstraints.EAST;
		gbc_textField.insets = new Insets(0, 0, 10, 5);
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		dimensionTF.setColumns(10);
		dimensionTF.setHorizontalAlignment(JTextField.CENTER);
		propertyPanel.add(dimensionTF, gbc_textField);
		
		/*
		 * Text field for showing and changing the generation of the shown grid.
		 */
		generationTF = new JTextField();
		generationTF.setText(Integer.toString(generation));
		generationTF.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int text = Integer.parseInt(generationTF.getText());
				eng.setShouldEngineRun(true);
				eng.resume();
				eng.runEngine(text);
			}
		});
		generationTF.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// This should pause the new iterations of generations so the text box can be modified.
				eng.setShouldEngineRun(false);
				eng.suspend();
			}
		});
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.insets = new Insets(0, 0, 10, 0);
		gbc_textField_1.fill = GridBagConstraints.VERTICAL;
		gbc_textField_1.anchor = GridBagConstraints.EAST;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 1;
		generationTF.setColumns(10);
		generationTF.setHorizontalAlignment(JTextField.RIGHT);
		propertyPanel.add(generationTF, gbc_textField_1);
		
		/*
		 * This is the graphical grid, which visualises our (other) grid.
		 */
/*		gGrid = new JTable(dimension, dimension);
		gGrid.setBackground(Color.DARK_GRAY);
		gGrid.setForeground(Color.LIGHT_GRAY);
		gGrid.setDefaultRenderer(Object.class, new CellRenderer());
		gGrid.setFocusable(false);
		gGrid.setRowSelectionAllowed(false);
		gGrid.setBorder(new LineBorder(new Color(51, 51, 51)));
		gGrid.setBounds(294, 11, (int) Math.floor(640/dimension) * dimension, (int) Math.floor(640/dimension) * dimension);
		gGrid.setRowHeight(640/dimension);
		gGrid.setEnabled(false);
		gGrid.setGridColor(new Color(51, 51, 51));
		TableColumnModel columnModel = gGrid.getColumnModel();
		for (int count = gGrid.getColumnCount(), col = 0; col < count; col++){
			TableColumn column = columnModel.getColumn(col); 
			column.setMinWidth(0);
		}
		frame.getContentPane().add(gGrid);*/
	}
	
	//Getter method for giving grid's dimension:
	//(May be invoked by "anyone")
	public int getDimension() {
		return dimension;
	}
	
	//Method, that changes the dimension of the grid:
	//(Invoked by GUI)
	private void changeDimension(int dimension) {
		/*
		 * Changes the dimension of the grid in whatever way we decide is good
		 * if at all we decide we want this feature */
		removeGridFromScreen();
		this.dimension = dimension;
		generation = 0;
		graphicalG = new GraphicalGrid(dimension);
		initialiseGridOnScreen();
		frame.invalidate();
		frame.validate();
	}
	
	private void removeGridFromScreen()
	{
		frame.getContentPane().remove(graphicalG.gGrid);
		frame.repaint();
	}
	//Method, that brings the grid to the screen:
	//(Invoked by GUI):
	private void initialiseGridOnScreen() {
		CellRenderer a = new CellRenderer();
		a.setGUI(this);
		graphicalG.gGrid.setDefaultRenderer(Object.class, a);
		graphicalG.gGrid.setFocusable(false);
		graphicalG.gGrid.setRowSelectionAllowed(false);
		graphicalG.gGrid.setBorder(new LineBorder(new Color(51, 51, 51)));
		graphicalG.gGrid.setBounds(294, 11, GRID_SIZE_IN_PIXELS, GRID_SIZE_IN_PIXELS);
		frame.getContentPane().add(graphicalG.gGrid);
	}
	
	//Method, that paints all cells:
	//(Invoked by Engine)
	public void paintAllGrid(Grid g) {
		int[][] a = g.getSquareArray();
		for (int y = 0 ; y < dimension; y++)
			for (int x = 0 ; x < dimension ; x++) {
				graphicalG.gGrid.setValueAt(a[y][x], y, x);
		}
		
	}
	
	//Method, that paints some specific cells, specified in
	//array list:
	//(Invoked by Engine)
	public void paintSomeGrid(ArrayList<Integer[]> arrList) {
		Integer[] arr;
		for (int x = 0 ; x < arrList.size(); x++) {
			arr = arrList.get(x);
			graphicalG.gGrid.setValueAt(arr[2], arr[0], arr[1]);
		}
	}
	
	
	//Method, that gives a color from current colorscheme:
	//(Invoked by GUI):
	public Color getColorSchemeColor(int c) {
		//A method for getting the color scheme color
		//(If a color scheme (gui instance variable)
		//is set to "2" and int c is "3", that means,
		//that this function should get the value of
		//third color in second color scheme):
		Color ans = Color.RED; // In case there's nothing there.
		if (colorScheme == 1) {
			if (c == 0)
				ans = Color.DARK_GRAY;
			if (c == 1)
				ans = Color.WHITE;
			if (c == 2)
				ans = new Color(0x9E0C39);
			if (c == 3)
				ans = new Color(0x83A300);
		}
		if (colorScheme == 2) {
			if (c == 0)
				ans = Color.DARK_GRAY;
			if (c == 1)
				ans = new Color(0xF8F4E6);
			if (c == 2)
				ans = new Color(0x7E6F3E);
			if (c == 3)
				ans = new Color(0x2FBAD6);
		}
		if (colorScheme == 3) {
			if (c == 0)
				ans = Color.DARK_GRAY;
			if (c == 1)
				ans = new Color(0x89C415);
			if (c == 2)
				ans = new Color(0xE22F74);
			if (c == 3)
				ans = new Color(0x17B78A);
		}
		if (colorScheme == 4) {
			if (c == 0)
				ans = Color.DARK_GRAY;
			if (c == 1)
				ans = Color.CYAN;
			if (c == 2)
				ans = Color.MAGENTA;
			if (c == 3)
				ans = Color.YELLOW;
		}
		return ans;
	}
	
	 public static int roundToBetterNumber(int a, int b){
		 if (Math.round((float) b/a) == Math.floor((float) b/a)){
			 while (b%a != 0) {
				 a++;
			 }
		 } else{
			 while (b%a != 0) {
				 a -= 1;
			 }
		 }
		 return a;
	 }
	
	
	
}
