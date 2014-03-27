package fiveminions.financialreportmvpversion;

import java.util.List;

import model.BankAccount;

import database.FinancialAccountSource;

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

public class AccountPageActivity extends ListActivity{
	
	transient private String userid;
	transient private FinancialAccountSource datasource;
	transient private List<BankAccount> accounts;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.account_page);
		Bundle b = getIntent().getExtras();
		userid = b.getString("userid");
		datasource = new FinancialAccountSource(this);
		datasource.open();
		display();
	}

	public void display(){
		accounts = datasource.getAccountList(userid);
		ArrayAdapter<BankAccount> adapter = new ArrayAdapter<BankAccount>(this, R.layout.list_view1, accounts);
		setListAdapter(adapter);
		Log.i(MainActivity.LOGTAG, "Refresh Account List");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.account_menu, menu);
		return super.onCreateOptionsMenu(menu);
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
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	    switch (item.getItemId()) {
	        case R.id.add_new_account:
	        	Intent intent = new Intent(this, NewAccountActivity.class);
	        	intent.putExtra("userid", userid);
	        	Log.i(MainActivity.LOGTAG, "Pass in userid");
	        	startActivity(intent);
	        	break;
	        default:
	        	break;
	    }
	            return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		BankAccount baccount = accounts.get(position);
		Intent intent = new Intent(this, BankAccountDetailActivity.class);
		intent.putExtra("model.BankAccount", baccount);
		startActivity(intent);
	}
	
}
