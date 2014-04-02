package fiveminions.financialreportmvpversion;

import java.util.List;

import model.BankAccount;

import database.FinancialAccountSource;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * This is a subclass of ListActivity that describes 
 * the protected methods needed for the activity of 
 * the transaction.
 * @version 1.0
 * @author Team 23
 */
public class TransactionActivity extends ListActivity{
	private String userid;
	private FinancialAccountSource datasource;
	private List<BankAccount> accounts;
	Bundle boudle;
	@Override
	protected void onCreate(Bundle savedInstanceState) { // NOPMD by wen on 4/2/14 1:44 AM
		super.onCreate(savedInstanceState);
		setContentView(R.layout.transaction_page);
		boudle = getIntent().getExtras(); // NOPMD by wen on 4/2/14 1:44 AM
		userid = boudle.getString("userid");
		datasource = new FinancialAccountSource(this);
		datasource.open();
		display();
	}

	public void display(){
		Log.i(MainActivity.LOGTAG, "userid: " + userid);
		accounts = datasource.getAccountList(userid);
		ArrayAdapter<BankAccount> adapter = new ArrayAdapter<BankAccount>(this, R.layout.list_view1, accounts);
		setListAdapter(adapter);
		Log.i(MainActivity.LOGTAG, "Refresh Account List");
	}
	
	
	@Override
	protected void onResume() {
		super.onResume();
		datasource.open();
		display();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		datasource.close();
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) { // NOPMD by wen on 4/2/14 1:44 AM
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		BankAccount baccount = accounts.get(position);
		Intent intent = new Intent(this, AccountTransactionActivity.class);
		intent.putExtra("bankname", baccount.getDisname()); // NOPMD by wen on 4/2/14 1:44 AM
		intent.putExtra("userid", userid);
		Log.i(MainActivity.LOGTAG, "Pass in bankname and userid");
		startActivity(intent);
	}
	
}
