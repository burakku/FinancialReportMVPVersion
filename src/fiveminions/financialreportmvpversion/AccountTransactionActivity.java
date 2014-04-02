package fiveminions.financialreportmvpversion;

import java.util.List;

import model.Transaction;

import database.FinancialTransactionSource;


import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * This class describes the methods needed for the activity 
 * of account transaction which holds the information of 
 * database and deal with the operation for items selected
 * @version 1.0
 * @author Team 23
 */
public class AccountTransactionActivity extends ListActivity{

	private FinancialTransactionSource datasource;
	private List<Transaction> transactions;
	private String bankname;
	private String userid;
	Bundle boudle;
	@Override
	protected void onCreate(Bundle savedInstanceState) { // NOPMD by wen on 4/2/14 1:58 AM
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bank_transaction);
		boudle = getIntent().getExtras(); // NOPMD by wen on 4/2/14 1:59 AM
		bankname = boudle.getString("bankname");
		userid = boudle.getString("userid");
		datasource = new FinancialTransactionSource(this);
		datasource.open();
		display();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.transaction_menu, menu); // NOPMD by wen on 4/2/14 1:58 AM
		return super.onCreateOptionsMenu(menu);
	}
	
	public void display(){
		transactions = datasource.getTransactionList(bankname, userid);
		ArrayAdapter<Transaction> adapter = new ArrayAdapter<Transaction>(this, R.layout.list_view1, transactions);
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
	protected void onListItemClick(ListView l, View v, int position, long id) { // NOPMD by wen on 4/2/14 1:58 AM
		super.onListItemClick(l, v, position, id);
		Intent intent = new Intent(this, TransactionDetailActivity.class);
		Transaction tran = transactions.get(position);
		intent.putExtra("model.Transaction", tran);
		intent.putExtra("model.myDate", tran.getDate()); // NOPMD by wen on 4/2/14 1:59 AM
		Log.i(MainActivity.LOGTAG, "Pass in accounttransaction");
		startActivity(intent);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) { // NOPMD by wen on 4/2/14 1:58 AM
	        case R.id.add_new_account:
	        	Intent intent = new Intent(this, AddTransactionActivity.class);
	        	intent.putExtra("bankname", bankname);
	        	intent.putExtra("userid", userid);
	        	Log.i(MainActivity.LOGTAG, "Pass in userid, and bankname");
	        	startActivity(intent);
	        	break;
	        default:
	        	break;
	    }
	            return super.onOptionsItemSelected(item);
	}
}
