package expensetracker;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import expensetracker.ExpenseTracker;


/**
 * @author Ramya Sivaraman
 * Student Id: W1282392
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Assignment 3
 * Date of Submission: May 31st 2017
 */

public class ExpenseController extends JPanel {
	
	private java.util.List<String> xList; // List of x-coord
	private java.util.List<Double> yList; // List of y-coord
	private java.util.List<String> monthList;
	private java.util.List<Double> spentList;

	private double[] values;
	private String[] labels;
	private double[] spent$;
	private String[] month;
	
	Map<String, Double> categoryAmt = new HashMap<String, Double>();
	Map<String, Double> dateAmt = new HashMap<String, Double>();

	public JTextField amountTextField;
	public JLabel label;
	public JLabel label2, label3;
	public JButton addButton, saveButton;
	public JComboBox comboBox;
	public BarChartView bc;
	public JTextField dateField;
	public TableView tv;
	public PieChartView pv;

	ExpenseController() {
		
		// lists to hold categories and its values for bargraph and piechart
		xList = new ArrayList<String>();
		yList = new ArrayList<Double>();
        
		//lists to hold months and expenditure for table
		monthList = new ArrayList<String>();
		spentList = new ArrayList<Double>();

		//Calls method to load data from file.
		loadCoordinatesFromFile("res/datafile.txt");
		String title = "Bar Graph";
		
		Color[] colors = new Color[] { Color.red, Color.orange, Color.yellow, Color.green, Color.blue };

		Color[] colors1 = new Color[] { Color.red, Color.orange, Color.yellow, Color.green, Color.blue, Color.gray,
				Color.magenta, Color.pink, Color.cyan, Color.black, Color.white, Color.lightGray };
		
		// creates instance for bar graph view
		bc = new BarChartView(values, labels, colors, title);
		
		//creates instance for table view
		tv = new TableView(month, spent$);
		
		//creates instance for piechart view
		pv = new PieChartView(values, labels, colors);
		
		label = new JLabel("Enter the amount spent in $");
		amountTextField = new JTextField(10);
		label2 = new JLabel("Enter the date in the format dd-mm-yyyy");
		label3 = new JLabel("Choose a category");
		dateField = new JTextField(10);
		comboBox = new JComboBox(labels);

		addButton = new JButton("Add");
		
        
		/**
		*  Takes value of expenditure and date from user and persists it in file
		*  @exception exception is thrown amount is provided or date is in wrong format
		**/
		addButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				try {

					// deposit amount entered in amountTextField
					addAmount(Double.parseDouble(amountTextField.getText()));

					if (dateField.getText().matches("([0-9]{2})-([0-9]{2})-([0-9]{4})")) {
						addAmtToTable(Double.parseDouble(amountTextField.getText()));

						persistance();
					}

					else {
						JOptionPane.showMessageDialog(ExpenseController.this,
								"Please enter a valid date in the given format", "Error", JOptionPane.ERROR_MESSAGE);
					}

				}

				catch (NumberFormatException exception) {
					JOptionPane.showMessageDialog(ExpenseController.this, "Please enter a valid amount", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			} // end method actionPerformed
		});

	}
	
	/**
	*  persists the expenditure and date in file
	*  @exception exception is thrown when file is missing
	**/
	public void persistance() {
		try {
			FileWriter writer = new FileWriter("res/datafile.txt", true);
			BufferedWriter bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.newLine();
			bufferedWriter.write(
					amountTextField.getText() + "," + (String) comboBox.getSelectedItem() + "," + dateField.getText());
			// bufferedWriter.newLine();
			bufferedWriter.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	/**
	*  To add value to the respective category and repaint the two graphs accordingly.
	*  @param: double, takes amount spent in that category
	**/
	
	public void addAmount(double amt) {

		for (int i = 0; i < values.length; i++) {
			if (labels[i].equals((String) comboBox.getSelectedItem()))
				values[i] = values[i] + amt;

		}

		bc.repaint();
		pv.repaint();

	}
	
	/**
	*  To add value to the respective month.
	*  @param: double, takes amount for that date 
	**/
	
	public void addAmtToTable(double amt) {

		String[] monthToken = dateField.getText().split("-");
		Double amountSpent = 0.0;
		String monthName = ExpenseUtil.getMonthNameFromNumber(monthToken[1]);
		if (dateAmt.containsKey(monthName) && dateAmt.get(monthName) != null) {
			amountSpent = dateAmt.get(monthName);
		}

		Double totalAmt = amountSpent + amt;
		dateAmt.put(monthName, totalAmt);
		
		tv.updateorAddAmountForMonth(monthName, totalAmt);

	}
    
	/**
	*  Reads the data from file
	*  Checks the date, finds corresponding month name for the month token of file
	*  Gets category name from file
	*  adds expenditure to the respective month and category
	*  @param: String of filename 
	*  @exception: Throws exception when file is not present
	**/
	
	public void loadCoordinatesFromFile(String fileName) {
		Scanner s = null;
		// int x;
		// String y;
		double groceryAmt = 0;
		double transAmt = 0;
		double housingAmt = 0;
		double entertAmt = 0;
		double miscAmt = 0;

		double monthamt[] = new double[12];
		for (int k = 0; k < 12; k++) {
			monthamt[k] = 0;
		}

		try {
			s = new Scanner(new BufferedReader(new FileReader(fileName)));

			while (s.hasNextLine()) {
				String line = s.nextLine();

				String[] tokens = line.split("[,\\s]+");
				String[] datetokens = tokens[2].split("-");

				if (tokens[1].equals("Grocery")) {
					groceryAmt = groceryAmt + Double.parseDouble(tokens[0]);
					categoryAmt.put("Grocery", groceryAmt);
				}
				if (tokens[1].equals("Transportation")) {
					transAmt = transAmt + Double.parseDouble(tokens[0]);
					categoryAmt.put("Transportation", transAmt);
				}
				if (tokens[1].equals("Housing")) {
					housingAmt = housingAmt + Double.parseDouble(tokens[0]);
					categoryAmt.put("Housing", housingAmt);
				}
				if (tokens[1].equals("Entertainment")) {
					entertAmt = entertAmt + Double.parseDouble(tokens[0]);
					categoryAmt.put("Entertainment", entertAmt);
				}
				if (tokens[1].equals("Misc")) {
					miscAmt = miscAmt + Double.parseDouble(tokens[0]);
					categoryAmt.put("Misc", miscAmt);
				}
				
				String monthName = ExpenseUtil.getMonthNameFromNumber(datetokens[1]);
				Double spentAmount = 0.0;
				if (dateAmt.containsKey(monthName) && dateAmt.get(monthName) != null) {
					spentAmount = dateAmt.get(monthName);
				}
				Double totalAmt = spentAmount + Double.parseDouble(tokens[0]);

				dateAmt.put(monthName, totalAmt);

			}

			for (Entry<String, Double> entry : categoryAmt.entrySet()) {
				xList.add((String) entry.getKey());
				yList.add((Double) entry.getValue());
			}
			

			for (Entry<String, Double> entry : dateAmt.entrySet()) {
				monthList.add((String) entry.getKey());
				spentList.add((Double) entry.getValue());
			}

			values = new double[yList.size()];
			labels = new String[xList.size()];
			Iterator<String> iterator1 = xList.iterator();
			Iterator<Double> iterator2 = yList.iterator();
			for (int i = 0; i < values.length; i++) {
				values[i] = iterator2.next();
				labels[i] = iterator1.next();
			}

			month = new String[monthList.size()];
			spent$ = new double[spentList.size()];
			Iterator<String> iterator4 = monthList.iterator();
			Iterator<Double> iterator3 = spentList.iterator();
			for (int i = 0; i < month.length; i++) {
				month[i] = iterator4.next();
				spent$[i] = iterator3.next();
			}

		} catch (Exception e) {
		} finally {

			if (s != null)
				s.close();
		}
	}
}