package fiveminions.financialreportmvpversion;


import database.FinancialUserSource;
import model.User;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class UserDetailActivity extends Activity{

	private TextView name;
	private TextView userid;
	private TextView password;
	private TextView email;
	private User user;
	private FinancialUserSource datasource;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.admin_page_detail);
		name = (TextView) findViewById(R.id.name);
		userid = (TextView) findViewById(R.id.username);
		password = (TextView) findViewById(R.id.password);
		email = (TextView) findViewById(R.id.email);
		Bundle b = getIntent().getExtras();
		user = b.getParcelable("model.User");
		display();
		
		datasource = new FinancialUserSource(this);
		datasource.open();
	}
	
	private void display(){
		name.setText(user.getName());
		password.setText(user.getPassword());
		userid.setText(user.getUserID());
		email.setText(user.getEmail());
	}
	
	public void onResetPWClick(View v){
		
	}
	
	public void onDeleteUserClick(View v){
		datasource.removeUser(user.getUserID());
		finish();
	}
}
