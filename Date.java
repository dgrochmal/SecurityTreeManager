/**
 * 
 */
package edu.ncsu.csc316.security_manager.date;

import java.util.Scanner;

/**
 * Custom Date object
 * 
 * @author Dan Grochmal djgrochm
 */
public class Date {

	/** Field that holds the year of a Date */
	public int year;
	/** Field that holds the month of a Date */
	public int month;
	/** Field that holds the day of a Date */
	public int day;
	/** Field that holds the hour of a Date */
	public int hr;
	/** Field that holds the minute of a Date */
	public int min;
	/** Field that holds the second of a Date */
	public int sec;
	
	/**
	 * Constructs a date object given just a String
	 * @param date String containing a Date
	 */
	public Date(String date) {
		Scanner scan = new Scanner(date);
		scan.useDelimiter("-|:| ");
		this.month = Integer.parseInt(scan.next());
		this.day = Integer.parseInt(scan.next());
		this.year = Integer.parseInt(scan.next());
		this.hr = Integer.parseInt(scan.next());
		this.min = Integer.parseInt(scan.next());
		this.sec = Integer.parseInt(scan.next());
		scan.close();
	}

	/**
	 * Constructs a Date given six values
	 * @param i Year value of Date
	 * @param j Month value of a Date
	 * @param k Day value of a Date
	 * @param l Hour value of a Date
	 * @param m Minute value of a Date
	 * @param n Second value of a Date
	 */
	public Date(int i, int j, int k, int l, int m, int n) {
		this.month = j;
		this.day = k;
		this.year = i;
		this.hr = l;
		this.min = m;
		this.sec = n;
	}

	/**
	 * Returns true if the first date given is the latest
	 * 
	 * @param d1 First Date to be compared
	 * @param d2 Second Date to be compared
	 * @return True if the first date is the latest, false otherwise
	 */
	public static boolean isLatest(Date d1, Date d2){
		if(d1.getYear() != d2.getYear()){
			return d1.getYear() > d2.getYear();
		} else if (d1.getMonth() != d2.getMonth()){
			return d1.getMonth() > d2.getMonth();
		} else if (d1.getDay() != d2.getDay()){
			return d1.getDay() > d2.getDay();
		} else if (d1.getHr() != d2.getHr()){
			return d1.getHr() > d2.getHr();
		} else if (d1.getMin() != d2.getMin()){
			return d1.getMin() > d2.getMin();
		} else if (d1.getSec() != d2.getSec()){
			return d1.getSec() > d2.getSec();
		}
		return false;
	}
	
	/**
	 * Converts a Date to a String
	 * @return A string representing a Date
	 */
	@Override
	public String toString() {
		return month + "/" + day + "/" + year + " " + hr + ":" + min + ":" + sec;
	}

	/**
	 * Gets a date's year
	 * @return the year of a Date
	 */
	public int getYear() {
		return year;
	}

	/**
	 * Gets a date's month
	 * @return the month of a Date
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * Gets a date's day
	 * @return the day of a Date
	 */
	public int getDay() {
		return day;
	}

	/**
	 * Gets a date's hour
	 * @return the hour of a Date
	 */
	public int getHr() {
		return hr;
	}

	/**
	 * Gets a date's minute
	 * @return the minute of a Date
	 */
	public int getMin() {
		return min;
	}

	/**
	 * Gets a date's seconds
	 * @return the seconds of a Date
	 */
	public int getSec() {
		return sec;
	}
	
	/**
	 * Gets a miniature representation of a Date as a String
	 * @return a miniature representation of a Date as a String
	 */
	public String getMini(){
		String yearM = Integer.toString(this.getYear());
		String monthM = Integer.toString(this.getMonth());
		String dayM = Integer.toString(this.getDay());
		if(monthM.length() != 2){
			monthM = "0" + monthM;
		}
		if(dayM.length() != 2){
			dayM = "0" + dayM;
		}
		return monthM + "-" + dayM + "-" + yearM;
	}

}