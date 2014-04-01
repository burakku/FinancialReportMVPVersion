package model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class holds the information of the report and
 * contains methods to access date, spending title and 
 * total title of the report.
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class Report {
	private myDate reportDate;
	private Date date;
	/**
	 * Default constructor for Bank Account 
	 *
	 */
	public Report() {
		date = new Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		this.reportDate = new myDate(df.format(date));
	}
	/**
	 * getCurrentYearMonth method
	 * 
	 * @return the date of the report in format
	 */
	public String getCurrentYearMonth(){
		DateFormat df = new SimpleDateFormat("yyyyMM");
		date = new Date();
		return df.format(date);
	}
	/**
	 * getSpendingTitle method
	 * 
	 * @param year - the year of the report
	 * @param month - the month of the report
	 * @return the spending and date of the report
	 */
	public String getSpendingTitle(String year, String month){
		if(!year.equals("")|| !month.equals("")){
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
	public String getTotalTile(double total){
		return "The total is $" + total;
	}
}
