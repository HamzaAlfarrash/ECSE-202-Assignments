/**
 * generic Queue class 
 * supporting enqueue and dequeue
 * 
 * @author Hamza Alfarrash
 *
 */
public class Queue {
	
	listNode head;
	listNode tail;
	
	
	/** 
	 * This method inserts
	 * new data to the Queue
	 * @param String data to be enqueued
	 */
	public void enqueue(String data) {
		listNode node = new listNode();
		node.Data = data;
		
		if (tail == null)						// Check if queue is empty
			head = node;				
		else tail.next = node;					// otherwise put the queue ahead of the node
		
		tail = node;				
	}
	
	/**
	 *  This method should be called to 
	 *  remove the least added data
	 *  if queue is empty, null object 
	 *  will be returned
	 * @return the data to be removed
	 */
	public String dequeue() {
		if (head == null) return null;			// Check if Queue is empty
		if (head == tail) tail = null;		    // Check if this is the last node
		String result = head.Data;	
		head = head.next;
		
		return result;
	}
	
	/**
	 * @return string representation of the Queue
	 */
	public String toString() {	
		return toString(head);
	}
	
	/**
	 * @param head node to start from 
	 * @return string representation of the Queue
	 */
	private String toString(listNode head) {
		String output = "";
		while (head != null) {					// check if the queue is empty
			output += head.Data + " ";		// append to the String
			head = head.next;	
		}
		return output;
	}
}
