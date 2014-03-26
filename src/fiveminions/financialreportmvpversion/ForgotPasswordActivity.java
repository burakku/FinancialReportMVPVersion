package fiveminions.financialreportmvpversion;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ForgotPasswordActivity extends Activity implements View.OnClickListener {

		EditText personsEmail, name , userName;
		String sEmail, sName, sUerName, adminEmail;
		Button sendEmail;
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.email);
			adminEmail = "cloverAppManager@gmail.com";
			initializeVars();
			sendEmail.setOnClickListener(this);
		}
		private void initializeVars() {
			// TODO Auto-generated method stub
			personsEmail = (EditText) findViewById(R.id.etEmails);
			name = (EditText) findViewById(R.id.etName);
			userName = (EditText) findViewById(R.id.etUsername);
			sendEmail = (Button) findViewById(R.id.bSentEmail);
		}
		public void onClick(View v) {
			// TODO Auto-generated method stub
			convertEditTextVarsIntoStrings();
			String emailaddress[] = { adminEmail };
			String message = "password reset request:\nname: "
					+ sName
					+ "\nuserName: "
					+ sUerName
					+"\nsend from email adress: "
					+ sEmail
					;
			
			// Send the email
			
			Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
			emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, emailaddress);
			emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "password reset request!");
			emailIntent.setType("plain/text");
			emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, message);
			
			
			try{
				startActivity(emailIntent);
			}catch(Exception e){
				e.printStackTrace();
			}
				
				
		}
		private void convertEditTextVarsIntoStrings() {
			// TODO Auto-generated method stub
			sEmail = personsEmail.getText().toString();
			sName = name.getText().toString();
			sUerName = userName.getText().toString();
			
		}
		@Override
		protected void onPause() {
			// TODO Auto-generated method stub
			super.onPause();
			finish();
		}
	}

