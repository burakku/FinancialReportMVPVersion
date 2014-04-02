package fiveminions.financialreportmvpversion;

import database.FinancialUserSource;
import model.User;
import presenters.RegisterPresenter;
import views.IRegisterView;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This is a subclass of Activity which contains 
 * the public methods needed for the activity of 
 * register to find user, add user and get the info 
 * of users. 
 * @version 1.0
 * @author Team 23
 */
public class RegisterActivity extends Activity implements IRegisterView {

	private RegisterPresenter regPresenter;
	EditText name, userID, password, email;
	TextView resultTxt;
	private FinancialUserSource datasource;

	@Override
	protected void onCreate(Bundle savedInstanceState) { // NOPMD by wen on 4/2/14 1:49 AM
		super.onCreate(savedInstanceState);

		setContentView(R.layout.register);
		regPresenter = new RegisterPresenter(this);

		name = (EditText) findViewById(R.id.regName);
		userID = (EditText) findViewById(R.id.regUserid);
		password = (EditText) findViewById(R.id.regPassword);
		email = (EditText) findViewById(R.id.regEmail);
		resultTxt = (TextView) findViewById(R.id.regText);
		datasource = new FinancialUserSource(this);
		setFont();
		
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
		finish(); // NOPMD by wen on 4/2/14 1:49 AM
	}

	public void onSignUpButtonClick(View view) {
		regPresenter.onClick();
	}

	@Override
	public String getUserid() {
		return userID.getText().toString(); // NOPMD by wen on 4/2/14 1:48 AM
	}

	@Override
	public String getPassword() {
		return password.getText().toString(); // NOPMD by wen on 4/2/14 1:49 AM
	}

	@Override
	public String getName() {
		return name.getText().toString(); // NOPMD by wen on 4/2/14 1:49 AM
	}

	@Override
	public void setRegisterText(String text) {
		resultTxt.setText(text);

	}

	@Override
	public void goLoginPage() {
		Intent intent = new Intent(this, LoginActivity.class);
		startActivity(intent);
	}

	@Override
	public String getEmail() {
		return email.getText().toString(); // NOPMD by wen on 4/2/14 1:48 AM
	}

	@Override
	public void addUser(User user) {
		datasource.addUser(user);

	}

	@Override
	public User findUser(String uid) {
		return datasource.findUser(uid);
	}
	
	public void setFont() {
		Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeueLight.ttf");
		Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/jennifer-bold.ttf");
		TextView textView1 = (TextView)findViewById(R.id.register_text_name);
		TextView textView2 = (TextView)findViewById(R.id.register_text_userid);
		TextView textView3 = (TextView)findViewById(R.id.register_text_password);
		TextView textView4 = (TextView)findViewById(R.id.textEmail);
		TextView textView5 = (TextView)findViewById(R.id.register_title);
		textView1.setTypeface(typeface); // NOPMD by wen on 4/2/14 1:48 AM
		textView2.setTypeface(typeface); // NOPMD by wen on 4/2/14 1:48 AM
		textView3.setTypeface(typeface); // NOPMD by wen on 4/2/14 1:48 AM
		textView4.setTypeface(typeface); // NOPMD by wen on 4/2/14 1:48 AM
		textView5.setTypeface(typeface1); // NOPMD by wen on 4/2/14 1:48 AM
	}

}
