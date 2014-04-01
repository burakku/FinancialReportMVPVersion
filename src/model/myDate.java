package model;

import fiveminions.financialreportmvpversion.MainActivity;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * This class mainly contains the methods to get and 
 * set the date of the transaction and it restricts 
 * format of the date to be returned.
 * 
 * @version 1.0
 * 
 * @author Team 23
 */
public class myDate implements Parcelable{
	private int year;
	private int month;
	private int day;
	private String rawDate;
	/**
	 * Default constructor for myDate
	 *
	 */
	public myDate() {
		this.year = 0;
		this.month = 0;
		this.day = 0;
		this.rawDate= "";
	}
	/**
	 * Constructor for myDate
	 *
	 * @param date the date 
	 */
	public myDate(String date) {
		this.rawDate = date;
		String[] a = date.split("-");
		if(a.length == 3){
		this.year = Integer.parseInt(a[0]);
		this.month =Integer.parseInt(a[1]);
		this.day = Integer.parseInt(a[2]);
		} else {
			Log.i(MainActivity.LOGTAG, "Date string format is not valid. " );
		}
	}
	/**
	 * formatMonth method that return the month in format
	 * 
	 * @param m month
	 * @return month - the month in format
	 */
	public String formatMonth(int m){
		String result = "";
		switch (m){
		case 1:
			result = "January";
			break;
		case 2:
			result = "February";
			break;	
		case 3:
			result = "March";
			break;
		case 4:
			result = "April";
			break;
		case 5:
			result = "May";
			break;
		case 6:
			result = "June";
			break;
		case 7:
			result = "July";
			break;
		case 8:
			result = "Aguest";
			break;
		case 9:
			result = "Spetember";
			break;
		case 10:
			result = "October";
			break;
		case 11:
			result = "November";
			break;
		case 12:
			result = "December";
			break;
		default:
			break;
		}
		return result;
	}
	/**
	 * getYear method
	 * 
	 * @return name - the year 
	 */
	public int getYear() {
		return year;
	}
	/**
	 * setYear method which sets the year
	 * 
	 * @param year the year 
	 */
	public void setYear(int year) {
		this.year = year;
	}
	/**
	 * getMonth method
	 * 
	 * @return month - the month 
	 */
	public int getMonth() {
		return month;
	}
	/**
	 * getFormatMonth method
	 * 
	 * @return formatMonth - the month in format 
	 */
	public String getFormatMonth(){
		return formatMonth(this.month);
	}
	/**
	 * setMonth method which sets the month
	 * 
	 * @param month - the month
	 */
	public void setMonth(int month) {
		this.month = month;
	}
	/**
	 * getDay method
	 * 
	 * @return day - the day
	 */
	public int getDay() {
		return day;
	}
	/**
	 * setDay method which sets the day
	 * 
	 * @param day - the day
	 */
	public void setDay(int day) {
		this.day = day;
	}
	/**
	 * getRawDate method 
	 * 
	 * @param rawDate - the original day
	 */
	public String getRawDate(){
		return rawDate;
	}
	
	@Override
	public String toString() {
		return formatMonth(month) + " " + day + ", " + year;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
	 * Constructor for my date
	 * 
	 * @param in
	 */
	public myDate(Parcel in){
		year= in.readInt();
		month = in.readInt();
		day = in.readInt();
	}
	@Override
	public void writeToParcel(Parcel dest, int flag) {
		dest.writeInt(year);
		dest.writeInt(month);
		dest.writeInt(day);
		}
	
	public static final Parcelable.Creator<myDate> CREATOR = new Parcelable.Creator<myDate>() {

		@Override
		public myDate createFromParcel(Parcel arg0) {
			Log.i(MainActivity.LOGTAG, "createFromParcel");
			return new myDate(arg0);
		}

		@Override
		public myDate[] newArray(int arg0) {
			Log.i(MainActivity.LOGTAG, "newArray");
			return new myDate[arg0];
		}

	};
}
