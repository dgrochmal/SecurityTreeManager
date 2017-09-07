package edu.ncsu.csc316.security_manager.tree;

import edu.ncsu.csc316.security_manager.date.Date;
import edu.ncsu.csc316.security_manager.list.ArrayList;
import edu.ncsu.csc316.security_manager.log.LogEntry;

/**
 * A binary search tree formed as an Array
 * 
 * @author Dan Grochmal djgrochm
 *
 */
public class LogTree {

	/** Array of LogEntries that will contain a tree */
	public LogEntry[] tree;
	/** Queue of LogEntries to be put into a tree */
	ArrayList<LogEntry> q;
	
	/**
	 * Constructor of LogTree
	 * @param length Length of array to be created
	 */
	public LogTree(int length) {
		//Capacity should be (2^Height)-1
		tree = new LogEntry[length * 2];
	}
	
	/**
	 * Constructor given a Queue
	 * @param q Queue containing LogEntries to be put into a tree
	 */
	public LogTree(ArrayList<LogEntry> q) {
		this.q = q;
		//Capacity should be (2^Height)-1
		tree = new LogEntry[q.size() + 3500];
		buildTree();
	}
	
	/**
	 * Adds a LogEntry to the Tree in the correct position
	 * @param obj LogEntry to be added to the list
	 */
	public void add(LogEntry obj){
		if(this.tree[0] == null){
			this.tree[0] = obj;
		} else {
			int pos = getPos(0, obj);
			this.tree[pos] = obj;
		}
	}
	
	/**
	 * Gets the position of where a LogEntry is to be added
	 * @param start Starting point of where to begin a search
	 * @param obj LogEntry to be added
	 * @return An int representing the position of where a LogEntry is to be added
	 */
	private int getPos(int start, LogEntry obj){
		int i = start;
		if(tree[i * 2 + 1] == null && Date.isLatest(tree[i].getDate(), obj.getDate())){
			
			return i * 2 + 1;
		} else if (tree[(i * 2) + 2] == null && Date.isLatest(obj.getDate(), tree[i].getDate())){
			return (i * 2) + 2;
		} else if (Date.isLatest(tree[i].getDate(), obj.getDate())){
			return getPos(i * 2 + 1, obj);
		} else {
			return getPos((i * 2) + 2, obj);
		}
	}
	
	/**
	 * Builds the LogTree
	 */
	public void buildTree(){
		for(int i = 0; i < q.size(); i++){
			this.add(q.get(i));
		}
//		while(!q.isEmpty()){
//			this.add(q.dequeue());
//		}
	}

}