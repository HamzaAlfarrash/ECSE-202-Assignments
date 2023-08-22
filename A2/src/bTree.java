import acm.program.ConsoleProgram;

/**
 * Implements a B-Tree class using a NON-RECURSIVE algorithm.
 * 
 * @author ferrie
 *
 */

public class bTree {

	// Instance variables

	bNode root = null; // Pointer to the B-Tree
	ConsoleProgram link = null; // Receiver for acm method calls

	/**
	 * Constructor
	 * 
	 * bTree myTree = new bTree(this)
	 * 
	 * Allows use of the acm println() method by the traversal routines so that data
	 * can be displayed using interactive windows.
	 */

	public bTree(ConsoleProgram link) {
		this.link = link;
	}

	/**
	 * addNode method - adds a new node by descending to the leaf node using a while
	 * loop in place of recursion. Ugly, yet easy to understand.
	 * 
	 * @param String data Data stored in the node
	 */

	public void addNode(String data) {

		bNode current;

		// Empty tree

		if (root == null) {
			root = makeNode(data);
		}

		// If not empty, descend to the leaf node according to
		// the input data.

		else {
			current = root;
			while (true) {
				if (keyCompareToignoreCase(data, current.data) < 1) {

					// New data < data at node, branch left

					if (current.left == null) { // leaf node
						current.left = makeNode(data); // attach new node here
						break;
					} else { // otherwise
						current = current.left; // keep traversing
					}
				} else {
					// New data >= data at node, branch right

					if (current.right == null) { // leaf node
						current.right = makeNode(data); // attach
						break;
					} else { // otherwise
						current = current.right; // keep traversing
					}
				}
			}
		}

	}

	/**
	 * makeNode
	 * 
	 * Creates a single instance of a bNode
	 * 
	 * @param String data Data to be added
	 * @return bNode node Node created
	 */

	bNode makeNode(String data) {
		bNode node = new bNode(); // create new object
		node.data = data; // initialize data field
		node.left = null; // set both successors
		node.right = null; // to null
		return node; // return handle to new object
	}

	/**
	 * inorder method - inorder traversal via call to recursive method
	 */

	public void inorder() { // hides recursion from user
		traverse_inorder(root);
	}

	/**
	 * traverse_inorder method - recursively traverses tree in order
	 * (LEFT-Root-RIGHT) and prints each node.
	 * 
	 * @param root start point of the tree/current position
	 */

	public void traverse_inorder(bNode root) {
		if (root.left != null)
			traverse_inorder(root.left);
		// can't go left anymore -> root.left == null
		link.println(root.data);
		// starts printing data from leftmost position
		if (root.right != null)
			traverse_inorder(root.right);
		// can't go right anymore -> root.right == null
	}

	/**
	 * preorder method - preorder traversal via call to recursive method
	 * 
	 */

	public void preorder() {
		traverse_preorder(root);
	}

	/**
	 * traverse_preorder method - recursively traverses tree in preorder
	 * (Root-LEFT-RIGHT) and prints each node.
	 * 
	 * @param bNode root start point of the tree/current position
	 */

	public void traverse_preorder(bNode root) {
		link.println(root.data);
		// prints the current data
		if (root.left != null)
			traverse_preorder(root.left);
		// can't go left anymore -> root.left == null
		if (root.right != null)
			traverse_preorder(root.right);
		// can't go right anymore -> root.right == null
	}

	/**
	 * postorder method - postorder traversal via call to recursive method
	 */

	public void postorder() {
		traverse_postorder(root);
	}

	/**
	 * traverse_postorder method - recursively traverses tree in postorder
	 * (Right-root-left) and prints each node.
	 * 
	 * @param bNode root start point of the tree
	 */

	public void traverse_postorder(bNode root) {
		if (root.right != null)
			traverse_postorder(root.right); // go right if not empty

		link.println(root.data); // print in reverse order (rightmost first)

		if (root.left != null)
			traverse_postorder(root.left); // then go left if not empty
		
	}

	/**
	 * this code was provided by TA Katrina Poulin alphabitically compares the last
	 * names of two strings
	 * 
	 * @param String name1 First name compared
	 * @param String name2 Second name compared
	 * @return int value of comparision (-1,0, or 1)
	 */
	public int keyCompareToignoreCase(String name1, String name2) {
		// found location of spaces
		int space1 = name1.lastIndexOf(' ');
		// if there is no space, will be -1
		int space2 = name2.lastIndexOf(' ');

		// crop string to get only the last name
		String lastName1 = name1.substring(space1 + 1);
		String lastName2 = name2.substring(space2 + 1);

		return lastName1.compareToIgnoreCase(lastName2); // result of comp. in int
	}
}

/**
 * A simple bNode class for use by bTree. The "payload" can be modified
 * accordingly to support any object type.
 * 
 * @author ferrie
 *
 */

class bNode {
	String data;
	bNode left;
	bNode right;
}