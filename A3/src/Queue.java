/**
 * This is a generic standard Queue class supporting enqueue and dequeue
 * methods.
 * 
 * @author Hamza Alfarrash
 *
 */
public class Queue {

	/* the least added node */
	listNode head;

	/* the most added node */
	listNode tail;

	/**
	 * This method inserts new data to the Queue
	 * 
	 * @param arg String to be enqueued
	 */
	public void enqueue(String arg) {
		listNode node = new listNode();
		node.data = arg;

		if (tail == null) // Check if queue is empty
			head = node;
		else
			tail.next = node; // otherwise put the queue ahead of the node

		tail = node; // move the most added node to the tail of the queue
	}

	/**
	 * This method removes the least added data if queue is empty, null object will
	 * be returned
	 * 
	 * @return the data to be removed
	 */

	public String dequeue() {

		if (head == null)
			return null; // Check if Queue is empty
		if (head == tail)
			tail = null; // Check if this is the last node
		String output = head.data;
		head = head.next;

		return output;
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
		while (head != null) { // check if the queue is empty
			output += head.data + " "; // append to the String
			head = head.next;
		}
		return output;
	}
}