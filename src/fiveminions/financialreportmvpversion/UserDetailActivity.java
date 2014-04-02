package fiveminions.financialreportmvpversion;

import database.FinancialUserSource;
import model.User;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * This is a subclass describes the  methods needed for 
 * the activity of user detail transaction which can set 
 * the text view of name, user ID, password and so forth. 
 * @version 1.0
 * @author Team 23
 */
public class UserDetailActivity extends Activity {

    private TextView name;
    private TextView userid;
    private TextView password;
    private TextView email;
    private User user;
    private FinancialUserSource datasource;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // NOPMD by wen on 4/2/14 1:41 AM
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_page_detail);
        name = (TextView) findViewById(R.id.name);
        userid = (TextView) findViewById(R.id.username);
        password = (TextView) findViewById(R.id.password);
        email = (TextView) findViewById(R.id.email);
        Bundle boundle = getIntent().getExtras();  // NOPMD by wen on 4/2/14 1:42 AM
        user = boundle.getParcelable("model.User");
        display();
        builder = new AlertDialog.Builder(this);
        datasource = new FinancialUserSource(this);
        datasource.open();
    }
    /**
     * refresh list.
     */
    private void display() {
        name.setText(user.getName());
        password.setText(user.getPassword());
        userid.setText(user.getUserID());
        email.setText(user.getEmail());
    }
    /**
     * reset password button click listener.
     * @param view The view
     */
    public void onResetPWClick(View view) {
        builder.setMessage(R.string.reset_pw);
        builder.setTitle("Password Reset!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int theid) {
                datasource.resetPW(user.getUserID());
                finish();
            }
        });
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int theid) {
                        dialog.dismiss();
                    }
                });
        dialog = builder.create();
        dialog.show();
    }
    /**
     * delete user button click listener.
     * @param view The View
     */
    public void onDeleteUserClick(View view) {
        builder.setMessage(R.string.remove_user);
        builder.setTitle("Delete User!");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int theid) {
                datasource.removeUser(user.getUserID());
                finish();
            }
        });
        builder.setNegativeButton("Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int theid) {
                        dialog.dismiss();
                    }
                });
        dialog = builder.create();
        dialog.show();
    }
}
