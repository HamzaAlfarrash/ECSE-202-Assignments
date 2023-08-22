import acm.program.ConsoleProgram;

/**
 * This class responsible for user dialog to convert a mathematical expression
 * from Infix to postFix
 * 
 * @author Hamza Alfarrash This is part of assignment 3 for McGill ECSE 202
 *         winter 2022.
 *
 */
public class In2p extends ConsoleProgram {

	public void run() {

		println("Infix to Postfix conversion, enter expression of blank line to exit.");

		postFix myPostFix = new postFix(); // create new postFix instance

		while (true) {

			String userInput = readLine("expr: ");
			if (userInput.equals(""))
				break; // Terminate when the user input empty field

			if (errorCheck(userInput) == false) {
				println("Please input valid characters");
				break;
			}

			String input = "";
			for (char c : userInput.toCharArray()) // remove all empty spaces from the user input
				if (c != ' ')
					input += c;

			Queue q = myPostFix.parse(input); // parse the input into tokens

			println(userInput + " ==> " + myPostFix.In2Post(q).toString()); // show the result in postFix form

		}

		println("Program terminated.");
	}

	/**
	 * Checks if the string contains invalid characters relative to this program
	 * 
	 * @param String input to check errors on
	 * @return boolean true if no error false otherwise
	 */
	public boolean errorCheck(String s) {
		for (int i = 0; i < s.length() - 1; i++) {
			if (Character.isLetter(s.charAt(i)) == true) {
				return false;
			}
		}
		return true;
	}

}