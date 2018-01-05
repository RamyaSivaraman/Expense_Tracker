package expensetracker;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Ramya Sivaraman
 * Student Id: W1282392
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Assignment 3
 * Date of Submission: May 31st 2017
 */
public class PieChartView extends JPanel {

	private double[] values;
	private String[] labels;
	private Color[] colors;
	
	/**
	*   Takes and sets month names, amount spent,colors for each sector  in pie graph
	*   @param   double takes amount spent in each month, string month names, color for each sector
	**/

	public PieChartView(double[] values, String[] labels, Color[] colors) {
		this.labels = labels;
		this.values = values;
		this.colors = colors;

	}
	
	/**
	*   Creates a Pie Chart view 
	*   @param Graphics  
	**/

	// draw spent$ in a pie chart
	public void paintComponent(Graphics g) {
		// ensure proper painting sequence
		super.paintComponent(g);

		// draw pie chart
		drawPieChart(g);

		// draw legend to describe pie chart wedges
		drawLegend(g);

		setSize(350, 300);
		setVisible(true);
	}
	
	/**
	*   draw pie chart on given Graphics context 
	*   @param level: Graphics  
	**/
	
	private void drawPieChart(Graphics g) {
		// get combined expenditure
		double totalvalue = getTotalValue();

		// create temporary variables for pie chart calculations
		double percentage = 0.0;
		int startAngle = 0;
		int arcAngle = 0;

		for (int j = 0; j < labels.length; j++) {

			percentage = values[j] / totalvalue;
			arcAngle = (int) Math.round(percentage * 360);
			g.setColor(colors[j]);
			g.fillArc(5, 5, 100, 100, startAngle, arcAngle);
			startAngle += arcAngle;
		}
	}
	/**
	*   draw pie wedge for each category
	*   draw pie chart legend on given Graphics context
	*   @param level: Graphics  
	**/
	
	private void drawLegend(Graphics g) {

		Font font = new Font("SansSerif", Font.BOLD, 12);
		g.setFont(font);

		// get FontMetrics for calculating offsets and
		// positioning descriptions
		FontMetrics metrics = getFontMetrics(font);
		int ascent = metrics.getMaxAscent();
		int offsetY = ascent + 2;

		for (int i = 0; i < labels.length; i++) {

			g.setColor(colors[i]);
			g.fillRect(125, offsetY * i, ascent, ascent);

			g.setColor(Color.black);
			g.drawString(labels[i], 140, offsetY * i + ascent);
		}
	} // end method drawLegend

	
	
	/**
	*   get combined expenditure of all categories
	*   @return level: a double, returns total expenditure
	**/
	//
	private double getTotalValue() {
		double sum = 0.0;

		// calculate total balance
		for (int i = 0; i < labels.length; i++) {

			sum += values[i];
		}

		return sum;
	}

}
