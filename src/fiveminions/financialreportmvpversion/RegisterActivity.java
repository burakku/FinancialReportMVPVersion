package fiveminions.financialreportmvpversion;


import database.FinancialUserSource;
import model.User;
import presenters.RegisterPresenter;
import views.IRegisterView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends Activity implements IRegisterView{

	private RegisterPresenter regPresenter;
	EditText name, userID, password,email;
	TextView resultTxt;
	private FinancialUserSource datasource;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.register);
		regPresenter = new RegisterPresenter(this);
		
		name = (EditText) findViewById(R.id.regName);
		userID = (EditText) findViewById(R.id.regUserid);
		password = (EditText) findViewById(R.id.regPassword);
		email = (EditText) findViewById(R.id.regEmail);
		datasource = new FinancialUserSource(this);
		
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		datasource.open();
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		datasource.close();
	}
	
	public void onSignUpButtonClick(View v){
		regPresenter.onClick();
	}

	@Override
	public String getUserid() {
		return userID.getText().toString();
	}

	@Override
	public String getPassword() {
		return password.getText().toString();
	}

	@Override
	public String getName() {
		return name.getText().toString();
	}

	@Override
	public void setRegisterText(String text) {
		resultTxt.setText(text);
		
	}

	@Override
	public void goUserPage() {
		Intent intent = new Intent(this, UserpageActivity.class );
		startActivity(intent);		
	}

	@Override
	public String getEmail() {
		return email.getText().toString();
	}

	@Override
	public void addUser(User user) {
		datasource.addUser(user);	
		
	}

	@Override
	public User findUser(String uid) {
		return datasource.findUser(uid);
	}

}
