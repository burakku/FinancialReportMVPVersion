package views;

import java.util.ArrayList;

public interface IReportView {

ArrayList<String> getYearList(); // NOPMD by hailin on 3/28/14 7:22 PM
ArrayList<String> getMonthList(); // NOPMD by hailin on 3/28/14 7:21 PM
String getYearMonth();
void refresh();
}
