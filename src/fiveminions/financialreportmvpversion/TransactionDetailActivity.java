package fiveminions.financialreportmvpversion;

import database.FinancialTransactionSource;
import model.Transaction;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class TransactionDetailActivity extends Activity {
	
	private TextView tran_bkname;
	private TextView tran_name;
	private TextView tran_type;
	private TextView tran_status;
	private TextView tran_date;
	private TextView tran_amount;
	private Transaction tran;
	private FinancialTransactionSource datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction_detail_activity);
		
		tran_bkname = (TextView) findViewById(R.id.tran_bkname);
		tran_name = (TextView) findViewById(R.id.tran_name);
		tran_type = (TextView) findViewById(R.id.tran_type);
		tran_status = (TextView) findViewById(R.id.tran_status);
		tran_date = (TextView) findViewById(R.id.tran_date);
		tran_amount = (TextView) findViewById(R.id.tran_amount);
		
		Bundle b = getIntent().getExtras();
		tran = b.getParcelable("Model.Transaction");
		display();
		
		datasource = new FinancialTransactionSource(this);
		datasource.open();
	}

	private void display() {
		tran_bkname.setText(tran.getBkDisName());
		tran_name.setText(tran.getName());
		tran_type.setText(tran.getType());
		tran_status.setText(tran.getStatus());
		tran_date.setText(tran.getDate());
		tran_amount.setText(Double.toString(tran.getAmount()));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction_detail_acitivity, menu);
		return true;
	}

}
