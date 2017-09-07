package edu.ncsu.csc316.security_manager.tree;

import edu.ncsu.csc316.security_manager.attack.AttackStep;
import edu.ncsu.csc316.security_manager.list.ArrayList;

/**
 * A linked general tree of generic objects
 * @author Dan Grochmal djgrochm
 * @param <E> Generic object contained in this LinkedTree
 */
public class LinkedTree<E> {

	/** Root of the LinkedTree */
	public TreeNode root;
	
	/**
	 * Constructor of LinkedTree, with a preorder and postorder traversal given
	 * @param pre Preorder traversal to be built into a tree
	 * @param post Postorder traversal to be built into a tree
	 */
	public LinkedTree(ArrayList<E> pre, ArrayList<E> post) {
		this.root = buildAttackTree(null, pre, post);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Builds an LinkedTree given a root, preorder, and postorder
	 * @param root the root of a tree to be built
	 * @param pre Preorder traversal representation of a list
	 * @param post Postorfer traversal representation of a list
	 * @return A TreeNode containing the root of the tree
	 */
	public TreeNode buildAttackTree(TreeNode root, ArrayList<E> pre, ArrayList<E> post) {
		if(root == null){
			TreeNode n = new TreeNode(null, pre.remove(0));
			this.root = n;
			post.remove(post.size() - 1);
			root = buildAttackTree(this.root, pre, post);
		} else if(theSame(pre.get(0), post.get(0))){
			boolean cont = true;
			while(pre.size() != 0 && cont){
				if(theSame(pre.get(0), post.get(0))){
					TreeNode node = new TreeNode(root, pre.remove(0));
					post.remove(0);
					root.addChild(node);
				} else {
					cont = false;
				}
			}
			if(!pre.isEmpty() && !post.isEmpty()){
				buildAttackTree(root, pre, post);
			}
		} else {
			ArrayList<E> newPostList = new ArrayList<E>();
			ArrayList<E> newPreList = new ArrayList<E>();
			int count = 0;
			E temp = pre.remove(0);
			TreeNode c = new TreeNode(root, temp);
			root.addChild(c);
			
			while(!theSame(post.get(0), temp)){
				count++;
				newPostList.add(newPostList.size(), post.remove(0));
			}
			post.remove(0);
			for(int i = 0; i < count; i++){
				newPreList.add(newPreList.size(), pre.remove(0));
			}
			buildAttackTree(c, newPreList, newPostList);
			if(!pre.isEmpty() && !post.isEmpty()){
				buildAttackTree(root, pre, post);
			}
		}
		return root; 
	}
	
	/**
	 * Discerns if two objects are the same or not
	 * @param pre First object to be compared
	 * @param post Second object to be compared
	 * @return True if the objects are the same, false otherwise
	 */
	private boolean theSame(E pre, E post) {
		return ((AttackStep) pre).getDesc().equals(((AttackStep) post).getDesc());
	}

	
	/**
	 * TreeNode to be used in LinkedTree
	 * @author Dan Grochmal djgrochm
	 */
	public class TreeNode {

		/** An ArrayList of TreeNodes that represents the children of a node */
		public ArrayList<TreeNode> children = new ArrayList<TreeNode>();
		/** TreeNode representing the parent of the given node */
		public TreeNode parent;
		/** The data that is included within a node */
		public E data;
		
		/**
		 * Constructor of a TreeNode
		 * @param parent Parent of the node being created
		 * @param data Data contained in the node being created
		 */
		public TreeNode(TreeNode parent, E data) {
			this.parent = parent;
			this.data = data;
		}
		
		/**
		 * Adds a child to a nodes children list
		 * @param node Node to be added to children
		 */
		public void addChild(TreeNode node){
			this.children.add(children.size(), node);
		}
		
		/**
		 * Discerns if a node has children or not
		 * @return True if the node has children, false otherwise
		 */
		public boolean hasChildren(){
			return children.size() != 0;
		}
		
		/**
		 * Gets the data contained in a node as an AttackStep
		 * @return An AttackStep contained in a node
		 */
		public AttackStep getData(){
			return (AttackStep) data;
		}
		
//		/**
//		 * Converts a TreeNode to a String
//		 * @return A TreeNode as a String of a AttackStep
//		 */
//		public String toString(){
//			return this.getData().toString();
//		}

	}
}