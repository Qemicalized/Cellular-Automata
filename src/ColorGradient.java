import java.awt.Color;
import java.util.ArrayList;


public class ColorGradient {

	private ArrayList<Color> colors = new ArrayList<Color>();
	private Color colorZero;
	private int intervals = 0; 
	private int gradients = 0;
	private boolean discontinous = false;//If true, replaces color of c=0 with colorZero
	
	/*
	 * Default constructor makes it a 24 color rainbow
	 */
	public ColorGradient() {
			this.addColor(Color.DARK_GRAY);
			this.addColor(Color.RED);
			this.addColor(Color.YELLOW);
			this.addColor(Color.GREEN);
			this.addColor(Color.BLUE);
			this.addColor(Color.MAGENTA);
			this.addColor(Color.DARK_GRAY);
			this.gradients = 4;
	}
	
	/*
	 * Constructor that takes a list of colors and a number of gradients per color. The total number 
	 * of available colors will thus be (number of colors -1)*gradiantsPerColor 
	 */
	public ColorGradient(ArrayList<Color> colors, int gradientsPerColor) {
		if (colors.size() < 2) {
			this.colors.clear();
			this.addColor(Color.DARK_GRAY);
			this.addColor(Color.WHITE);
		}else {
			this.colors = colors;
		}
		this.intervals = this.colors.size()-1;
		if (gradientsPerColor < 1) {
			this.gradients = 2;
		} else {
			this.gradients = gradientsPerColor;
		}
	}
	
	public ColorGradient(ArrayList<Color> colors, int gradientsPerColor, Color colorZero) {
		this(colors, gradientsPerColor);
		this.colorZero = colorZero;
		this.discontinous = true;
	}
	
	/*
	 * Methods for modifying gradient after initialization
	 */
	public void addColor(Color color) {
		this.colors.add(color);
		this.intervals = colors.size()-1;
	}
	
	public void setColors(ArrayList<Color> colors) {
		this.colors = colors;
		this.intervals = colors.size()-1;
	}
	public void setGradients(int gradients) {
		this.gradients = gradients;
	}
	
	/*
	 * Method that produces a color output.
	 */
	public Color getColor(int c) {
		int interval = c/gradients;
		int gradient = c%gradients;
		if (discontinous && c == 0) {
			return colorZero;
		}else if (c > intervals*gradients || c < 0) {
			return Color.RED;
		}else if (interval == intervals) {
			return colors.get(intervals);
		}else {
			int aG = colors.get(interval).getGreen();
			int bG = colors.get(interval+1).getGreen();
			int deltaR = colors.get(interval+1).getRed() -colors.get(interval).getRed();
			int deltaG = colors.get(interval+1).getGreen() -colors.get(interval).getGreen();
			int deltaB = colors.get(interval+1).getBlue() -colors.get(interval).getBlue();
			
			int r = colors.get(interval).getRed() + gradient*deltaR/gradients;
			int g = colors.get(interval).getGreen() + gradient*deltaG/gradients;
			int b = colors.get(interval).getBlue() + gradient*deltaB/gradients;
			
			return new Color(r,g,b);

		}
	}
}
