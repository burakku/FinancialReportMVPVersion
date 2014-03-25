package model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fiveminions.financialreportmvpversion.MainActivity;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * This class can get and hold the information of a 
 * transaction by a bank user. One can get the name
 * of the user, transaction time and data and other 
 * kind of relative information of the transaction. 
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class Transaction implements Parcelable {

	private String name;
	private String type;
	private myDate date;
	private double amount;
	private String status;
	private String recordTime;
	private String bkDisName;
	private String userid;
	/**
	 * Default constructor for Transaction 
	 *
	 */
	public Transaction() {
		this.name = "";
		this.type = "";
		this.date = new myDate();
		this.amount = 0;
		this.status = "pending";
		this.recordTime = getDateTime();
		this.bkDisName = "";
		this.userid= "";
	}
	/**
	 * getDateTime method which returns the date of the transaction 
	 *
	 * @return date in format of yyyy-MM-dd
	 */
	private String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}
	/**
	 * Constructor for Bank Account 
	 * 
	 * @param name the name of the person
	 * @param type the type of the transaction
	 * @param date the date of the transaction
	 * @param amount the amount of the transaction
	 * @param bkDisName the bank displaying name of the user
	 */
	public Transaction(String name, String type, myDate date, Double amount,
			String bkDisName, String userid) {
		this.name = name;
		this.type = type;
		this.date = date;
		this.amount = amount;
		this.status = "pending";
		this.recordTime = getDateTime();
		this.bkDisName = bkDisName;
		this.userid = userid;
	}
	/**
	 * getName method
	 * 
	 * @return name - the name of the user
	 */
	public String getName() {
		return name;
	}
	/**
	 * setName method which sets the name of the user
	 * 
	 * @param name - the name of the user
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * getType method 
	 * 
	 * @return type - the type of the transaction
	 */
	public String getType() {
		return type;
	}
	/**
	 * setType method which sets the type of the transaction
	 * 
	 * @param type - the type of the transaction
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * getDate method
	 * 
	 * @return date - the date of the transaction
	 */
	public myDate getDate() {
		return date;
	}
	/**
	 * setDate method which sets the date of the transaction
	 * 
	 * @param date - the date of the transaction
	 */
	public void setDate(myDate date) {
		this.date = date;
	}
	/**
	 * getAmount method
	 * 
	 * @return amount - the amount of the transaction
	 */
	public double getAmount() {
		return amount;
	}
	/**
	 * setAmount method which sets the amount of the transaction
	 * 
	 * @param amount - the amount of the transaction
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}
	/**
	 * getStatus method
	 * 
	 * @return status - the status of the transaction
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * setStatus method which sets the status of the transaction
	 * 
	 * @param status - the status of the transaction
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * getRecordTime method
	 * 
	 * @return recordTime - the recorded time of the transaction
	 */
	public String getRecordTime() {
		return recordTime;
	}
	/**
	 * setRecordTime method which sets the recording time of the transaction
	 * 
	 * @param recordTime - the recording time of the transaction
	 */
	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}
	/**
	 * geBkDisName method
	 * 
	 * @return bkDisName - the bank displaying name of the user
	 */
	public String getBkDisName() {
		return bkDisName;
	}
	/**
	 * setBkDisName method which sets the bank displaying name of the user
	 * 
	 * @return bkDisName - the bank displaying name of the user
	 */
	public void setBkDisName(String bkDisName) {
		this.bkDisName = bkDisName;
	}
	/**
	 * getUserid method 
	 * 
	 * @return userid - the id of the user
	 */
	public String getUserid() {
		return userid;
		}
	/**
	 * setUserid method which sets the id of the user
	 * 
	 * @param userid - the id of the user
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * toString method which return user name and the amount and date of 
	 * the transaction
	 * 
	 * @return user name and the amount and date of the transaction 
	 */
	@Override
	public String toString() {
		return this.bkDisName + " : " + this.name
				+ " : $" + this.amount + " ( " + this.date + ")";
	}
	/**
	 * describeContents method which returns content
	 * 
	 * @return 0
	 */
	@Override
	public int describeContents() {
		return 0;
	}
	/**
	 * Constructor for Transaction
	 * 
	 * @param in 
	 */
	public Transaction(Parcel in) {
		name = in.readString();
		type = in.readString();
		amount = in.readDouble();
		date = (myDate) in.readValue(myDate.class.getClassLoader());
		status = in.readString();
		recordTime = in.readString();
		bkDisName = in.readString();
	}
	/**
	 * writeToParcel method which writes the information of
	 * the user to parcel
	 * 
	 * @param dest
	 * @param flag
	 */
	@Override
	public void writeToParcel(Parcel dest, int flag) {
		Log.i(MainActivity.LOGTAG, "writeToParcel");
		dest.writeString(name);
		dest.writeString(type);
		dest.writeDouble(amount);
		dest.writeValue(date);
		dest.writeString(status);
		dest.writeString(recordTime);
		dest.writeString(bkDisName);

	}

	public static final Parcelable.Creator<Transaction> CREATOR = new Parcelable.Creator<Transaction>() {

		@Override
		public Transaction createFromParcel(Parcel arg0) {
			Log.i(MainActivity.LOGTAG, "createFromParcel");
			return new Transaction(arg0);
		}

		@Override
		public Transaction[] newArray(int arg0) {
			Log.i(MainActivity.LOGTAG, "newArray");
			return new Transaction[arg0];
		}

	};

}
