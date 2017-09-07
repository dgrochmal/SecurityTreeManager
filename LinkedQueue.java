/**
 * 
 */
package edu.ncsu.csc316.security_manager.list;

/**
 * Custom Linked List Queue
 * @author Daniel Grochmal
 *
 * @param <E> a generic type for use in the Queue
 */
public class LinkedQueue<E>  {

	Node front;
	Node back;
	int size;
	/**
	 * Queue constructor of type linked list
	 */
	public LinkedQueue() {
		front = null;
		back = null;
		size = 0;
	}

	/**
	 * Adds element to the queue
	 * @param element to be added to the queue
	 */
	public void enqueue(E element) {
		Node n = new Node(element);
		if(size == 0){
			front = n;
			back = n;
			size++;
		} else {
			back.next = n;
			back = back.next;
			size++;
		}	
	}

	/**
	 * Takes an element from the queue
	 * @return the element removed from the queue
	 */
	public E dequeue() {
		Node temp = front;
		front = front.next;
		size--;
		return temp.data; 
	}

	/**
	 * Discerns if the queue is empty
	 * @return true if the queue is empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * Returns the size of the list
	 * @return size of the list
	 */
	public int size() {
		return size;
	}

	/**
	 * Node to be used in a Queue
	 * @author Dan Grochmal djgrochm
	 *
	 */
	public class Node {

		/** The data that is included within a node */
		public E data;
		/** A nodes next node */
		public Node next;
		
		/**
		 * Constructor of a TreeNode
		 * @param data Data contained in the node being created
		 */
		public Node(E data) {
			this.data = data;
			this.next = null;
		}


	}

}