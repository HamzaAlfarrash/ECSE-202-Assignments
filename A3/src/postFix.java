import java.util.StringTokenizer;

/**
 * This class were designed to convert mathematical InFix input and return the
 * PostFix as a Queue.
 * 
 * @author Hamza Alfarrash This is part of assignment 3 for McGill ECSE 202
 *         winter 2022.
 *
 */
public class postFix {

	/**
	 * @param arg mathematical InFix
	 * @return queue of the numbers and operators in the input
	 */
	public Queue parse(String arg) {
		arg.replaceAll(" ", "");
		StringTokenizer str = new StringTokenizer(arg, "*-+/()^%", true); // define delims and returnDelims as well
		Queue output = new Queue();

		while (str.hasMoreTokens())
			output.enqueue(str.nextToken()); // enqueue all tokens
		return output;
	}

	/**
	 * @param Qin queue of numbers '0' to '9' and operators "*-+/()^%"
	 * @return queue of the PostFix expression
	 */
	public Queue In2Post(Queue Qin) {

		Stack operator = new Stack(); // temporally stack to hold operators
		Queue output = new Queue(); // this outputs holds the PostFix

		String head = Qin.dequeue();

		while (head != null) { // check if there is anymore tokens
			if (isOperator(head)) // check if its an operator

				if (head.equals("(") || head.equals(")")) // check if parentheses
					pushParentheses(head, operator, output); // deal with the parentheses
				else
					pushOperator(head, operator, output); // deal with the operator

			else
				output.enqueue(head); // its a number >> enqueue to the output

			head = Qin.dequeue();
		}

		/* Adding all remaining operator to the Queue */
		String pop = operator.pop();
		while (pop != null) {
			output.enqueue(pop);
			pop = operator.pop();
		}
		return output;
	}

	/**
	 * @param head     token to be added to the postFix expression
	 * @param operator stack to hold operators temporally before popping them to the
	 *                 output
	 * @param output   the postFix expression so far
	 */
	private void pushOperator(String head, Stack operator, Queue output) {

		int precedence = getPrecedence(head); // check precedence of the operator

		String topOperator = operator.pop(); // pop operator on top to compare with the current one

		while (topOperator != null) { // check if operator' stack is already empty
			if (precedence > getPrecedence(topOperator)) { // check if current is greater than the one on top
				operator.push(topOperator); // push the one top back to the stack
				break;
			} else if (precedence <= getPrecedence(topOperator))
				output.enqueue(topOperator); // enqueue the operator to the output and repeat until its greater
			topOperator = operator.pop();
		}

		operator.push(head); // add the current operator to the stack
	}

	/**
	 * @param head     token to be added to the postFix expression
	 * @param operator stack to hold operators temporally before popping them to the
	 *                 output
	 * @param output   the postFix expression so far
	 */
	private void pushParentheses(String head, Stack operator, Queue output) {

		if (head.equals("(")) // check if its an open or closed
			operator.push(head); // push it to the operator stack

		else if (head.equals(")")) { // if closed, pop all operator until you find "("
			String top = operator.pop();
			while (top != null && !top.equals("(")) {
				output.enqueue(top);
				top = operator.pop();
			}
		}
	}

	/**
	 * @param head the operator to check
	 * @return integer represent the precedence of the operator +,- << *,/,% << ^
	 */
	private int getPrecedence(String head) {
		switch (head) {
		case "+":
		case "-":
			return 1;
		case "*":
		case "/":
		case "%":
			return 2;
		case "^":
			return 3;
		}
		return -1;
	}

	/**
	 * @param o takes a Character represented as a String
	 * @return if the argument is -+%/*^() will return true
	 */
	public boolean isOperator(String o) {

		switch (o) {
		case "*":
		case "/":
		case "-":
		case "+":
		case "(":
		case ")":
		case "^":
			return true;
		}
		return false;
	}
}