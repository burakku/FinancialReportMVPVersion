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
public class FinancialDBOpenHelper extends SQLiteOpenHelper {
/**
 * string variable for log cat.
 */
    private static final String LOGTAG = "CLOVER";

/**
 * database name.
 */
    private static final String DATABASE_NAME = "clover.db";
 
/**
 * database version.
 */
    private static final int DATABASE_VERSION = 1;

/**
 * user table name.    
 */
    public static final String TABLE_USERS = "users";
 
/**
 * user ID column.
 */
    public static final String COLUMN_USERID = "userID";

/**
 * user password column.
 */
    public static final String COLUMN_PASSWORD = "password";
    
/**
 * user name column.
 */
    public static final String COLUMN_NAME = "name";
    
/**
 * user email column.
 */
    public static final String COLUMN_EMAIL = "email";
    
/**
 * user type column.
 */
    public static final String COLUMN_TYPE = "type_of_user";
	
/**
 * create table statement string.
 */
    private static final String USER_TABLE_CREATE = "CREATE TABLE " + TABLE_USERS + "( "
			+ COLUMN_USERID + " TEXT PRIMARY KEY, " + COLUMN_PASSWORD + " TEXT, " // NOPMD by hailin on 3/31/14 8:11 PM
		    + COLUMN_NAME + " TEXT, " + COLUMN_EMAIL + " TEXT, " + COLUMN_TYPE + " TEXT )";

/**
 * account table.
 */
    public static final String TABLE_ACCOUNTS = "accounts";
 
/**
 * account name column.
 */
    public static final String COLUMN_ACNAME = "acName";
    
/**
 * account display name column.
 */
    public static final String COLUMN_DISNAME = "disName";
    
/**
 * account balance column.
 */
    public static final String COLUMN_BALANCE = "balance";
    
/**
 * monthly interest rate column.
 */
    public static final String COLUMN_MIR = "mirate";
    
/**
 * user ID column.
 */
    public static final String COLUMN_ACUSERID = "acUserID";

 /*
  * string use to create account table.
  */
    private static final String ACT_TABLE_CREATE = 
			"CREATE TABLE " + TABLE_ACCOUNTS + "( " +
			COLUMN_ACNAME + " TEXT, " + COLUMN_DISNAME + " TEXT, "
		    + COLUMN_BALANCE + " DOUBLE, " + COLUMN_MIR + " DOUBLE, "
			+ COLUMN_ACUSERID + " TEXT NOT NULL,"
		    + "PRIMARY KEY(" + COLUMN_DISNAME + "," + COLUMN_ACUSERID + ") "
		    + "FOREIGN KEY(" + COLUMN_ACUSERID + ") REFERENCES " + TABLE_USERS
		    + "(" + COLUMN_USERID+  ")" +")";
	
/**
 * transaction table.
 */
    public static final String TABLE_TRANS = "transactions";

/**
 * transaction name column.
 */
    public static final String COLUMN_TRNAME = "trName";
    
/**
 * transaction type column.
 */
    public static final String COLUMN_TRTYPE = "trType";

/**
 * transaction date column.
 */
    public static final String COLUMN_TRDATE = "trDate";

/**
 * transaction amount column.
 */
    public static final String COLUMN_TRAMOUNT = "trAmount";
 
/**
 * transaction status column.
 */
    public static final String COLUMN_TRSTATUS = "trStatus";
    
/**
 * transaction time column.
 */
    public static final String COLUMN_TRRECORD = "trRecordTime";
    
/**
 * bank account name column.
 */
    public static final String COLUMN_TBDNAME = "trBKDisName";
 
/**
 * user id column.
 */
    public static final String COLUMN_TRUSERID = "trUserID";
	
/**
 *  string use to create transaction table.
 */
    private static final String TRAN_TABLE_CREATE = 
			"CREATE TABLE " + TABLE_TRANS + "( "
			+ COLUMN_TRNAME + " TEXT, " + COLUMN_TRTYPE + " TEXT, "
		    + COLUMN_TRDATE + " DATE, " + COLUMN_TRAMOUNT + " DOUBLE, "
			+ COLUMN_TRSTATUS + " TEXT," + COLUMN_TRRECORD + " DATE,"
			+ COLUMN_TBDNAME + " TEXT," + COLUMN_TRUSERID + " TEXT,"
		    + "FOREIGN KEY(" + COLUMN_TBDNAME + ") REFERENCES " + TABLE_ACCOUNTS
		    + "(" + COLUMN_DISNAME + ")" 
		    + "FOREIGN KEY(" + COLUMN_TRUSERID + ") REFERENCES " + TABLE_USERS
		    + "(" + COLUMN_USERID + ")" + ")"; 
/**
 * Constructor for FinancialDBOpenHelper calling
 * constructor of super class.
 * 
 * @param context context of the account source
 */
    public FinancialDBOpenHelper(final Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(final SQLiteDatabase database) {
        final ContentValues values = new ContentValues();
        values.put(FinancialDBOpenHelper.COLUMN_USERID, "admin");
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
    public void onUpgrade(final SQLiteDatabase database, final int oldVersion, final int newVersion) {
    	database.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
    	database.execSQL("DROP TABLE IF EXISTS " + TABLE_ACCOUNTS);
    	database.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANS);
    	onCreate(database);
    }

}
