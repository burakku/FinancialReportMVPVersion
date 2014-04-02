package fiveminions.financialreportmvpversion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
/**
 * The class to set up method for reset password,
 * and send email method.
 * @author Team23
 *
 */
public class ForgotPasswordActivity extends Activity implements
        View.OnClickListener {

    private EditText personsEmail, userName;
    private String sEmail, sUerName, adminEmail;
    private Button sendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // NOPMD by wen on
                                                            // 4/2/14 1:53 AM
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email);
        adminEmail = "cloverAppManager@gmail.com";
        // password: clover123
        initializeVars();
        sendEmail.setOnClickListener(this);
    }
    
    /**
     * initialize the variables.
     */
    private void initializeVars() {
        // TODO Auto-generated method stub
        personsEmail = (EditText) findViewById(R.id.etEmails);

        userName = (EditText) findViewById(R.id.etUsername);
        sendEmail = (Button) findViewById(R.id.bSentEmail);
    }
    
    @Override
    public void onClick(View view) {
        // TODO Auto-generated method stub
        convertEditTextVarsIntoStrings();
        String emailaddress[] = {adminEmail};
        String message = "password reset request: " + "\nuserName: " + sUerName
                + "\nsend from email adress: " + sEmail;

        // Send the email

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailaddress);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "password reset request!");
        emailIntent.setType("plain/text");
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);

        try {
            startActivity(emailIntent);
        } catch (Exception e) { // NOPMD by wen on 4/2/14 1:53 AM
            e.printStackTrace(); // NOPMD by wen on 4/2/14 1:53 AM
        }

    }

    /**
     * Convert text to string.
     */
    private void convertEditTextVarsIntoStrings() {
        // TODO Auto-generated method stub
        sEmail = personsEmail.getText().toString(); // NOPMD by wen on 4/2/14
                                                    // 1:53 AM

        sUerName = userName.getText().toString(); // NOPMD by wen on 4/2/14 1:53
                                                    // AM

    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish(); // NOPMD by wen on 4/2/14 1:53 AM
    }
}
