package fiveminions.financialreportmvpversion;

import views.IAddTransactionView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AddTransactionActivity extends Activity implements IAddTransactionView{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_transaction);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_transaction, menu);
		return true;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAmount() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRecordTime() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBKDisname() {
		// TODO Auto-generated method stub
		return null;
	}

}
