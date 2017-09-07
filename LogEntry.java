package edu.ncsu.csc316.security_manager.log;

import edu.ncsu.csc316.security_manager.date.Date;

/**
 * Object that holds all fields of a log from the file
 * 
 * @author Dan Grochmal djgrochm
 *
 */
public class LogEntry {

	/** Field to hold the date of a Log */
	public Date date;
	/** Field to hold the user of a Log */
	public String user;
	/** Field to hold the action of a Log */
	public String action;
	
	/**
	 * Constructor of a LogEntry object
	 * 
	 * @param date of a LogEntry to be constructed
	 * @param user of a LogEntry to be constructed
	 * @param action of a LogEntry to be constructed
	 */
	public LogEntry(Date date, String user, String action) {
		this.date = date;
		this.user = user;
		this.action = action;
	}

	/**
	 * Gets the Date of a LogEntry
	 * @return the date of a LogEntry
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Gets the user of a LogEntry
	 * @return the user of a LogEntry
	 */
	public String getUser() {
		return user;
	}

	/**
	 * Gets the action of a LogEntry
	 * @return the action
	 */
	public String getAction() {
		return action;
	}

	/**
	 * Prints a LogEntry as a string
	 * @return LogEntry as a string
	 */
	@Override
	public String toString() {
		String year = Integer.toString(date.getYear());
		String month = Integer.toString(date.getMonth());
		String day = Integer.toString(date.getDay());
		String hour = Integer.toString(date.getHr());
		String min = Integer.toString(date.getMin());
		String sec = Integer.toString(date.getSec());
		if(month.length() != 2){
			month = "0" + month;
		}
		if(day.length() != 2){
			day = "0" + day;
		}
		if(hour.length() != 2){
			hour = "0" + hour;
		}
		if(min.length() != 2){
			min = "0" + min;
		}
		if(sec.length() != 2){
			sec = "0" + sec;
		}
		return "LogEntry[timestamp=" + year + "/" + month + "/" + day + " " + hour + ":" + min + ":" + sec + ", user=" + user + ", description=" + action + "]";
	}

}