package database;

import java.util.ArrayList;
import java.util.List;

import model.Transaction;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class FinancialReportGenerator {
	public static final String LOGTAG = "CLOVER";

	SQLiteOpenHelper dbhelper;
	SQLiteDatabase database;
	
	public FinancialReportGenerator(final Context context) {
		dbhelper = new FinancialDBOpenHelper(context);
	}
	
	public void open() {
		Log.i(LOGTAG, "Account Databases opened");
		database = dbhelper.getWritableDatabase();
	}

	public void close() {
		Log.i(LOGTAG, "Account Databases closed");
		database.close();
	}
		
	public List<Transaction> getSpendingList(String date, String userid){
		final List<Transaction> trs = new ArrayList<Transaction>();
		final Cursor cursor = database.query(FinancialDBOpenHelper.TABLE_TRANS, FinancialTransactionSource.TRANSCOLUMNS,
				FinancialDBOpenHelper.COLUMN_TRTYPE + " = " + "'Withdrawl' AND "
				 + "strftime('%Y%m'," + FinancialDBOpenHelper.COLUMN_TRDATE + ") = " + "'" + date + "' AND " +
				FinancialDBOpenHelper.COLUMN_TRUSERID + " = " + "'"+ userid + "'",
				 null, null, null, null);
		Log.i(LOGTAG, "Find " + cursor.getCount() + " rows");
		return FinancialTransactionSource.cursorTransaction(cursor, trs);
	}
	
	public Double getTotal(final List<Transaction> list){
		final List<Transaction> trs = list;
		double total = 0;
		for(int i=0; i < trs.size(); i++){
			total += trs.get(i).getAmount();
		}
		return total;
		
	}
	
}
