package edu.ncsu.csc316.security_manager.list;

/**
 * Custom array list implementation
 * 
 * @author Dan Grochmal djgrochm
 * @param <E> Generic type
 */
public class ArrayList<E> {

	/** Initial size of the array */
	private static final int INIT_SIZE = 2500;
	/** Array that stores the ArrayList */
	private E[] list;
	/** Number of elements currently in the ArrayList */
	private int size;
	
	/**
	 * Constructs the ArrayList and initializes fields
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		this.size = 0;
		this.list = (E[]) new Object[INIT_SIZE];
	} 
	
	/**
	 * Adds an element to the ArrayList
	 * @param obj element to add to the ArrayList
	 * @param idx index where element is to be added
	 */
	public void add(int idx, E obj) throws IndexOutOfBoundsException, IllegalArgumentException, NullPointerException {
		if (obj == null){
			throw new NullPointerException();
		}
		for (int i = 0; i < this.size; i++) {
			if (obj.equals(this.list[i])){ 
				throw new IllegalArgumentException();
			}
		}
		if (this.size == this.list.length) {
			growArray();
		}
		if (idx < 0 || idx > this.size) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = size; i > idx; i--) {
			this.list[i] = this.list[i - 1];
		}
		this.list[idx] = obj;
		size++;
	}
	
	/**
	 * Removes an element from the list. Returns removed element
	 * @param idx index of element to remove
	 * @return removed element
	 */
	@SuppressWarnings("unchecked")
	public E remove(int idx) {
		if  (idx < 0 || idx >= size()) {
			throw new IndexOutOfBoundsException();
		}
		Object temp = this.list[idx];
		for (int i = idx; i < size - 1; i++) {
			this.list[i] = this.list[i + 1];
		}
		this.list[size - 1] = null;
		size--;
		return (E) temp;
	}
	
//	/**
//	 * Swaps two elements for use in sorting
//	 * @param i index of first
//	 * @param j index of second
//	 */
//	public void swap(int i, int j){
//		E temp;
//		E one = this.get(i);
//		E two = this.get(j);
//		temp = one;
//		this.list[i] = two;
//		this.list[j] = temp;
//	}
	
	/**
	 * Increases the capacity of the ArrayList 
	 */
	@SuppressWarnings("unchecked")
	public void growArray() {
		E[] temp = this.list;
		this.list = (E[]) new Object[this.list.length + 1000];
		for (int i = 0; i < size; i++) {
			this.list[i] = temp[i];
		}
	}

	
	/**
	 * Replaces an element in the ArrayList
	 * @param idx index of element to replace
	 * @param obj element to replace the element in the ArrayList with
	 * @return element added to ArrayList
	 */
	@SuppressWarnings("unchecked")
	public E set(int idx, E obj) throws IndexOutOfBoundsException, IllegalArgumentException, NullPointerException {
		if (idx < 0 || idx > this.size) {
			throw new IndexOutOfBoundsException();
		}
		Object temp = this.list[idx];
		this.list[idx] = obj; 
		return (E )temp;
	}

	/**
	 * Returns an element from the ArrayList
	 * @param index index of element to get
	 * @return element at index
	 */
	public E get(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException();
		}
		return this.list[index];
	}

	/**
	 * Return the number of elements in the ArrayList
	 * @return the number of elements in the ArrayList
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Discerns if the list is empty or not
	 * @return True if the list is empty, false otherwise
	 */
	public boolean isEmpty() {
		return size == 0;
	}
}