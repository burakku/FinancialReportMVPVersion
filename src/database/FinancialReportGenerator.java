package database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Transaction;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class describes the public methods needed for 
 * generating the financial report. These methods allows
 * the report to be opened or closed and to get the total
 * amount and spending list of the financial report.
 * @version 1.0
 * @author Team 23
 */
public class FinancialReportGenerator {
/**
 * string variable for log cat.
 */
    public static final String LOGTAG = "CLOVER";

/**
 * database open helper.
 */
    SQLiteOpenHelper dbhelper; // NOPMD by hailin on 4/2/14 9:14 PM
    
/**
 * database.
 */
    SQLiteDatabase database; // NOPMD by hailin on 4/2/14 9:15 PM
    
/**
 * Constructor for FinancialReportGenerator.
 * 
 * @param context context of the account source
 */
    public FinancialReportGenerator(final Context context) {
        dbhelper = new FinancialDBOpenHelper(context);
    }
    
/**
 * void method to open the account source.
 */
    public void open() {
        Log.i(LOGTAG, "Account Databases opened");
        database = dbhelper.getWritableDatabase();
    }

/**
 * void method to close the account source.
 */
    public void close() {
        Log.i(LOGTAG, "Account Databases closed");
        database.close();
    }
    
/**
 * getSpendingList method.
 * 
 * @param date the date of the report
 * @param userid the ID of the user
 * @return the spending list of the report
 */
    public List<Transaction> getSpendingList(final String date, final String userid) {
        final List<Transaction> trs = new ArrayList<Transaction>();
        final Cursor cursor = database.query(FinancialDBOpenHelper.TABLE_TRANS, FinancialTransactionSource.TRANSCOLUMNS,
                FinancialDBOpenHelper.COLUMN_TRTYPE + " = " + "'Withdrawl' AND "
                + "strftime('%Y%m'," + FinancialDBOpenHelper.COLUMN_TRDATE + ") = " + "'" + date + "' AND " 
                + FinancialDBOpenHelper.COLUMN_TRUSERID + " = " + "'" + userid + "'", null, null, null, null);
        //Log.i(LOGTAG, "Find spending " + cursor.getCount() + " rows");
        return FinancialTransactionSource.cursorTransaction(cursor, trs);
    }
    /**
     * getIncomeList method.
     * 
     * @param date the date of the report
     * @param userid the ID of the user
     * @return the spending list of the report
     */    
    public List<Transaction> getIncomeList(String date, String userid) {
        List<Transaction> trs = new ArrayList<Transaction>();
        Cursor cursor = database.query(FinancialDBOpenHelper.TABLE_TRANS, FinancialTransactionSource.TRANSCOLUMNS,
                FinancialDBOpenHelper.COLUMN_TRTYPE + " = " + "'Deposit' AND "
                 + "strftime('%Y%m'," + FinancialDBOpenHelper.COLUMN_TRDATE + ") = " + "'" + date + "' AND "
                 + FinancialDBOpenHelper.COLUMN_TRUSERID + " = " + "'" + userid + "'", null, null, null, null);
        //Log.i(LOGTAG, "Find income " + cursor.getCount() + " rows");
        return FinancialTransactionSource.cursorTransaction(cursor, trs);
    }
    
    /**
     * get a Hash Map of spending category with name and amount.
     * @param date the date
     * @param userid the userid
     * @return category a Map of category
     */
    public Map<String, Double> getSpendingCategoryList(String date, String userid) {
        Map<String, Double> category = new HashMap<String, Double>();
        String[] cols = new String[]{FinancialDBOpenHelper.COLUMN_TRCATEGORY, FinancialDBOpenHelper.COLUMN_TRAMOUNT};
        Cursor cursor = database.query(FinancialDBOpenHelper.TABLE_TRANS, cols, 
                FinancialDBOpenHelper.COLUMN_TRTYPE + " = " + "'Withdrawl' AND "
                 + "strftime('%Y%m'," + FinancialDBOpenHelper.COLUMN_TRDATE + ") = " + "'" + date + "' AND "
                 + FinancialDBOpenHelper.COLUMN_TRUSERID + " = " + "'" + userid + "'",
                 null, FinancialDBOpenHelper.COLUMN_TRCATEGORY, null, null);
        //Log.i(LOGTAG, "Find spending category list " + cursor.getCount() + " rows");
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                category.put(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRCATEGORY)),
                        cursor.getDouble(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRAMOUNT)));
            }
        }
        return category;
    }
    
    /**
     * get a Hash Map of income category with name and amount.
     * @param date the date
     * @param userid the userid
     * @return category a Map of category
     */
    public Map<String, Double> getIncomeCategoryList(String date, String userid) {
        Map<String, Double> category = new HashMap<String, Double>();
        String[] cols = new String[]{FinancialDBOpenHelper.COLUMN_TRCATEGORY, FinancialDBOpenHelper.COLUMN_TRAMOUNT};
        Cursor cursor = database.query(FinancialDBOpenHelper.TABLE_TRANS, cols, 
                FinancialDBOpenHelper.COLUMN_TRTYPE + " = " + "'Deposit' AND "
                 + "strftime('%Y%m'," + FinancialDBOpenHelper.COLUMN_TRDATE + ") = " + "'" + date + "' AND " 
                 + FinancialDBOpenHelper.COLUMN_TRUSERID + " = " + "'" +  userid + "'",
                 null, FinancialDBOpenHelper.COLUMN_TRCATEGORY, null, null);
        Log.i(LOGTAG, "Find income category list " + cursor.getCount() + " rows");
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                category.put(cursor.getString(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRCATEGORY)),
                        cursor.getDouble(cursor.getColumnIndex(FinancialDBOpenHelper.COLUMN_TRAMOUNT)));
            }
        }
        return category;
    }
    
/**
 * getTotal method.
 * 
 * @param list the list of transaction
 * @return total the total amount of the transaction
 */
    public Double getTotal(final List<Transaction> list) {
        final List<Transaction> trs = list;
        double total = 0;
        for (int i = 0; i < trs.size(); i++) { // NOPMD by hailin on 4/2/14 9:15 PM
            total += trs.get(i).getAmount(); // NOPMD by hailin on 4/2/14 9:14 PM
        }
        return total;    
    }
}
