/**
 * generic stack class supporting push and pop. data type = String
 * 
 * @author Hamza Alfarrash
 *
 */
public class Stack {

	// item on top of stack
	listNode top;

	/**
	 * This is a standard push method of the stack which take arg of type String
	 * 
	 * @param arg data to be pushed in the stack
	 */
	public void push(String arg) {
		listNode node = new listNode(); // Create new node
		node.Data = arg; // set data to the node
		node.next = top; // place the node on top of the stack
		top = node; // keep a ref to the top node
	}

	/**
	 * This method pops the most recently added data from the Stack
	 * 
	 * @return the latest data added to the stack
	 */
	public String pop() {
		if (top == null)
			return null; // Check if Stack is empty
		String output = top.Data; // otherwise save the data before deleting the node
		top = top.next; // remove the node
		return output;
	}

}
