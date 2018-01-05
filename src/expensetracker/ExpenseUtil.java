package expensetracker;
/**
 * @author Ramya Sivaraman
 * Student Id: W1282392
 * Course Number:COEN 275 Object Oriented Analysis Design and Programming.
 * Assignment: Assignment 3
 * Date of Submission: May 31st 2017
 */

public class ExpenseUtil {
	
	/**
	*  To get the name of the month by providing the month number
	*   @param level: String of month number
	*   @return: String of month name
	**/

	public static String getMonthNameFromNumber(String monNum) {

		String monName = null;
		if (monNum.equals("01")) {
			monName = "Jan";
		} else if (monNum.equals("02")) {
			monName = "Feb";
		} else if (monNum.equals("03")) {
			monName = "Mar";
		} else if (monNum.equals("04")) {
			monName = "Apr";
		} else if (monNum.equals("05")) {
			monName = "May";
		} else if (monNum.equals("06")) {
			monName = "Jun";
		} else if (monNum.equals("07")) {
			monName = "Jul";
		} else if (monNum.equals("08")) {
			monName = "Aug";
		} else if (monNum.equals("09")) {
			monName = "Sep";
		} else if (monNum.equals("10")) {
			monName = "Oct";
		} else if (monNum.equals("11")) {
			monName = "Nov";
		} else if (monNum.equals("12")) {
			monName = "Dec";
		}
		return monName;
	}

}
