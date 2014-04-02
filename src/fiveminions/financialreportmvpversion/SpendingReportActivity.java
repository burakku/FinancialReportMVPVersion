package fiveminions.financialreportmvpversion;

import java.util.ArrayList;
import java.util.List;

import presenters.ReportPresenter;

import model.Report;
import model.Transaction;

import database.FinancialReportGenerator;

import views.IReportView;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * This is a subclass of ListActivity which describes the 
 * methods needed for  the activity of spending report. 
 * These methods can access the information of a report 
 * about the month, year and so forth. 
 * @version 1.0
 * @author Team 23
 */
public class SpendingReportActivity extends ListActivity implements
		IReportView, OnItemSelectedListener {

	private FinancialReportGenerator datasource;
	private List<Transaction> spendingList;
	private TextView text;
	private TextView amountText;
	private Report report;
	private ReportPresenter presenter;
	private Spinner yearSpinner; // NOPMD by wen on 4/2/14 1:48 AM
	private List<String> yearList; // NOPMD by wen on 4/2/14 1:48 AM
	private Spinner monthSpinner; // NOPMD by wen on 4/2/14 1:48 AM
	private List<String> monthList; // NOPMD by wen on 4/2/14 1:46 AM
	private String year = "";
	private String month = "";
	private String userid;
	Bundle boudle;
	@Override
	protected void onCreate(Bundle savedInstanceState) { // NOPMD by wen on 4/2/14 1:46 AM
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report);
		datasource = new FinancialReportGenerator(this);
		presenter = new ReportPresenter(this);
		report = new Report();
		
		boudle = getIntent().getExtras(); // NOPMD by wen on 4/2/14 1:46 AM
		userid = boudle.getString("userid");
		
		text = (TextView) findViewById(R.id.reportText);
		amountText = (TextView) findViewById(R.id.reportTotalAmountText);

		yearSpinner = (Spinner) findViewById(R.id.reportYearSpinner);
		monthSpinner = (Spinner) findViewById(R.id.reportMonthSpinner);
		yearSpinner.setOnItemSelectedListener(this);
		monthSpinner.setOnItemSelectedListener(this);
		yearList = getYearList();
		monthList = getMonthList();

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

		datasource.open();
		display(report.getCurrentYearMonth());

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long theid) {
		// On selecting a spinner item
		switch (parent.getId()) { // NOPMD by wen on 4/2/14 1:44 AM
		case R.id.reportYearSpinner:
			year = parent.getItemAtPosition(position).toString(); // NOPMD by wen on 4/2/14 1:47 AM
			break;
		case R.id.reportMonthSpinner:
			month = parent.getItemAtPosition(position).toString(); // NOPMD by wen on 4/2/14 1:48 AM
			break;
		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

	public void display(String yearmonth) {
		spendingList = datasource.getSpendingList(yearmonth, userid);
		ArrayAdapter<Transaction> adapter = new ArrayAdapter<Transaction>(this,
				R.layout.list_view1, spendingList);
		setListAdapter(adapter);
		Log.i(MainActivity.LOGTAG, "Refresh Spending List");
		text.setText(report.getSpendingTitle(year, month));
		amountText.setText(report.getTotalTile(datasource.getTotal(spendingList)));
	}

	@Override
	protected void onResume() {
		super.onResume();
		datasource.open();
		display(report.getCurrentYearMonth());
	}

	@Override
	protected void onPause() {
		super.onPause();
		datasource.close();
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) { // NOPMD by wen on 4/2/14 1:45 AM
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent(this, TransactionDetailActivity.class);
		Transaction tran = spendingList.get(position);
		intent.putExtra("model.Transaction", tran);
		intent.putExtra("model.myDate", tran.getDate()); // NOPMD by wen on 4/2/14 1:48 AM
		Log.i(MainActivity.LOGTAG, "Open Transaction Detail");
		startActivity(intent);
	}

	public void onViewClick(View view) {
		presenter.onClickView();
	}

	@Override
	public ArrayList<String> getYearList() {
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("2007");
		arrayList.add("2008");
		arrayList.add("2009");
		arrayList.add("2010");
		arrayList.add("2011");
		arrayList.add("2012");
		arrayList.add("2013");
		arrayList.add("2014");
		return arrayList;
	}

	@Override
	public ArrayList<String> getMonthList() {
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("01"); arrayList.add("02"); arrayList.add("03"); arrayList.add("04");
		arrayList.add("05"); arrayList.add("06"); arrayList.add("07"); arrayList.add("08");
		arrayList.add("09"); arrayList.add("10"); arrayList.add("11"); arrayList.add("12");
		return arrayList;
	}

	@Override
	public String getYearMonth() {
		return year+month;
	}

	@Override
	public void refresh() {
		display(getYearMonth());

	}

}
