package fiveminions.financialreportmvpversion;

import java.util.List;

import model.AbstractReport;
import model.Transaction;
import model.IncomeReport;
import model.SpendingReport;

import database.FinancialReportGenerator;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
/**
 * This is a subclass of ListActivity which describes the 
 * methods needed for the activity of report. 
 * @version 1.0
 * @author Team 23
 */
public class ReportTransActivity extends ListActivity {

    private FinancialReportGenerator datasource;
    private List<Transaction> transactionList;
    private TextView text;
    private TextView amountText;
    private AbstractReport spendingreport;
    private AbstractReport incomereport;
    private String year;
    private String month;
    private String userid;
    private String reportType;
    private Bundle bundle; // NOPMD by farongcheng on 4/3/14 12:33 AM
    @Override
    protected void onCreate(Bundle savedInstanceState) { // NOPMD by farongcheng on 4/3/14 12:34 AM
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_transaction);
        datasource = new FinancialReportGenerator(this);
        spendingreport = new SpendingReport();
        incomereport = new IncomeReport();
        bundle = getIntent().getExtras(); // NOPMD by farongcheng on 4/3/14 12:34 AM
        userid = bundle.getString("userid");
        reportType = bundle.getString("reportType");
        year = bundle.getString("year");
        month = bundle.getString("month");
        
        text = (TextView) findViewById(R.id.reportText);
        amountText = (TextView) findViewById(R.id.reportTotalAmountText);

        datasource.open();
        display();

    }

    /**
     * refresh the list.
     */
    public void display() {
        if (reportType.equals("spending")) { // NOPMD by farongcheng on 4/3/14 12:33 AM
            transactionList = datasource.getSpendingList(year + month, userid);
        } else if (reportType.equals("income")) { // NOPMD by farongcheng on 4/3/14 12:33 AM
            transactionList = datasource.getIncomeList(year + month, userid);
        }
        ArrayAdapter<Transaction> adapter = new ArrayAdapter<Transaction>(this,
                R.layout.list_view1, transactionList);
        setListAdapter(adapter);
        Log.i(MainActivity.LOGTAG, "Refresh Spending List");
        if (reportType.equals("spending")) { // NOPMD by farongcheng on 4/3/14 12:33 AM
            amountText.setText(spendingreport.getTotalTile(datasource.getTotal(transactionList)));
            text.setText(spendingreport.getTitle(year, month));
        } else {
            amountText.setText(incomereport.getTotalTile(datasource.getTotal(transactionList)));
            text.setText(incomereport.getTitle(year, month));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        datasource.open();
    }

    @Override
    protected void onPause() {
        super.onPause();
        datasource.close();
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) { // NOPMD by farongcheng on 4/3/14 12:33 AM
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(this, TransactionDetailActivity.class);
        Transaction tran = transactionList.get(position);
        intent.putExtra("model.Transaction", tran);
        intent.putExtra("model.myDate", tran.getDate()); // NOPMD by farongcheng on 4/3/14 12:34 AM
        Log.i(MainActivity.LOGTAG, "Open Transaction Detail");
        startActivity(intent);
    }

}