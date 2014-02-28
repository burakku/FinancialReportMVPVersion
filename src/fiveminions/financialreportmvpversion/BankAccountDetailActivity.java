package fiveminions.financialreportmvpversion;


import model.BankAccount;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;
import database.FinancialAccountSource;


public class BankAccountDetailActivity extends Activity {
	public static final String LOGTAG = "CLOVER";
			String test;
	private TextView bankname;
	private TextView accountname;
	private TextView userid;
	private TextView balance;
	private TextView mir;
	private BankAccount baccount; 
	private FinancialAccountSource datasource;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bank_account_detail);
		bankname = (TextView) findViewById(R.id.bankname);
		userid = (TextView) findViewById(R.id.userid);
		accountname = (TextView) findViewById(R.id.accountname);
		balance = (TextView) findViewById(R.id.balance);
		mir = (TextView) findViewById(R.id.mir);
		
		Bundle b = getIntent().getExtras();
		baccount = b.getParcelable("model.BankAccount");
		display();
		

		datasource = new FinancialAccountSource(this);
		datasource.open();
		
		
	}
	
	private void display(){
		userid.setText(baccount.getUserid());
		bankname.setText(baccount.getName());
		accountname.setText(baccount.getDisname());
		balance.setText(Double.toString(baccount.getBalance()));
		mir.setText(Double.toString(baccount.getMir()));		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bank_account_detail, menu);
		return true;
	}
	
	
	public void onResetPWClick(){
		
	}
	
	public void onDeleteAccountClick(){
		
	}

}
