package views;

import java.util.ArrayList;

/**
 * An interface for the report view which specifies the
 * methods of its implemented class.
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public interface IReportView {
/**
 * get year list.
 * @return list list of year
 */
    ArrayList<String> getYearList(); // NOPMD by hailin on 3/28/14 7:22 PM
 
/**
 * get month list.
 * @return list list of month
 */
    ArrayList<String> getMonthList(); // NOPMD by hailin on 3/28/14 7:21 PM
    
/**
 * get year and month list
 * @return list list of year and month
 */
    String getYearMonth();
    void refresh();
}
