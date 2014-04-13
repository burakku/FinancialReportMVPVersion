package model;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import fiveminions.financialreportmvpversion.MainActivity;

/**
 * This class can get and hold the information of a 
 * transaction by a bank user. One can get the name
 * of the user, transaction time and data and other 
 * kind of relative information of the transaction. 
 * @version 1.0
 * @author Team 23
 */
public class Transaction implements Parcelable {
	/**
	 * transaction name.
	 */
    private String name;
	/**
	 * transaction type.
	 */
    private String type;
	/**
	 * transaction date.
	 */
    private MyDate date;
	/**
	 * transaction amount.
	 */
    private double amount;
	/**
	 * transaction status.
	 */
    private String status;
	/**
	 * transaction recorded time.
	 */
    private String recordTime;
	/**
	 * transaction belong to which bank display name.
	 */
    private String bkDisName;
	/**
	 * transaction belong to which userid.
	 */
    private String userid;
    /**
     * transaction category.
     */
    private String category;
    /**
     * parcelable creator.
     */
    public static final Parcelable.Creator<Transaction> CREATOR = new Parcelable.Creator<Transaction>() {

        @Override
        public Transaction createFromParcel(final Parcel arg0) {
            Log.i(MainActivity.LOGTAG, "createFromParcel");
            return new Transaction(arg0);
        }

        @Override
        public Transaction[] newArray(final int arg0) {
            Log.i(MainActivity.LOGTAG, "newArray");
            return new Transaction[arg0];
        }

    };
    /**
     * Default constructor for Transaction. 
     *
     */
    public Transaction() {
        this.name = "";
        this.type = "";
        this.date = new MyDate();
        this.amount = 0;
        this.status = "pending";
        this.recordTime = getDateTime();
        this.bkDisName = "";
        this.userid = "";
        this.category= "";
    }
    /**
     * getDateTime method which returns the date of the transaction.
     *
     * @return date in format of yyyy-MM-dd
     */
    private String getDateTime() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                Locale.getDefault());
        final Date date = new Date();
        return dateFormat.format(date);
    }
    /**
     * Constructor for Transaction.
     * @param name the name of the person
     * @param type the type of the transaction
     * @param date the date of the transaction
     * @param amount the amount of the transaction
     * @param bkDisName the bank displaying name of the user
     * @param userid the userid that transaction belong to
     * @param category the category of the transaction
     */
    public Transaction(final String name, final String type, final MyDate date, final Double amount,
            final String bkDisName, final String userid, final String category) {
        this.name = name;
        this.type = type;
        this.date = date;
        this.amount = amount;
        this.status = "pending";
        this.recordTime = getDateTime();
        this.bkDisName = bkDisName;
        this.userid = userid;
        this.category = category;
        
    }
    /**
     * getName method.
     * 
     * @return name - the name of the user
     */
    public String getName() {
        return name;
    }
    /**
     * setName method which sets the name of the user.
     * 
     * @param name - the name of the user
     */
    public void setName(final String name) {
        this.name = name;
    }
    /**
     * getType method.
     * 
     * @return type - the type of the transaction
     */
    public String getType() {
        return type;
    }
    /**
     * setType method which sets the type of the transaction.
     * 
     * @param type - the type of the transaction
     */
    public void setType(final String type) {
        this.type = type;
    }
    /**
     * getDate method.
     * 
     * @return date - the date of the transaction
     */
    public MyDate getDate() {
        return date;
    }
    /**
     * setDate method which sets the date of the transaction.
     * 
     * @param date - the date of the transaction
     */
    public void setDate(final MyDate date) {
        this.date = date;
    }
    /**
     * getAmount method.
     * 
     * @return amount - the amount of the transaction
     */
    public double getAmount() {
        return amount;
    }
    /**
     * setAmount method which sets the amount of the transaction.
     * 
     * @param amount - the amount of the transaction
     */
    public void setAmount(final double amount) {
        this.amount = amount;
    }
    /**
     * getStatus method.
     * 
     * @return status - the status of the transaction
     */
    public String getStatus() {
        return status;
    }
    /**
     * setStatus method which sets the status of the transaction.
     * 
     * @param status - the status of the transaction
     */
    public void setStatus(final String status) {
        this.status = status;
    }
    /**
     * getRecordTime method.
     * 
     * @return recordTime - the recorded time of the transaction
     */
    public String getRecordTime() {
        return recordTime;
    }
    /**
     * setRecordTime method which sets the recording time of the transaction.
     * 
     * @param recordTime - the recording time of the transaction
     */
    public void setRecordTime(final String recordTime) {
        this.recordTime = recordTime;
    }
    /**
     * geBkDisName method.
     * 
     * @return bkDisName - the bank displaying name of the user
     */
    public String getBkDisName() {
        return bkDisName;
    }
    /**
     * setBkDisName method which sets the bank displaying name of the user.
     * @bkDisName bkDisName display name
     */
    public void setBkDisName(final String bkDisName) {
        this.bkDisName = bkDisName;
    }
    /**
     * getUserid method.
     * 
     * @return userid - the id of the user
     */
    public String getUserid() {
        return userid;
    }

    /**
     * setUserid method which sets the id of the user.
     * 
     * @param userid - the id of the user
     */
    public void setUserid(final String userid) {
        this.userid = userid;
    }
    /**
     * get the category type.
     * @return category
     */
	public String getCategory() {
		return category;
	}
   /**
    * set the category.
    * @param category the category
    */
	public void setCategory(String category) {
		this.category = category;
	}
    /**
     * toString method which return user name and the amount and date of 
     * the transaction.
     * 
     * @return user name and the amount and date of the transaction 
     */
    @Override
    public String toString() {
    	NumberFormat numForm = NumberFormat.getCurrencyInstance();
        return this.bkDisName + " : " + this.name
                + " : " + numForm.format(this.amount) + " ( " + this.date + ")";
    }
    /**
     * describeContents method which returns content.
     * 
     * @return 0
     */
    @Override
    public int describeContents() {
        return 0;
    }
    /**
     * Constructor for Transaction.
     * 
     * @param in 
     */
    public Transaction(final Parcel in) { // NOPMD by wen on 4/2/14 1:16 AM
        name = in.readString();
        type = in.readString();
        amount = in.readDouble();
        date = (MyDate) in.readValue(MyDate.class.getClassLoader()); // NOPMD by wen on 4/2/14 1:16 AM
        status = in.readString();
        recordTime = in.readString();
        bkDisName = in.readString();
        category = in.readString();
    }
    /**
     * writeToParcel method which writes the information of
     * the user to parcel.
     * 
     * @param dest destination
     * @param flag flag
     */
    @Override
    public void writeToParcel(final Parcel dest, final int flag) {
        Log.i(MainActivity.LOGTAG, "writeToParcel");
        dest.writeString(name);
        dest.writeString(type);
        dest.writeDouble(amount);
        dest.writeValue(date);
        dest.writeString(status);
        dest.writeString(recordTime);
        dest.writeString(bkDisName);
        dest.writeString(category);
    }

    

}
