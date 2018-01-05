package expensetracker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import expensetracker.ExpenseController;

/**
 * @author Ramya Sivaraman
 * Student Id: W1282392
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Assignment 3
 * Date of Submission: May 31st 2017
 * This class organizes all the views in different panels on the frame.
 */

public class ExpenseTracker extends JFrame {
	public JPanel panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8, panel9;

	public ExpenseTracker() {
		super("Expense Tracker");

		Container container = getContentPane();
		container.setLayout(new FlowLayout());

		panel1 = new JPanel();

		panel1.setPreferredSize(new Dimension(600, 900));
		panel1.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		panel2 = new JPanel();
		panel2.setPreferredSize(new Dimension(600, 900));
		panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK));

		container.add(panel1);
		container.add(panel2);

		panel7 = new JPanel();
		panel7.setLayout(new BoxLayout(panel7, BoxLayout.Y_AXIS));
		panel7.setPreferredSize(new Dimension(590, 430));
		panel7.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel7.setBorder(new TitledBorder("Customer Entry"));

		panel1.add(panel7);
		panel3 = new JPanel(new BorderLayout());
		panel3.setPreferredSize(new Dimension(590, 430));
		panel3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel3.setBorder(new TitledBorder("BarGraph representing Expense by category"));

		ExpenseController ex = new ExpenseController();

		panel8 = new JPanel();
		panel8.setPreferredSize(new Dimension(590, 430));
		panel8.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel8.setBorder(new TitledBorder("PieChartView representing Expense by Category"));
		panel8.add(ex.pv, BorderLayout.CENTER);
		panel1.add(panel8);

		panel3.add(ex.bc, BorderLayout.CENTER);

		panel5 = new JPanel();
		panel5.add(ex.label);
		panel5.add(ex.amountTextField);
		panel5.add(ex.addButton);

		panel9 = new JPanel();
		panel9.add(ex.label3);
		panel9.add(ex.comboBox);

		panel6 = new JPanel();
		panel6.add(ex.label2);
		panel6.add(ex.dateField);

		panel7.add(panel6);
		panel7.add(panel9);
		panel7.add(panel5);
		

		panel4 = new JPanel();
		panel4.setPreferredSize(new Dimension(590, 430));
		panel4.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		panel4.setBorder(new TitledBorder("TableView representing Expense by Month"));

		panel4.add(ex.tv, BorderLayout.CENTER);

		panel2.add(panel3);
		panel2.add(panel4);

		setSize(1500, 1000);
		setVisible(true);
	}

	public static void createAndShowGUI() {
		ExpenseTracker application = new ExpenseTracker();

		application.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		// Schedule a job for the event dispatch thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});

		
	}

}
