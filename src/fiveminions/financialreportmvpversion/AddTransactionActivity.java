package fiveminions.financialreportmvpversion;

import java.util.List;

import database.FinancialAccountSource;
import database.FinancialTransactionSource;

import model.Transaction;
import presenters.AddTransactionPresenter;
import views.IAddTransactionView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * This class describes the protected and public methods 
 * needed for the activity of add transactions. These 
 * methods hold the name, date, amount, type and other 
 * informations for the transaction being added. 
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class AddTransactionActivity extends Activity implements IAddTransactionView, OnItemSelectedListener{

	AddTransactionPresenter presenter;
	private int year;
	private int month;
	private int day;
	private EditText name;
	private DatePicker date;
	private EditText amount;
	private String type;
	private String bankname;
	private String userid;
	private TextView text;
	private Spinner typeSpinner;
	private List<String> categories;
	private FinancialTransactionSource datasource;
	Bundle b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_transaction);
		presenter = new AddTransactionPresenter(this);
		datasource = new FinancialTransactionSource(this);
		b = getIntent().getExtras();
		bankname = b.getString("bankname");
		userid = b.getString("userid");
		text = (TextView) findViewById(R.id.tranText);
		name = (EditText) findViewById(R.id.tranName);
		date = (DatePicker) findViewById(R.id.tranDate);
		amount = (EditText) findViewById(R.id.tranAmount);
		typeSpinner = (Spinner) findViewById(R.id.tranTypeSpinner);
		typeSpinner.setOnItemSelectedListener(this);
		date.setCalendarViewShown(false);
		
		categories = presenter.getTypeList();
		// Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new 
        		ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(dataAdapter);
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int position,
			long id) {
		// On selecting a spinner item
		type = parent.getItemAtPosition(position).toString();
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub
		
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
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_transaction, menu);
		return true;
	}

	public void onSubmitClick(View v){
		presenter.onSubmitClick();
	}
	
	@Override
	public String getName() {
		return name.getText().toString();
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String getDate() {
		year = date.getYear();
		month = date.getMonth();
		day = date.getDayOfMonth();
		String date = year + "-" + month + "-" + day;
		return date;
	}

	@Override
	public String getAmount() {
		return amount.getText().toString();
	}


	@Override
	public String getBKDisname() {
		return bankname;
	}

	@Override
	public boolean addTrans(Transaction t) {
		boolean flag = false;
		if(datasource.addTransaction(t)){
			flag = true;
		}
		return flag;
	}

	@Override
	public void setText(String t) {
		text.setText(t);
	}

	@Override
	public void goBack() {
		finish();
	}

	@Override
	public String getUserid() {
		return userid;
	}



}
