import acm.program.ConsoleProgram; //imports the ConsoleProgram class

/**
 * A UI for printing sorted binary tree strings using bTree class
 * 
 * @author Hamza Al-farrash
 *
 */
public class TreeSort extends ConsoleProgram {

	/**
	 * Prints Program Identifier Gets string inputs(Names) from user prints a sorted
	 * and reverse sorted alphabetical lists
	 */
	public void run() {

		// Program Identifier
		println("Text Sorting Program: (ECSE 202 - Assignment 2)");
		println("Enter Names to be sorted, line by line. A blank line terminates.");
		println("You can cut and paste text into this window:\n");

		// create bTree
		bTree myTree = new bTree(this); // declaring object myTree of type bTree

		// keeps getting strings from user until it breaks
		while (true) {
			String userinput = readLine(""); // gets a string from user (readLine)

			// if ("") then break out of the loop
			if (userinput.equals("")) {
				break;
			}

			// add a node to the bTree
			myTree.addNode(userinput);

		}
		// output:

		if (myTree.root == null) { // error message when tree is empty
			print("Please input a Name.");
		} else {
			println("Text in sort order:\n");
			myTree.traverse_inorder(myTree.root); // prints myTree alphabetically

			println("\nText in reverse sort order:\n");
			myTree.traverse_postorder(myTree.root); // prints myTree reverse alphabetically

			println("\nProgram terminated."); // End of Program

		}

	}

}