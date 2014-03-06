package fiveminions.financialreportmvpversion;

import database.FinancialTransactionSource;
import model.Transaction;
import android.os.Bundle;
import android.app.Activity;
import android.graphics.Typeface;
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
		tran = b.getParcelable("model.Transaction");
		setFont();
		display();
		
		datasource = new FinancialTransactionSource(this);
		datasource.open();
	}

	private void setFont() {
		Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeueLight.ttf");
		Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/jennifer-bold.ttf");
		
		TextView textView1 = (TextView)findViewById(R.id.transD_textView1);
		TextView textView2 = (TextView)findViewById(R.id.transD_textView2);
		TextView textView3 = (TextView)findViewById(R.id.transD_textView3);
		TextView textView4 = (TextView)findViewById(R.id.transD_textView4);
		TextView textView6 = (TextView)findViewById(R.id.transD_textView5);
		TextView textView7 = (TextView)findViewById(R.id.transD_textView6);
		TextView textView5 = (TextView)findViewById(R.id.transD_title);
		textView1.setTypeface(typeface);
		textView2.setTypeface(typeface);
		textView3.setTypeface(typeface);
		textView4.setTypeface(typeface);
		textView5.setTypeface(typeface1);
		textView6.setTypeface(typeface);
		textView7.setTypeface(typeface);
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
