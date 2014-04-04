package fiveminions.financialreportmvpversion;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import database.FinancialReportGenerator;

import model.AbstractReport;
import model.IncomeReport;
import model.SpendingReport;
import presenters.ReportPresenter;
import views.IReportView;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
/**
 * The class to show the report by categories.
 * @author Team23
 * @version 1.0
 */
public class ReportCategoryActivity extends ListActivity implements // NOPMD by farongcheng on 4/3/14 12:30 AM
        IReportView, OnItemSelectedListener {
    public static final String LOGTAG = "CLOVER";
    private ReportPresenter presenter;
    private TextView text;
    private TextView amountText;
    private String userid;
    private String reportType;
    private AbstractReport spendingreport;
    private AbstractReport incomereport;
    private FinancialReportGenerator datasource;
    private Map<String, Double> category;
    private ArrayAdapter<String> adapter; // NOPMD by farongcheng on 4/3/14 12:30 AM
    private ArrayList<String> cateList; // NOPMD by farongcheng on 4/3/14 12:32 AM
    private Spinner yearSpinner; // NOPMD by farongcheng on 4/3/14 12:33 AM
    private Spinner monthSpinner; // NOPMD by farongcheng on 4/3/14 12:30 AM
    private List<String> yearList; // NOPMD by farongcheng on 4/3/14 12:33 AM
    private List<String> monthList; // NOPMD by farongcheng on 4/3/14 12:30 AM
    private DateList dl; // NOPMD by farongcheng on 4/3/14 12:30 AM
    private String year = "";
    private String month = "";
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // NOPMD by farongcheng on 4/3/14 12:29 AM
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_category);
        presenter = new ReportPresenter(this);
        spendingreport = new SpendingReport();
        incomereport = new IncomeReport();
        dl = new DateList();
        // Get passing value
        bundle = getIntent().getExtras(); // NOPMD by farongcheng on 4/3/14 12:29 AM
        userid = bundle.getString("userid");
        reportType = bundle.getString("reportType");

        text = (TextView) findViewById(R.id.reportText);
        amountText = (TextView) findViewById(R.id.reportTotalAmountText);

        yearSpinner = (Spinner) findViewById(R.id.reportYearSpinner);
        monthSpinner = (Spinner) findViewById(R.id.reportMonthSpinner);
        yearSpinner.setOnItemSelectedListener(this);
        monthSpinner.setOnItemSelectedListener(this);
        yearList = dl.getYearList();
        monthList = dl.getMonthList();

        // Create Adapter for spinners
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, yearList);
        yearAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        yearSpinner.setAdapter(yearAdapter);
        // ------------
        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, monthList);
        monthAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        monthSpinner.setAdapter(monthAdapter);

        // Open Databases
        datasource = new FinancialReportGenerator(this);
        datasource.open();
        // set default date to current date
        int spinnerYear = yearAdapter.getPosition(spendingreport
                .getYear());
        int spinnerMonth = monthAdapter.getPosition(spendingreport
                .getMonth());
        yearSpinner.setSelection(spinnerYear);
        monthSpinner.setSelection(spinnerMonth);
        display(spendingreport.getCurrentYearMonth());

    }
    /**
     * display the title and get list according to 
     * the passed in value.
     * @param yearmonth the selected year and month
     */
    private void display(String yearmonth) {
        Log.i(MainActivity.LOGTAG, "ssss" + year + month);
        cateList = new ArrayList<String>();
        if (reportType.equals("spending")) { // NOPMD by farongcheng on 4/3/14 12:29 AM
            category = datasource.getSpendingCategoryList(yearmonth, userid);
        } else {
            category = datasource.getIncomeCategoryList(yearmonth, userid);
        }
        for (String key : category.keySet()) {
            cateList.add(key + ": " + Double.toString(category.get(key)));
        }
        double totalamount = 0;
        for (double each : category.values()) {
            totalamount += each;
        }
        
        adapter = new ArrayAdapter<String>(this, R.layout.list_view1, cateList);
        setListAdapter(adapter);
        
        if (reportType.equals("spending")) { // NOPMD by farongcheng on 4/3/14 12:33 AM
            amountText.setText(spendingreport.getTotalTile(totalamount));
            text.setText(spendingreport.getTitle(year, month));
        } else {
            amountText.setText(incomereport.getTotalTile(totalamount));
            text.setText(incomereport.getTitle(year, month));
        }
        
    }
    /**
     * transfer to view transaction page.
     * @param view the View
     */
    public void viewReportTransaction(View view) {
        Intent intent = new Intent(this, ReportTransActivity.class);
        intent.putExtra("userid", userid);
        intent.putExtra("reportType", reportType);
        intent.putExtra("year", year);
        intent.putExtra("month", month);
        startActivity(intent);
    }
    /**
     * call the presenter view click method.
     * @param view the View
     */
    public void onViewClick(View view) {
        presenter.onClickView();
    }
    
    @Override
    public void onResume() {
    	super.onResume();
        Log.i(LOGTAG, "FinancialReportGenerator opened");
        datasource.open();
    }
    @Override
    public void onPause() {
    	super.onPause();
        Log.i(LOGTAG, "FinancialReportGenerator closed");
        datasource.close();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
            long id) { // NOPMD by farongcheng on 4/3/14 12:32 AM
        // On selecting a spinner item
        switch (parent.getId()) {
            case R.id.reportYearSpinner:
                year = parent.getItemAtPosition(position).toString(); // NOPMD by farongcheng on 4/3/14 12:29 AM
                break;
            case R.id.reportMonthSpinner:
                month = parent.getItemAtPosition(position).toString(); // NOPMD by farongcheng on 4/3/14 12:29 AM
                break;
            default :
                break;
        }
        
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    @Override
    public String getYearMonth() {
        return year + month;
    }

    @Override
    public void refresh() {
        display(getYearMonth());

    }
}
