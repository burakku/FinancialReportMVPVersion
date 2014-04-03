package fiveminions.financialreportmvpversion;

import java.util.List;

import database.FinancialTransactionSource;

import model.Transaction;
import presenters.AddTransactionPresenter;
import views.IAddTransactionView;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * This class describes the protected and public methods 
 * needed for the activity of add transactions. These 
 * methods hold the name, date, amount, type and other 
 * informations for the transaction being added. 
 * @version 1.0
 * @author Team 23
 */
public class AddTransactionActivity extends Activity implements IAddTransactionView, OnItemSelectedListener {
    
    private AddTransactionPresenter presenter;
    private int year; // NOPMD by wen on 4/2/14 1:57 AM
    private int month; // NOPMD by wen on 4/2/14 1:57 AM
    private int day; // NOPMD by wen on 4/2/14 1:57 AM
    private EditText name;
    private DatePicker date; // NOPMD by wen on 4/2/14 1:58 AM
    private EditText amount;
    private String type;
    private String bankname;
    private String userid;
    private TextView text;
    private Spinner typeSpinner; // NOPMD by wen on 4/2/14 1:57 AM
    private List<String> categories; // NOPMD by wen on 4/2/14 1:58 AM
    private FinancialTransactionSource datasource;
    private Bundle boudle; // NOPMD by farongcheng on 4/2/14 12:46 PM
    @Override
    protected void onCreate(Bundle savedInstanceState) { // NOPMD by wen on 4/2/14 1:58 AM
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);
        presenter = new AddTransactionPresenter(this);
        datasource = new FinancialTransactionSource(this);
        boudle = getIntent().getExtras(); // NOPMD by wen on 4/2/14 1:58 AM
        bankname = boudle.getString("bankname");
        userid = boudle.getString("userid");
        text = (TextView) findViewById(R.id.tranText);
        name = (EditText) findViewById(R.id.tranName);
        date = (DatePicker) findViewById(R.id.tranDate);
        amount = (EditText) findViewById(R.id.tranAmount);
        typeSpinner = (Spinner) findViewById(R.id.tranTypeSpinner);
        typeSpinner.setOnItemSelectedListener(this);
        date.setCalendarViewShown(false);
        
        categories = presenter.getTypeList();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new 
                ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
            long theid) {
        // On selecting a spinner item
        type = parent.getItemAtPosition(position).toString(); // NOPMD by wen on 4/2/14 1:56 AM
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    protected void onResume() {
        super.onResume();
        datasource.open();
    }
    
    @Override
    protected void onPause() {
        super.onPause();
        datasource.close(); // NOPMD by wen on 4/2/14 1:56 AM
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.add_transaction, menu); // NOPMD by wen on 4/2/14 1:58 AM
        return true;
    }
    
    /**
     * Call the sumbitClick method in presenter.
     * @param view the View
     */
    public void onSubmitClick(View view) {
        presenter.onSubmitClick();
    }
    
    @Override
    public String getName() {
        return name.getText().toString(); // NOPMD by wen on 4/2/14 1:57 AM
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getDate() {
        year = date.getYear(); // NOPMD by wen on 4/2/14 1:57 AM
        month = date.getMonth(); // NOPMD by wen on 4/2/14 1:57 AM
        day = date.getDayOfMonth(); // NOPMD by wen on 4/2/14 1:58 AM
        String date = year + "-" + month + "-" + day;
        return date; // NOPMD by wen on 4/2/14 1:57 AM
    }

    @Override
    public String getAmount() {
        return amount.getText().toString(); // NOPMD by wen on 4/2/14 1:58 AM
    }


    @Override
    public String getBKDisname() {
        return bankname;
    }

    @Override
    public boolean addTrans(Transaction transaction) {
        boolean flag = false;
        if (datasource.addTransaction(transaction)) {
            flag = true;
        }
        return flag;
    }

    @Override
    public void setText(String string) {
        text.setText(string);
    }

    @Override
    public void goBack() {
        finish();
    }

    @Override
    public String getUserid() {
        return userid;
    }
}
