/**
 * 
 */
package edu.ncsu.csc316.security_manager.manager;

import edu.ncsu.csc316.security_manager.attack.AttackStep;
import edu.ncsu.csc316.security_manager.io.AttackFileReader;
import edu.ncsu.csc316.security_manager.io.LogFileReader;
import edu.ncsu.csc316.security_manager.list.ArrayList;
import edu.ncsu.csc316.security_manager.list.LinkedQueue;
import edu.ncsu.csc316.security_manager.log.LogEntry;
import edu.ncsu.csc316.security_manager.tree.LinkedTree;
import edu.ncsu.csc316.security_manager.tree.LinkedTree.TreeNode;
import edu.ncsu.csc316.security_manager.tree.LogTree;

/**
 * Manager class of the Security system
 * @author Dan Grochmal djgrochm
 *
 */
public class SecurityTreeManager {

	/** Tree of AttackSteps */
	public LinkedTree<AttackStep> attackTree;
	/** Tree of LogEntries */
	public LogTree logTree;
	String levelOrder = "";
	String logDates = "";
	LogEntry[] sortedLogs;
	int count = 0;

	/**
     * Constructs a new SecurityTreeManager object with the given paths 
     * to the preOrder and postOrder traversal files.
     * @param preOrder - the path to the preOrder traversal file
     * @param postOrder - the path to the postOrder traversal file
     */
    public SecurityTreeManager(String preOrder, String postOrder)
    {
        AttackFileReader afr = new AttackFileReader(preOrder, postOrder);
        attackTree = new LinkedTree<AttackStep>(afr.pre, afr.post);
    }
    
    /**
     * Constructs a new SecurityTreeManager object with the given path
     * to the file that contains the log entries.
     * @param filePath - the path to the log entry file
     */
    public SecurityTreeManager(String filePath)
    {
    	LogFileReader lfr = new LogFileReader(filePath);
        ArrayList<LogEntry> queue = lfr.getQueue();
        logTree = new LogTree(queue);
        sortedLogs = new LogEntry[logTree.tree.length];
    }

    /**
     * Returns the level order traversal of the Attack Tree
     * as a string in the format (where each "node" is indented 3 spaces):
     * 
     * LevelOrder[
     *    GOAL Step[Use DDoS Attack to Disrupt All Users, C=0.00, P=0.000, I=0.00]
     *    OR Step[Attack Servers, C=0.00, P=0.000, I=0.00]
     *    OR Step[Attack Comm Infrastructure, C=0.00, P=0.000, I=0.00]
     * ]
     *
     * THE LEVEL ORDER TRAVERSAL MUST NOT RETURN ANY OF THE PROPOGATED VALUES!
     * 
     * @return the level order traversal (as a string) of the attack tree
     */
    @SuppressWarnings("rawtypes")
	public String getAttackTreeLevelOrder()
    {
    	LinkedQueue<TreeNode> queue = new LinkedQueue<TreeNode>(); 
        TreeNode current = this.attackTree.root;
        StringBuilder s = new StringBuilder();
        s.append("LevelOrder[\n");
        queue.enqueue(current); 

        while(!queue.isEmpty())
        {
            current = queue.dequeue();
            s.append("   ").append(current.getData().toString()).append("\n");
            if (current.children != null)
            {
            	for(int i = 0; i < current.children.size(); i++){
            		queue.enqueue((TreeNode) current.children.get(i));
            	}
                
      
            }
        }
        s.append("]");
        return s.toString();
    }
    
    
    /**
     * Returns the level order traversal of the Log Tree
     * as a string in the format (where each "node" is indented 3 spaces)
     *
     * THE LEVEL ORDER TRAVERSAL MUST NOT RETURN ANY OF THE PROPOGATED VALUES!
     * 
     * @return the level order traversal (as a string) of the attack tree
     */
    public String getLogTreeLevelOrder() {
    	StringBuilder sb = new StringBuilder();
    	sb.append("LevelOrder[\n");
    	//String lOrder = "LevelOrder[\n";
		int size = logTree.tree.length;
		for(int i = 0; i < size; i++){
			if(logTree.tree[i] != null){
				sb.append("   ").append(logTree.tree[i]).append("\n");
				//lOrder += "   " + logTree.tree[i] + "\n";
			}
		}
		sb.append("]");
		//lOrder += "]";
		return sb.toString();
	}
    
    /**
     * Returns the values (as a string) propagated to the root node
     * using the formulas from the project writeup.
     * For example:
     * GOAL Step[Use DDoS Attack to Disrupt All Users, C=21557.12, P=0.878, I=8.00]
     * 
     * @return the metric values (as a string) that are propagated to the root node
     */
    @SuppressWarnings({ })
	public String propagateValues()
    {
    	propagate(this.attackTree.root);
    	String cost = String.format("%.2f", this.attackTree.root.getData().getCost());
    	String impact = String.format("%.2f", this.attackTree.root.getData().getImpact());
    	String prob = String.format("%.3f", this.attackTree.root.getData().getProbability());
    	StringBuilder s = new StringBuilder();
    	return s.append("GOAL Step[").append(this.attackTree.root.getData().getDesc()).append(", C=").append(cost).append(", P=").append(prob).append(", I=").append(impact).append("]").toString();
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	private void propagate(TreeNode root){
		if(root.hasChildren()){
			for(int i = 0; i < root.children.size(); i++){
				propagate((TreeNode) root.children.get(i));
			}
		}
		if(root != this.attackTree.root){
			ArrayList<TreeNode> list = root.parent.children;
			if(root.getData().andOr){
				root.parent.getData().setProbability(andProbability(list));
				root.parent.getData().setCost(andCost(list));
				root.parent.getData().setImpact(andImpact(list));
			} else {
				root.parent.getData().setProbability(orProbability(list));
				root.parent.getData().setCost(orCost(list));
				root.parent.getData().setImpact(orImpact(list));
			}

		}
		
			
		
    }
    
    @SuppressWarnings("rawtypes")
	private double andProbability(ArrayList<TreeNode> list){
    	double p = 0.0;
    	for(int i = 0; i < list.size(); i++){
    		if(i == 0){
    			p = list.get(i).getData().getProbability();
    		} else {
    			p *= list.get(i).getData().getProbability();
    		}
    		
    	}
    	return p;
    }
    
    @SuppressWarnings("rawtypes")
	private double andCost(ArrayList<TreeNode> list){
    	double c = 0.0;
    	for(int i = 0; i < list.size(); i++){
    		c += list.get(i).getData().getCost();
    	}
    	return c;
    }
    
    @SuppressWarnings("rawtypes")
    private double andImpact(ArrayList<TreeNode> list){
    	double i = 0.0;
    	int p = pow(10, list.size());
		int q = pow(10, list.size() - 1);
    	for(int j = 0; j < list.size(); j++){
    		if(i == 0){
    			i = (10 - list.get(j).getData().getImpact());
    		} else {
    			i *= (10 - list.get(j).getData().getImpact());
    		}
    		
    	}
    	i = (p - i) / q;
    	return i;
    }
    
    @SuppressWarnings("rawtypes")
    private double orProbability(ArrayList<TreeNode> list ){
    	double p = 0.0;
    	for(int i = 0; i < list.size(); i++){
    		if(i == 0){
    			p = 1 - list.get(i).getData().getProbability();
    		} else {
    			p *= (1 - list.get(i).getData().getProbability());
    		}
    		
    	}
    	return 1 - p;
    }
    
    @SuppressWarnings("rawtypes")
    private double orCost(ArrayList<TreeNode> list){
    	double c = 0.0;
    	double d = 0.0;
    	for(int i = 0; i < list.size(); i++){
    		c += list.get(i).getData().getCost() * list.get(i).getData().getProbability();
    		d += list.get(i).getData().getProbability();
    	}
    	return c / d;
    }
    
    @SuppressWarnings("rawtypes")
    private double orImpact(ArrayList<TreeNode> list){
    	double i = 0.0;
    	double k = 0.0;
    	for(int j = 0; j < list.size(); j++){
    		k = list.get(j).getData().getImpact();
    		if ( k > i ){
    			i = k;
    		}
    	}
    	return i;
    }
    
    private int pow(int base, int power){
        if(power == 0) return 1;
        return base * pow(base, --power);
    }
    
    /**
     * Returns (as a string, sorted in increasing order) the log entries 
     * for the given date in the format:
     * 
     * LogEntry[timestamp=2015/07/17 15:49:38, user=user4, description=print calendar]
     * LogEntry[timestamp=2015/07/17 15:55:25, user=user8, description=save immunizations]
     * 
     * @param date - the date, in the format MM-DD-YYYY
     * @return the string representation of the log entries for the specified date
     */
    public String getLogEntriesForDate(String date)
    {
    	StringBuilder sb = new StringBuilder();
    	printSorted(logTree.tree, 0);
    	for(int i = 0; i < sortedLogs.length; i++){
    		if(sortedLogs[i] != null && sortedLogs[i].getDate().getMini().equals(date)){
    			sb.append(sortedLogs[i].toString()).append("\n");
    		}
    	}
        return sb.toString();
    }
    
    private void printSorted(LogEntry arr[], int root){
    	if(arr[(2 * root) + 1] != null){
    		printSorted(arr, (2 * root) + 1);
    	}
    	if(arr[root] != null){
    		sortedLogs[count] = arr[root];
    		count++;
    	}
    	if(arr[(2 * root) + 2] != null){
    		printSorted(arr, (2 * root) + 2);
    	}
    }
    	
    
}