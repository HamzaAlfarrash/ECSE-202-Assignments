/**
 * This is a generic standard Stack class supporting push and pop methods.
 * 
 * @author Hamza Alfarrash
 *
 * @param Any data type can be used
 */
public class Stack {

	/* Item on the top of the stack */
	private listNode top;

	/**
	 * This is a standard push method of the stack which take arg of type T
	 * 
	 * @param arg data to be pushed in the stack
	 */
	public void push(String arg) {
		listNode node = new listNode(); // Create new node
		node.data = arg; // set data to the node
		node.next = top; // place the node on top of the stack
		top = node; // keep a ref to the top node
	}

	/**
	 * This is method remove the most recently added data from the Stack
	 * 
	 * @return the latest data added to the stack
	 */
	public String pop() {
		if (top == null)
			return null; // Check if Stack is empty
		String output = top.data; // otherwise save the data before deleting the node
		top = top.next; // remove the node
		return output;
	}
}