package fiveminions.financialreportmvpversion;

import java.util.ArrayList;
import java.util.List;
/**
 * The class to create list of years and months
 * for the use in spinner.
 * @author Team23
 * @version 1.0
 */
public class DateList {
	/**
	 * list of years.
	 */
    private final List<String> yearList;
	/**
	 * list of months.
	 */
    private final List<String> monthList;
    /**
     * constructor.
     */
    public DateList() {
        yearList = new ArrayList<String>();
        monthList = new ArrayList<String>();
        yearList.add("2007");
        yearList.add("2008");
        yearList.add("2009");
        yearList.add("2010");
        yearList.add("2011");
        yearList.add("2012");
        yearList.add("2013");
        yearList.add("2014");
        monthList.add("01");
        monthList.add("02");
        monthList.add("03");
        monthList.add("04");
        monthList.add("05");
        monthList.add("06");
        monthList.add("07");
        monthList.add("08");
        monthList.add("09");
        monthList.add("10");
        monthList.add("11");
        monthList.add("12");
    }
    /**
     * get year list.
     * @return list of the years
     */
    public List<String> getYearList() {
        return yearList;
    }
    /**
     * get month list.
     * @return list of the months
     */
    public List<String> getMonthList() {
        return monthList;
    }
    
    
    
}
