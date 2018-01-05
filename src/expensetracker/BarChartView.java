package expensetracker;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
/**
 * @author Ramya Sivaraman
 * Student Id: W1282392
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Assignment 3
 * Date of Submission: May 31st 2017
 */
public class BarChartView extends JPanel {

	private double[] values;
	private String[] labels;
	private Color[] colors;
	private String title;

	/**
	*   Takes and sets categories, amount spent,colors for each bar in bar graph, title 
	*   @param   double takes amount spent in each category, string category names, color for each bar, string for title
	**/
	
	
	public BarChartView(double[] values, String[] labels, Color[] colors, String title) {
		this.labels = labels;
		this.values = values;
		this.colors = colors;
		this.title = title;
	}

	/**
	*   Creates a Bar Graph view   
	*   Calculates the maximum value and minimum value and determines panel width, bar width, scale and height
	*   Sets font for title and labels  
	*   Sets title, labels,draws rectangle for bar graphs
	*   Sets size and visibility 
	*   @param level: Graphics  
	**/
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (values == null || values.length == 0) {
			return;
		}

		double minValue = 0;
		double maxValue = 0;
		for (int i = 0; i < values.length; i++) {
			if (minValue > values[i]) {
				minValue = values[i];
			}
			if (maxValue < values[i]) {
				maxValue = values[i];
			}
		}

		Dimension dim = getSize();
		int panelWidth = dim.width;
		int panelHeight = dim.height;
		int barWidth = panelWidth / values.length;

		Font titleFont = new Font("Book Antiqua", Font.BOLD, 15);
		FontMetrics titleFontMetrics = g.getFontMetrics(titleFont);

		Font labelFont = new Font("Book Antiqua", Font.BOLD, 10);
		FontMetrics labelFontMetrics = g.getFontMetrics(labelFont);

		int titleWidth = titleFontMetrics.stringWidth(title);
		int stringHeight = titleFontMetrics.getAscent();
		int stringWidth = (panelWidth - titleWidth) / 2;
		g.setFont(titleFont);
		g.drawString(title, stringWidth, stringHeight);

		int top = titleFontMetrics.getHeight();
		int bottom = labelFontMetrics.getHeight();
		if (maxValue == minValue) {
			return;
		}
		double scale = (panelHeight - top - bottom) / (maxValue - minValue);
		stringHeight = panelHeight - labelFontMetrics.getDescent();
		g.setFont(labelFont);
		for (int j = 0; j < values.length; j++) {
			int valueP = j * barWidth + 1;
			int valueQ = top;
			int height = (int) (values[j] * scale);
			if (values[j] >= 0) {
				valueQ += (int) ((maxValue - values[j]) * scale);
			} else {
				valueQ += (int) (maxValue * scale);
				height = -height;
			}

			g.setColor(colors[j]);
			g.fillRect(valueP, valueQ, barWidth - 2, height);
			g.setColor(Color.black);
			g.drawRect(valueP, valueQ, barWidth - 2, height);

			int labelWidth = labelFontMetrics.stringWidth(labels[j]);
			stringWidth = j * barWidth + (barWidth - labelWidth) / 2;
			g.drawString(labels[j], stringWidth, stringHeight);

			setSize(350, 300);
			setVisible(true);

		}
	}

}