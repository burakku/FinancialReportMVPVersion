package model;

import java.util.ArrayList;
import java.util.List;
/**
 * income report class extends report class.
 * @author Team23
 * @version 1.0
 */
public class IncomeReport extends AbstractReport {

    /**
     * constructor.
     */
    public IncomeReport() {
        super();
    }

    @Override
    public String getTitle(String year, String month) {
        if(!year.equals("") || !month.equals("")){
            reportDate.setYear(Integer.parseInt(year));
            reportDate.setMonth(Integer.parseInt(month));
        }
        return"Income Transactions for " + reportDate.getYear() + " "+ reportDate.getFormatMonth();
    }

    @Override
    public ArrayList<String> getCategoryList() {
        List<String> cate = new ArrayList<String>();
        cate.add("Salary");
        cate.add("Birthday");
        cate.add("Parents");
        cate.add("Shoclarship");
        cate.add("Lottery");
        cate.add("Other");
        return (ArrayList<String>) cate;
    }
    
}
