package views;

/**
 * An interface for the report view which specifies the methods of its
 * implemented class.
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public interface IReportView {
    /**
     * get year and month list.
     * 
     * @return list list of year and month
     */
    String getYearMonth();

    /**
     * refresh view.
     */
    void refresh();
}
