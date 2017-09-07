package edu.ncsu.csc316.security_manager.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.ncsu.csc316.security_manager.attack.AttackStep;
import edu.ncsu.csc316.security_manager.list.ArrayList;

/**
 * Reader of Attack File
 * @author Dan Grochmal djgrochm
 *
 */
public class AttackFileReader {

	/** List containing the preorder traversal of AttackSteps */
	public ArrayList<AttackStep> pre = new ArrayList<AttackStep>();
	/** List containing the postorder traversal of AttackSteps */
	public ArrayList<AttackStep> post = new ArrayList<AttackStep>();
	
	/**
	 * Reads in files and gets AttackSteps
	 * 
	 * @param preFilename Name of the file containing the preorder traversal
	 * @param postFilename Name of the file containing the postorder traversal
	 */
	public AttackFileReader(String preFilename, String postFilename) {
		try(Scanner scanPre = new Scanner(new FileInputStream(preFilename), "UTF8"))
		{
			int size = 0;
			scanPre.next();
	    	String desc = scanPre.nextLine().trim();
	    	AttackStep goal = new AttackStep(desc);
	    	pre.add(size, goal);
	    	size++;
		    while(scanPre.hasNextLine())
		    {
		    	String let = scanPre.next();
		    	if(let.equals("A")){
		    		if(scanPre.hasNextDouble()){
		    			double p = scanPre.nextDouble();
		    			double i = scanPre.nextDouble();
		    			double c = scanPre.nextDouble();
		    			desc = scanPre.nextLine().trim();
		    			goal = new AttackStep(true, desc, i, c, p);
		    			pre.add(size, goal);
		    	    	size++;
		    		} else {
		    			desc = scanPre.nextLine().trim();
		    			goal = new AttackStep(true, desc);
		    			pre.add(size, goal);
		    	    	size++;
		    		}
		    	} else if(let.equals("O")){
		    		if(scanPre.hasNextDouble()){
		    			double p = scanPre.nextDouble();
		    			double i = scanPre.nextDouble();
		    			double c = scanPre.nextDouble();
		    			desc = scanPre.nextLine().trim();
		    			goal = new AttackStep(false, desc, i, c, p);
		    			pre.add(size, goal);
		    	    	size++;
		    		} else {
		    			desc = scanPre.nextLine().trim();
		    			goal = new AttackStep(false, desc);
		    			pre.add(size, goal);
		    	    	size++;
		    		}
		    	}
		    }
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
		
		try(Scanner scanPost = new Scanner(new FileInputStream(postFilename), "UTF8"))
		{
			int size = 0;
	    	String desc;
	    	AttackStep goal;
		    while(scanPost.hasNextLine())
		    {
		    	String let = scanPost.next();
		    	if(let.equals("GOAL")){
		    		desc = scanPost.nextLine().trim();
			    	goal = new AttackStep(desc);
			    	post.add(size, goal);
			    	size++;
		    	}
		    	if(let.equals("A")){
		    		if(scanPost.hasNextDouble()){
		    			double p = scanPost.nextDouble();
		    			double i = scanPost.nextDouble();
		    			double c = scanPost.nextDouble();
		    			desc = scanPost.nextLine().trim();
		    			goal = new AttackStep(true, desc, i, c, p);
		    			post.add(size, goal);
		    	    	size++;
		    		} else {
		    			desc = scanPost.nextLine().trim();
		    			goal = new AttackStep(true, desc);
		    			post.add(size, goal);
		    	    	size++;
		    		}
		    	} else if(let.equals("O")){
		    		if(scanPost.hasNextDouble()){
		    			double p = scanPost.nextDouble();
		    			double i = scanPost.nextDouble();
		    			double c = scanPost.nextDouble();
		    			desc = scanPost.nextLine().trim();
		    			goal = new AttackStep(false, desc, i, c, p);
		    			post.add(size, goal);
		    	    	size++;
		    		} else {
		    			desc = scanPost.nextLine().trim();
		    			goal = new AttackStep(false, desc);
		    			post.add(size, goal);
		    	    	size++;
		    		}
		    	}
		    }
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		}
	}

}