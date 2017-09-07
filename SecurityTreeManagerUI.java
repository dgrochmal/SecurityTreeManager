/**
 * 
 */
package edu.ncsu.csc316.security_manager.ui;

import java.util.InputMismatchException;
import java.util.Scanner;

import edu.ncsu.csc316.security_manager.manager.SecurityTreeManager;

/**
 * User interface of the system that includes the main method
 * @author Dan Grochmal djgrochm
 *
 */
public class SecurityTreeManagerUI {

	/**
	 * Main method for the system
	 * @param args Command Line arguments
	 */
	public static void main(String args[]) {
		boolean which = false;
		SecurityTreeManager manager = null;
		if(args.length == 1){
			which = true;
		} else if (args.length == 2){
			which = false;
		} else {
			System.out.println("Invalid number of parameters");
			System.exit(1);
		}
		if(!which){
			manager = new SecurityTreeManager(args[0], args[1]);
		}
		if(which){
			 manager = new SecurityTreeManager(args[0]);
		}
		int option = 0;
		System.out.println("1. Retrieve Attack Goal Summary");
		System.out.println("2. Retrieve Log Entries for a Given Day");
		System.out.println("3. Generate Level-order Traversal");
		System.out.println("4. Quit");
		System.out.print("Enter choice: ");
		Scanner reader = new Scanner(System.in);
		option = reader.nextInt();
		reader.nextLine();

		if(option == 1) {
			
			System.out.println(manager.propagateValues());
		} else if(option == 2) {
			System.out.println("Enter a date (MM-DD-YYYY): ");
			try{
				String date = reader.next();
				//manager = new SecurityTreeManager(args[0]);
				System.out.println(manager.getLogEntriesForDate(date));
			} catch (InputMismatchException e){
				System.out.println("Enter a valid date in the format MM-DD-YYYY");
			}
		} else if(option == 3){
			if (!which) {
				System.out.println(manager.getAttackTreeLevelOrder());
				
			} else if (which) {
				System.out.println(manager.getLogTreeLevelOrder());
				
			}
		} else if(option == 4){
			System.exit(0);
		}

		reader.close();
	}

}