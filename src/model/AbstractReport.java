package model;

import android.annotation.SuppressLint;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * This class holds the information of the report and
 * contains methods to access date, spending title and 
 * total title of the report.
 * @version 1.0
 * @author Team 23
 */
public abstract class AbstractReport {
    /**
     * myDate for user to select different date to view report.
     */
    protected final MyDate reportDate; // NOPMD by wen on 4/2/14 1:18 AM
    /**
     * system format date, to get current date.
     */
    protected Date date; // NOPMD by wen on 4/2/14 1:19 AM
    /**
     * Default constructor for Bank Account.
     *
     */
    @SuppressLint("SimpleDateFormat")
    public AbstractReport() {
        date = new Date();
        final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // NOPMD by wen on 4/2/14 1:18 AM
        this.reportDate = new MyDate(dateFormat.format(date));
    }
    /**
     * getCurrentYearMonth method.
     * 
     * @return the date of the report in format
     */
    @SuppressLint("SimpleDateFormat")
    public String getCurrentYearMonth() {
        final DateFormat dataFormat = new SimpleDateFormat("yyyyMM"); // NOPMD by wen on 4/2/14 1:18 AM
        date = new Date();
        return dataFormat.format(date);
    }

    /**
     * getTotalTitle method.
     * 
     * @param total - the total title of the report
     * @return the total title of the report in string
     */
    public String getTotalTile(final double total) {
    	NumberFormat numForm = NumberFormat.getCurrencyInstance();
        return "The total is " + numForm.format(total);
    }
    
    /**
     * get the year.
     * @return year
     */
    public String getYear(){
        return Integer.toString(reportDate.getYear());
    }
    
    /**
     * get the month.
     * @return month
     */
    @SuppressLint("SimpleDateFormat")
    public String getMonth(){
        DateFormat dateformate = new SimpleDateFormat("MM");
        return dateformate.format(date);
    }
    
    
    /**
     * getTitle method.
     * 
     * @param year - the year of the report
     * @param month - the month of the report
     * @return the title of the report
     */
    abstract public String getTitle(String year, String month);
    /**
     * get the category list of the report.
     * @return category list
     */
    abstract public ArrayList<String> getCategoryList();
}
