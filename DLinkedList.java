
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  AmazonStore.java
// File:             DLinkedList.java
// Semester:         CS367 Spring 2015
//
// Author:           Richard Chen(Siyu) schen358@wisc.edu
// CS Login:         chens
// Lecturer's Name:  Jim Skrentny
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Zixin Chi
// Email:            zchi4@wisc.edu
// CS Login:         zixin
// Lecturer's Name:  Jim Skrentny
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          Identify persons by name, relationship to you, and email.
//                   Describe in detail the the ideas and help they provided.
//
// Online sources:   avoid web searches to solve your problems, but if you do
//                   search, be sure to include Web URLs and description of 
//                   of any information you find.
//////////////////////////// 80 columns wide //////////////////////////////////
public class DLinkedList<E> implements ListADT {

	private Listnode<E> head;
	private Listnode<E> tail;
	private int numItems;

	public  DLinkedList() {
		head = null;
		tail = null;
		numItems = 0;
	}
	
	/**
	 * Adds item to the end of the List.
	 *
	 * @param item that will be added to the end of the list
	 * 
	 */
	public void add(Object item) {

		if (item == null) throw new IllegalArgumentException();
		Listnode<E> newnode = new Listnode <E> ((E)item);

		//Special Case: empty list

		if (head == null) {
			head = newnode;
			tail = newnode;
			numItems++;
		}
		//General Case: non-empty list
		else {
			tail.setNext(newnode);
			newnode.setPrev(tail);
			tail = newnode;
			tail.setNext(null);
			numItems++;
		}
	}
	/**
	 * Adds item at position pos in the List, moving the items originally 
	 * in positions pos through size() - 1 one place to the right to make room.
	 *
	 * @param the position in the list that the item will be added
	 * @param item that will be added to the list
	 */

	public void add(int pos, Object item) {
		if (item == null || pos < 0) throw new IllegalArgumentException();
		
		Listnode<E> curr = head;
		Listnode<E> newnode = new Listnode <E> ((E)item);
		//Special Case: empty list
		if (head == null) {
			head = newnode;
			tail = newnode;
			numItems++;
		}
		//Special case: add at front
		if(pos == 0){
			newnode.setNext(curr);
			curr.setPrev(newnode);
			head = newnode;
			numItems++;
		}
		//General Case: non-empty list
		else{
			for(int i = 0; i < pos-1; i++){
				curr = curr.getNext();
			}
			newnode.setNext(curr.getNext());
			curr.getNext().setPrev(newnode);
			newnode.setPrev(curr);
			curr.setNext(newnode);
			numItems++;
		}
		


	}
	/**
	 * Method to check an item is in the list
	 *
	 * @param items that will be checked whether is in the list
	 * @return Returns true if item is in the List 
	 */
	
	public boolean contains(Object item) {
		if (item == null) throw new IllegalArgumentException();
		Listnode<E> curr = head;
		//Special Case: empty list
		if(head == null)
			return false; 
		//General Case: non-empty list
		while (curr != null){
			if(curr.getData().equals(item))
				return true;
			else curr = curr.getNext();
		}	
		return false;
	}
	/**
	 * To get the data of a specific position in the list
	 *
	 * @param position in the list
	 * @return the item at position pos in the List.	 */

	public Object get(int pos) {
		if ( pos < 0) throw new IllegalArgumentException();
		Listnode<E> curr = head;
		//Special Case: empty list
		if(head == null) 		
			throw new IndexOutOfBoundsException();
		//General Case: non-empty list
		else{
			for(int i = 0; i < pos; i++)
				curr = curr.getNext();
			return	curr.getData();
		}
	}
	/**
	 * To check whether the list is empty
	 *
	 * @return true if the list contains no item, otherwise return false
	 */
	public boolean isEmpty() {
		if(numItems ==0){
			return true;
		}
		return false;
	}

	/**
	 * Removes and returns the item at position pos in the List,
	 *
	 * @param the position of the item that will be removed from the list
	 * @return The item that being removed
	 * 
	 */
	public Object remove(int pos) {
		if ( pos < 0) throw new IllegalArgumentException();
		Listnode<E> curr = head;
		//Special Case: empty list
		if(head == null) 		
			throw new IndexOutOfBoundsException();
		//General Case: non-empty list
		else{
			for(int i = 0; i <pos-1;i++)
				curr = curr.getNext();
			curr.setNext(curr.getNext().getNext());
			curr.getNext().setPrev(curr);
			numItems--;
		}
		return null;
	}

	/**
	 * To get the number of items in the List.
	 * @return Return the number of items in the List.
	 */
	public int size() {
		return numItems;
	}

}
