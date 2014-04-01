package database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This subclass describes of SQLiteOpenHelper class 
 * contains the methods that help and financial DB to 
 * be opened.
 * @version 1.0
 * @author Team 23
 */
public class FinancialDBOpenHelper extends SQLiteOpenHelper{
	private static final String LOGTAG = "CLOVER";
	
	private static final String DATABASE_NAME = "clover.db";
	private static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_USERS = "users";
	public static final String COLUMN_USERID = "userID";
	public static final String COLUMN_PASSWORD = "password";
	public static final String COLUMN_NAME = "name";
	public static final String COLUMN_EMAIL = "email";
	public static final String COLUMN_TYPE = "type_of_user";
	
	private static final String USER_TABLE_CREATE = 
			"CREATE TABLE " + TABLE_USERS + "( " +
			COLUMN_USERID + " TEXT PRIMARY KEY, " + COLUMN_PASSWORD + " TEXT, " // NOPMD by hailin on 3/31/14 8:11 PM
		    + COLUMN_NAME + " TEXT, " + COLUMN_EMAIL + " TEXT, " + COLUMN_TYPE + " TEXT )";
	
	public static final String TABLE_ACCOUNTS = "accounts";
	public static final String COLUMN_ACNAME = "acName";
	public static final String COLUMN_DISNAME = "disName";
	public static final String COLUMN_BALANCE = "balance";
	public static final String COLUMN_MIR = "mirate";
	public static final String COLUMN_ACUSERID = "acUserID";
	
	private static final String ACT_TABLE_CREATE = 
			"CREATE TABLE " + TABLE_ACCOUNTS + "( " +
			COLUMN_ACNAME + " TEXT, " + COLUMN_DISNAME + " TEXT, "
		    + COLUMN_BALANCE + " DOUBLE, " + COLUMN_MIR + " DOUBLE, "
			+ COLUMN_ACUSERID + " TEXT NOT NULL,"
		    + "PRIMARY KEY(" + COLUMN_DISNAME +"," + COLUMN_ACUSERID + ") "
		    + "FOREIGN KEY(" + COLUMN_ACUSERID + ") REFERENCES " + TABLE_USERS
		    + "(" + COLUMN_USERID+  ")" +")";
	
	public static final String TABLE_TRANS = "transactions";
	public static final String COLUMN_TRNAME = "trName";
	public static final String COLUMN_TRTYPE = "trType";
	public static final String COLUMN_TRDATE = "trDate";
	public static final String COLUMN_TRAMOUNT = "trAmount";
	public static final String COLUMN_TRSTATUS = "trStatus";
	public static final String COLUMN_TRRECORD = "trRecordTime";
	public static final String COLUMN_TBDNAME = "trBKDisName";
	public static final String COLUMN_TRUSERID = "trUserID";
	
	private static final String TRAN_TABLE_CREATE = 
			"CREATE TABLE " + TABLE_TRANS + "( " +
			COLUMN_TRNAME + " TEXT, " + COLUMN_TRTYPE + " TEXT, "
		    + COLUMN_TRDATE + " DATE, " + COLUMN_TRAMOUNT + " DOUBLE, "
			+ COLUMN_TRSTATUS + " TEXT,"+ COLUMN_TRRECORD + " DATE,"
			+ COLUMN_TBDNAME +" TEXT," + COLUMN_TRUSERID + " TEXT,"
		    + "FOREIGN KEY(" + COLUMN_TBDNAME +") REFERENCES " + TABLE_ACCOUNTS
		    + "(" + COLUMN_DISNAME + ")" 
		    + "FOREIGN KEY(" + COLUMN_TRUSERID +") REFERENCES " + TABLE_USERS
		    + "(" + COLUMN_USERID + ")"+")"; 
	/**
	 * Constructor for FinancialDBOpenHelper calling
	 * constructor of super class
	 * 
	 * @param context context of the account source
	 */
	public FinancialDBOpenHelper(final Context context) {
		super(context, DATABASE_NAME, null,DATABASE_VERSION);
	}

	@Override
	public void onCreate(final SQLiteDatabase database) {
		final ContentValues values = new ContentValues();
		values.put(FinancialDBOpenHelper.COLUMN_USERID,"admin");
		values.put(FinancialDBOpenHelper.COLUMN_PASSWORD, "pass123");
		values.put(FinancialDBOpenHelper.COLUMN_NAME, "administrator");
		values.put(FinancialDBOpenHelper.COLUMN_EMAIL, "admin@gatech.edu");
		values.put(FinancialDBOpenHelper.COLUMN_TYPE, "admin");
		database.execSQL(USER_TABLE_CREATE);
		Log.i(LOGTAG, "Users Table has been created");
		database.insert(FinancialDBOpenHelper.TABLE_USERS, null, values);
		Log.i(LOGTAG, "Admin has been created");
		database.execSQL(ACT_TABLE_CREATE);
		Log.i(LOGTAG, "Accounts Table has been created");
		database.execSQL(TRAN_TABLE_CREATE);
		Log.i(LOGTAG, "Transaction Table has been created");
	}

	@Override
	public void onUpgrade(final SQLiteDatabase database,
			final int oldVersion, final int newVersion) {
		database.execSQL("DROP TABLE IF EXISTS "+ TABLE_USERS);
		database.execSQL("DROP TABLE IF EXISTS "+ TABLE_ACCOUNTS);
		database.execSQL("DROP TABLE IF EXISTS "+ TABLE_TRANS);
		onCreate(database);
	}

}
