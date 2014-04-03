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

/***
 * This class describes the methods needed for the 
 * activity of account page which include display,
 * create option menu, resume, pause and so forth. 
 * @version 1.0
 * @author Team 23
 */
public class AccountPageActivity extends ListActivity {
    
	/**
	 * passin userid.
	 */
    private String userid;
    /**
     * database source.
     */
    private FinancialAccountSource datasource;
    /**
     * list of bank account.
     */
    private List<BankAccount> accounts;
    @Override
    protected void onCreate(Bundle savedInstanceState) { // NOPMD by wen on 4/2/14 2:00 AM
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_page);
        Bundle b = getIntent().getExtras(); // NOPMD by wen on 4/2/14 2:00 AM
        userid = b.getString("userid");
        datasource = new FinancialAccountSource(this);
        datasource.open();
        display();
    }

   /**
    * refresh the list view.
    */
    public void display() {
        accounts = datasource.getAccountList(userid);
        final ArrayAdapter<BankAccount> adapter = 
                new ArrayAdapter<BankAccount>(this, R.layout.list_view1, accounts);
        setListAdapter(adapter);
        Log.i(MainActivity.LOGTAG, "Refresh Account List");
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.account_menu, menu); // NOPMD by wen on 4/2/14 2:00 AM
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
        switch (item.getItemId()) { // NOPMD by wen on 4/2/14 1:59 AM
            case R.id.add_new_account:
                final Intent intent = 
                    new Intent(this, NewAccountActivity.class);
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
    protected void onListItemClick(ListView listView,
                View view, int position, long theid) {
        super.onListItemClick(listView, view, position, theid);
        final BankAccount baccount = accounts.get(position);
        final Intent intent =
            new Intent(this, BankAccountDetailActivity.class);
        intent.putExtra("model.BankAccount", baccount);
        startActivity(intent);
    }
}
