package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class holds the information of the report and
 * contains methods to access date, spending title and 
 * total title of the report.
 * @version 1.0
 * @author Team 23
 */
public class Report {
	private final myDate reportDate; // NOPMD by wen on 4/2/14 1:18 AM
	private Date date; // NOPMD by wen on 4/2/14 1:19 AM
	/**
	 * Default constructor for Bank Account 
	 *
	 */
	public Report() {
		date = new Date();
		final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // NOPMD by wen on 4/2/14 1:18 AM
		this.reportDate = new myDate(dateFormat.format(date));
	}
	/**
	 * getCurrentYearMonth method
	 * 
	 * @return the date of the report in format
	 */
	public String getCurrentYearMonth(){
		final DateFormat dataFormat = new SimpleDateFormat("yyyyMM"); // NOPMD by wen on 4/2/14 1:18 AM
		date = new Date();
		return dataFormat.format(date);
	}
	/**
	 * getSpendingTitle method
	 * 
	 * @param year - the year of the report
	 * @param month - the month of the report
	 * @return the spending and date of the report
	 */
	public String getSpendingTitle(final String year, final String month){
		if(!year.equals("")|| !month.equals("")){ // NOPMD by wen on 4/2/14 1:19 AM
			reportDate.setYear(Integer.parseInt(year));
			reportDate.setMonth(Integer.parseInt(month));
		}
		return"Spending Report for " + reportDate.getYear() + " "+ reportDate.getFormatMonth();
	}
	/**
	 * getTotalTitle method
	 * 
	 * @param total - the total title of the report
	 * @return the total title of the report in string
	 */
	public String getTotalTile(final double total){
		return "The total is $" + total;
	}
}
