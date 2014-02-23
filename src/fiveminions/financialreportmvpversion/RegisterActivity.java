package fiveminions.financialreportmvpversion;

import model.MemoryModel;
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
	EditText name, userID, password;
	TextView resultTxt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.register);
		regPresenter = new RegisterPresenter(this, new MemoryModel());
		
		name = (EditText) findViewById(R.id.regName);
		userID = (EditText) findViewById(R.id.regUserid);
		password = (EditText) findViewById(R.id.regPassword);
		
		
	}
	
	public void onSignUpButtonClick(View v){
		regPresenter.onClick();
	}

	@Override
	public String getUserid() {
		// TODO Auto-generated method stub
		return userID.toString();
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password.toString();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return name.toString();
	}

	@Override
	public void setRegisterText(String text) {
		// TODO Auto-generated method stub
		resultTxt.setText(text);
		
	}

	@Override
	public void goUserPage() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, UserpageActivity.class );
		startActivity(intent);		
	}

}
