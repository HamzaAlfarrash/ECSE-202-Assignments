//Hamza Alfarrash McGill ID: 261017161
import acm.program.ConsoleProgram;

public class Base2Base extends ConsoleProgram {
	
	//The 'run' method code was taken from A1 hand out given by Prof. Ferrie
	// the run method includes the program identification print statements and the loop the operates the base2base conversion
	public void run() {
		// Identify Program
		println("Base conversion program for positive integers.");
		println("The program prompts for 3 inputs:");
		println("1. String corresponding to the input number");
		println("2. An integer corresponding to the base of the input [2,16].");
		println("3. An integer corresponding to the base of the output [2,16].");
		println("A blank or negative input or base value outside of [2,16] terminates the program.");
		
		
		while (true) { //while loop that collects the data needed from all methods in the class to compute the output
			
			// 3 different inputs
			// 1. input -> String
			
			String input = readLine("input: "); //used to read the input value from user
			// 2 termination cases (-ve and blank)
			if (input.equals("") || input.charAt(0)=='-') break;
			int inBase = readInt("input base: "); // used to read the input base from user
			if (inBase < 2 || inBase > 16) break; // Check bounds on input base
			int outBase = readInt("output base: ");// used to read output base from user
			if (outBase < 2 || outBase > 16) break; // Same for outBase
			
			// Call String2Int method to determine decimal value of input string.
			// If invalid input, skip to next round. Method returns -1 if input is invalid.
			
			int inVal = String2Int(input,inBase);
			if (inVal < 0) {
			    println("Input inconsistent with base. Try again or exit program with blank line.");
			} else {
				// convert decimal to output base
				String output = Int2String(inVal, outBase); // stores the resulting output string that will be printed to the user 
				
				//prints the input value in the input base
				//prints the output decimal in the new base
				println(input+" has decimal value "+inVal+".");
				println(input+" expressed in Base "+outBase+" = "+output+".");
				println();
			}
			
		}		
		println("Program terminated."); // end of the program which is called if an error occurs in the while loop
	}
	 
	//String2Int takes the input value and base and returns calculates its value in that base
	private int String2Int(String inputValue, int inBase) {
		int sum = 0;
		int power = 0; // -> inBase**(p)
		for (int i = inputValue.length() - 1; i >= 0; i--) {
			char c = inputValue.charAt(i);
			// convert to decimal
			int value = Char2Int(c, inBase);
			if (value == -1 || value > (inBase - 1)) {
				return -1;
			}
			sum += value*Math.pow(inBase, power);
			power++;
		}
		
		return sum;
	}
	
	// Int2String method takes the decimalValue computed from String2int and computes the output value in the output base requested.
	private String Int2String(int decimalValue, int outBase) {
		String output = ""; //initial condition of the output string that will contain the original value in the new output base.
		
		while (decimalValue>0) { //loops through each digit of the decimalValue of current base and gets the equivalent value in the out base 
			int currentDigit = (decimalValue % outBase);
			decimalValue = (decimalValue / outBase);
			if (currentDigit < 10) { //adds the new digits to the output string via casting and concatenation
				output = (char)(currentDigit + '0') + output;
			}
			else {
				output = (char)(currentDigit - 10 + 'a') + output; 
			}
		}
		return output;
	}
	
	
	//The following code was taken from TA Katrina Poulin in ECSE202 tutorial session.
	//Char2Int converts char to integer by adding/subtracting the suitable characters.
	private int Char2Int(char c, int inBase) {
		//method instructions
		
		int number = 0; // initial condition of the output integer we need
		// c is a number
		if (c >= '0' && c <= '9') { //if the char was 0-9 digit
			number = c - '0';
		} else if (c >= 'A' && c <= 'F' ) { //if the char was in CAPS
			number = c - 'A' + 10;
		} else if (c >= 'a' && c <= 'f' ) { //if the char was in lowercase
			number = c - 'a' + 10;
		} else { //error statement
			number = -1;
		}
		if (number >= inBase) { //checks if there the produced integer > input base, which results in error
			number = -1;
		}
		return number;
	}

}
