package model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import fiveminions.financialreportmvpversion.MainActivity;

/**
 * This class mainly contains the methods to get and 
 * set the date of the transaction and it restricts 
 * format of the date to be returned.
 * @version 1.0
 * @author Team 23
 */
public class MyDate implements Parcelable { // NOPMD by wen on 4/2/14 1:20 AM
    private int year;
    private int month;
    private int day;
    private String rawDate; // NOPMD by wen on 4/2/14 1:20 AM
    
    /**
     * parcelable creator.
     */
    public static final Parcelable.Creator<MyDate> CREATOR = new Parcelable.Creator<MyDate>() {

        @Override
        public MyDate createFromParcel(final Parcel arg0) {
            Log.i(MainActivity.LOGTAG, "createFromParcel");
            return new MyDate(arg0);
        }

        @Override
        public MyDate[] newArray(final int arg0) {
            Log.i(MainActivity.LOGTAG, "newArray");
            return new MyDate[arg0];
        }

    };
    /**
     * Default constructor for myDate.
     *
     */
    public MyDate() {
        this.year = 0;
        this.month = 0;
        this.day = 0;
        this.rawDate = "";
    }
    /**
     * Constructor for myDate.
     *
     * @param date the date 
     */
    public MyDate(final String date) {
        this.rawDate = date;
        final String[] string = date.split("-");
        if (string.length == 3) {
            this.year = Integer.parseInt(string[0]);
            this.month = Integer.parseInt(string[1]);
            this.day = Integer.parseInt(string[2]);
        } else {
            Log.i(MainActivity.LOGTAG, "Date string format is not valid. " );
        }
    }
    /**
     * formatMonth method that return the month in format.
     * 
     * @param month month
     * @return month - the month in format
     */
    public String formatMonth(final int month) { // NOPMD by wen on 4/2/14 1:20 AM
        String result = "";
        switch (month) {
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
     * getYear method.
     * 
     * @return name - the year 
     */
    public int getYear() {
        return year;
    }
    /**
     * setYear method which sets the year.
     * 
     * @param year the year 
     */
    public void setYear(final int year) {
        this.year = year;
    }
    /**
     * getMonth method.
     * 
     * @return month - the month 
     */
    public int getMonth() {
        return month;
    }
    /**
     * getFormatMonth method.
     * 
     * @return formatMonth - the month in format 
     */
    public String getFormatMonth() {
        return formatMonth(this.month); // NOPMD by wen on 4/2/14 1:21 AM
    }
    /**
     * setMonth method which sets the month.
     * 
     * @param month - the month
     */
    public void setMonth(final int month) {
        this.month = month;
    }
    /**
     * getDay method.
     * 
     * @return day - the day
     */
    public int getDay() {
        return day;
    }
    /**
     * setDay method which sets the day.
     * 
     * @param day - the day
     */
    public void setDay(final int day) {
        this.day = day;
    }
    /**
     * getRawDate method .
     * @return date in yyyy-MM-dd format
     */
    public String getRawDate() {
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
     * Constructor for my date.
     * 
     * @param inparcel parcel
     */
    public MyDate(final Parcel inparcel) {
        year = inparcel.readInt();
        month = inparcel.readInt();
        day = inparcel.readInt();
    }
    @Override
    public void writeToParcel(final Parcel dest, final int flag) {
        dest.writeInt(year);
        dest.writeInt(month);
        dest.writeInt(day);
    }
    

}
