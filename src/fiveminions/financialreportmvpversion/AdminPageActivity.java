package fiveminions.financialreportmvpversion;

import java.util.List;

import model.User;

import database.FinancialUserSource;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * This class describes the methods needed for the 
 * activity of administrator page. This class is for 
 * administrator of the bank to process the info for 
 * the bank activities.  
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class AdminPageActivity extends ListActivity{

	private FinancialUserSource datasource;
	private List<User> users;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.adminpage);
		datasource = new FinancialUserSource(this);
		datasource.open();
		display();
	}
	
	public void display(){
		users = datasource.getUserList();
		ArrayAdapter<User> adapter = new ArrayAdapter<User>(this, R.layout.list_view1, users);
		setListAdapter(adapter);
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
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		User user = users.get(position);
		Intent intent = new Intent(this, UserDetailActivity.class);
		intent.putExtra("model.User", user);
		startActivity(intent);
	}
}
