package edu.ncsu.csc316.security_manager.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.security_manager.date.Date;
import edu.ncsu.csc316.security_manager.list.ArrayList;
import edu.ncsu.csc316.security_manager.log.LogEntry;

/**
 * Reader of a correctly formatted log file
 * @author Dan Grochmal djgrochm
 *
 */
public class LogFileReader {

	/** A Queue of LogEntry's to be formed by the reader */
    //public static Queue<LogEntry> q = new Queue<LogEntry>(100);
    public ArrayList<LogEntry> q = new ArrayList<LogEntry>();
	
	/**
	 * Reads a file and fills the queue with LogEntry's
	 * @param filename File containing Log's
	 */
	public LogFileReader(String filename) {
		try(Scanner scan = new Scanner(new FileInputStream(filename), "UTF8"))
		{
			scan.useDelimiter("[,\n\r]");
		    while(scan.hasNextLine())
		    {
		        String date = scan.next().trim();
		        Date d = new Date(date);
		        String user = scan.next().trim();
		        String action = scan.next().trim();
		        LogEntry l = new LogEntry(d, user, action);
		        q.add(q.size(), l);
		        //q.enqueue(l);
		        if (scan.hasNextLine()){
                    scan.nextLine();
                }
		        
		    }
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}
	
	/**
	 * Gets the Queue containing the read in log entries
	 * @return The Queue containing the read in log entries
	 */
	public ArrayList<LogEntry> getQueue(){
		return q;
	}

	
}