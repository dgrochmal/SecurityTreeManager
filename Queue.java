/**
 * 
 */
package edu.ncsu.csc316.security_manager.list;


import java.util.NoSuchElementException;

/**
 * Custom Array Queue
 * @author Daniel Grochmal
 *
 * @param <E> a generic type for use in the Queue
 */
public class Queue<E> {

	private ArrayList<E> list;
	private int capacity;
	
	/**
	 * Queue constructor of type array
	 * @param capacity is the capacity of the queue
	 */
	public Queue(int capacity) {
		list = new ArrayList<E>();
		this.capacity = capacity;
	}

	/**
	 * Adds an element to the Queue
	 * @param element Element to be added
	 */
	public void enqueue(E element) {
		if (list.size() >= capacity) {
			this.setCapacity(capacity * 2);
		}
		list.add(list.size(), element);
	}

	/**
	 * Removes an element from the queue
	 * @return the element removed from the queue
	 */
	public E dequeue() {
		if(!list.isEmpty()){
			return list.remove(0);
		}
		throw new NoSuchElementException();
	}

	/**
	 * Discerns if the Queue is empty or not
	 * @return True if the Queue is empty, false othewise
	 */
	public boolean isEmpty() {
		return (list.size() == 0);
	}

	/**
	 * Gets the lists size
	 * @return The lists size
	 */
	public int size() {
		return list.size();
	}

	/**
	 * Sets the capacity of the Queue
	 * @param capacity The capacity to be set
	 */
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < list.size()) {
			throw new IllegalArgumentException();
		}
		this.capacity = capacity;
	}

	/**
	 * Gets the capacity of the Queue
	 * @return the capacity of the Queue
	 */
	public int getCapacity() {
		return capacity;
	}

}