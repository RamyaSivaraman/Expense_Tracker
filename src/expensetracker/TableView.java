package expensetracker;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
/**
 * @author Ramya Sivaraman
 * Student Id: W1282392
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Assignment 3
 * Date of Submission: May 31st 2017
 */

public class TableView extends JPanel {
	JTable table;

	public TableView(String[] month, double[] spent$) {
		// headers for the table
		String[] columns = new String[] { "Month", "Spent$" };

		// actual data for the table in a 2d array
		Object[][] data = new Object[month.length][2];
		for (int i = 0; i < month.length; i++) {
			if (spent$[i] > 0) {
				data[i][0] = month[i];
				data[i][1] = spent$[i];
			}
		}

		final Class[] columnClass = new Class[] { Integer.class, String.class, Double.class, Boolean.class };
		// create table model with data
		DefaultTableModel model = new DefaultTableModel(data, columns) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return columnClass[columnIndex];
			}
		};
		// create table with data
		table = new JTable(model);
		// add the table to the frame
		this.add(new JScrollPane(table));

		this.setVisible(true);
	}
	
	/**
	*  To check if the month already exists in the table,adds the expenditure to the month
	*  or create a new row having the new month and expenditure.
	*   @param  String takes the month name , double gets the expenditure in each month
	**/

	public void updateorAddAmountForMonth(String month, double value) {

		DefaultTableModel model = (DefaultTableModel) table.getModel();
		int rows = model.getRowCount();
		boolean rowExists = false;
		for (int i = 0; i < model.getRowCount(); i++) {
			String tablemonth = (String) model.getValueAt(i, 0);
			if (tablemonth.equals(month)) {
				model.setValueAt(value, i, 1);
				rowExists = true;
				break;
			}
		}

		if (!rowExists) {
			model.addRow(new Object[] { month, value });
		}

	}

}
