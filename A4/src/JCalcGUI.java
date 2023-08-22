import java.awt.Color;
import java.awt.event.ActionEvent;
import java.math.BigDecimal;
import java.math.MathContext;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import acm.gui.TableLayout;
import acm.program.Program;

/**
 * This class creates a user GUI with buttons to convert a mathematical
 * expression from Infix to postFix and then show the result of the postFix
 * expression.
 * 
 * @author Hamza Alfarrash This is part of assignment 4 for 2 * McGill ECSE 202
 *         winter 2022.
 */
public class JCalcGUI extends Program {

	/* User Input Field */
	private JTextField expressionField;
	/* Field to show the result to the user */
	private JTextField answerField;

	JSlider sigfig = new JSlider(0, 16, 5); // adds a slider to control sigfigs
	JLabel currentSigfig = new JLabel("current sigfig = " + sigfig.getValue()); // shows the current precision value of
																				// sigfigs

	/* create postFix instance to be used across the program */
	postFix post = new postFix();

	/**
	 * Entry point to the program
	 */
	public void init() {

		setLayout(new TableLayout(9, 4)); // set a TableLayout Manager to take care of the UI

		expressionField = new JTextField(); // set the TextField where the user will enter values
		expressionField.setActionCommand("="); // when user hit enter, will be treated as =
		expressionField.addActionListener(this); // add Listener to the TextField
		add(expressionField, "gridwidth=4"); // add to the layout and fill all the row

		answerField = new JTextField(); // set a plain TextField to show result
		add(answerField, "gridwidth=4"); // add to the layout and fill all the row

		addButtons(); // set the rest of the layout
		addActionListeners(this);
		; // set Listeners for the actions
		setBackground(new Color(122, 166, 253)); // set the background color

	}

	/**
	 * when call this method, all buttons will be added to the layout
	 * 
	 */
	private void addButtons() {
		// TODO ADD COMMENTING
		add(currentSigfig, SOUTH); // adds the JLabel showing current sigfig precision
		add(new JLabel("Sig. Fig. slider: "), NORTH); // following 4lines adds the slider and its endpoints
		add(new JLabel("0"), NORTH);
		add(sigfig, NORTH);
		add(new JLabel("16"), NORTH);

		add(new JButton("C"));
		add(new JButton("+/-"));
		add(new JButton("%"));
		add(new JButton("/"));
		add(new JButton("7"));
		add(new JButton("8"));
		add(new JButton("9"));
		add(new JButton("x"));
		add(new JButton("4"));
		add(new JButton("5"));
		add(new JButton("6"));
		add(new JButton("-"));
		add(new JButton("1"));
		add(new JButton("2"));
		add(new JButton("3"));
		add(new JButton("+"));
		add(new JButton("0"));
		add(new JButton("."));
		add(new JButton("^"));
		add(new JButton("="));
		add(new JButton("("));
		add(new JButton(")"));
		add(new JButton());
		add(new JButton());
		add(new JButton());
		add(new JButton());
		add(new JButton());
		add(new JButton("Quit"));
	}

	/**
	 * This method handles all actions when the user hits any button
	 */
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		switch (cmd) {
		case "x":
			expressionField.setText(expressionField.getText() + "*");
			break; // appends * to the TextField when user hit x button
		case "C":
			expressionField.setText("");
			answerField.setText("");
			break; // Clears all text fields
		case "Quit":
			System.exit(0);
			break; // Kills the program, 0 indicates a normal termination
		case "=":
			printResult(); // shows result for the input and continue switch statement
		case "+":
		case "-":
		case "/":
		case "%":
		case "^":
		case "*":
		case "0":
		case "1":
		case "2":
		case "3":
		case "4":
		case "5":
		case "6":
		case "7":
		case "8":
		case "9":
		case ".":
		case "(":
		case ")":
			expressionField.setText(expressionField.getText() + cmd); // Append digit or operator to the input
		}
	}

	/**
	 * This method handles the input and show the results to the user
	 */
	public void printResult() {

		String userInput = expressionField.getText(); // get user input as a String

		if (userInput.charAt(userInput.length() - 1) == '=') // Check if user hit = twice
			userInput = userInput.substring(0, userInput.length() - 1); // if yes, delete the '=' at the end of the text

		expressionField.setText(userInput);

		String input = "";
		for (char c : userInput.toCharArray()) // remove all empty spaces from the user input
			if (c != ' ')
				input += c;

		Queue tokens = post.parse(input); // parse tokens
		Queue postFixResult = post.In2Post(tokens); // get postFix expression

		answerField.setText(String.valueOf(sigFigures(post.PostEval(postFixResult)))); // Calculates and shows the result
	}

	/**
	 * This method was duplicated from Sean Owen on Stackoverflow reference:
	 * https://stackoverflow.com/questions/7548841/round-a-double-to-3-significant-figures
	 * it rounds the double input to sigfigs set by user using a slider
	 * 
	 * @param double value normal decimal value/answer
	 * @return double rounded value to chosen sigfigs
	 */
	public double sigFigures(double value) {
		int sf = sigfig.getValue(); // gets the sigfigs from the slider
		currentSigfig.setText("current SigFig = " + sf); // updates sigfig JLabel to newly selected sigfig precision
		BigDecimal bd = new BigDecimal(value); // creates instance of BigDecimal object
		bd = bd.round(new MathContext(sf)); // uses round method
		return bd.doubleValue(); // returns final rounded answer

	}
}