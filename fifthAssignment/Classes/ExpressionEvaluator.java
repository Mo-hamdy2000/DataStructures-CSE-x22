package Classes;

import Interfaces.IExpressionEvaluator;

/**
 * This class implement converting an infix expression into postfix
 * It evaluates postfix expression
 * It is implemented using stack data structure 
 * @author Mo'men
 *
 */
public class ExpressionEvaluator implements IExpressionEvaluator {

	/**
	 * This function takes operator character and return its precedence
	 * @param c character representing an operator
	 * @return an int value indicating the operator precedence 
	 */
	private int priority(char c) {
		if (c == '+' || c == '-') {
			return 1;
		}
		else if (c == '*' || c == '/' || c == '%') {
			return 2;
		}
		else if (c == '^') {
			return 3;
		}
		return 0;
	}
	
	
	/**
	 * This function checks whether a charachter is an operator or not 
	 * @param c the character to be tested
	 * @return boolean value indicate the charater state
	 */
	private boolean isOperator(char c) {
		if (c == '+' || c == '-' || c == '*' || c == '/' || c == '%' || c == '^') {
			return true;
		}
		return false;
	}
	
	
	/**
	 * This funtion takes two inputs and perform arithmetic operation between them 
	 * depending on the entered operator parameter
	 * @param val1 the first operand
	 * @param val2 the second operand
	 * @param c the operator character
	 * @return the result of the  arithmetic operation between the two parameters
	 */
	private float operate(float val1, float val2, char c) {
		switch(c) 
		{ 
		    case '+': 
		    	return val1 + val2;
		      
		    case '-': 
		    	return val1 - val2; 
		      
		    case '/': 
		    	if (val2 == 0) { throw new ArithmeticException();}
		    	return val1 / val2; 
		      
		    case '*': 
		    	return val1 * val2;
		    	
		    case '%': 
		    	return val1 % val2;
		    
		    case '^': 
		    	float base = 1;
		    	for(int i = 0; i < val2; i++) {
		    		base *= val1;
		    	}
		    	return base;
		 }
		return 0;
	}
	
	
	
	/**
	 * This function takes an infix expression to get it in postfix expression
	 * @param expression the string containing the infix expression
	 * @return string with the postfix expression
	 */
	@Override
	public String infixToPostfix(String expression) {
		// String for storing the postfix expression
		String postfix = "";
		// Stack for operating the operators
		Stack stk = new Stack();
		// A character prev to handle negative and multi digit integers
		char prev = ' ';
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			// If the character is alphanumeric then append it to the postfix expression
			if (Character.isDigit(c) || Character.isLetter(c)) {
				if (Character.isLetter(c) && Character.isLetter(prev)) { throw new RuntimeException("Invalid Expression");}
				if(!Character.isDigit(prev) && prev != ' ') {
					postfix += " ";
				}
				postfix += c;
				prev = c;
			}
			// If it is an opening parenthesis then push it to the stack
			else if(c == '(') {
				stk.push(c);
				prev = c;
			}
			// If it is exponential operator then it has higher priority
			// then it is pushed to the stack directly
			else if(c == '^') {
				stk.push(c);
				prev = c;
			}
			// If it is a closing parenthesis then we pop the stack til we find
			// an opening one and append the values to the postifix expression 
			else if (c == ')') {
				while (!stk.isEmpty() && (char)stk.peek()!= '(') {
					postfix += " " + (char)stk.pop();
				}
				if (stk.isEmpty()) {
					throw new RuntimeException("Invalid Expression");
				}
				stk.pop();
				prev = c;
			}
			else if (!isOperator(c)) {
				if (c == ' ') { continue;}
				else {
					throw new RuntimeException("Invalid Expression");
				}
			}
			else {
				// If we have - operator and the previous character was not a digit
				// Then we consider a unary negative operator
				if(((!Character.isDigit(prev) && !Character.isLetter(prev)) || prev == ' ') && c == '-') {
					String operand = "";
					int j = i+1;
					while (expression.charAt(j) == ' ') {
						j++;
					}
					while (j < expression.length() && (Character.isDigit(expression.charAt(j)) || Character.isLetter(expression.charAt(j)))) {
						operand += expression.charAt(j);
						prev = expression.charAt(j);
						j++;
					}
					i = j - 1;
					postfix += " 0 " + operand + " " + c;
					continue;
				}
				// If it is any other operator we will pop all the operators with
				// a higher precedence than it or equal and append them to the postfix expression
				// Finally we append this operator to the stack
				else {
					if (isOperator(prev)) { throw new RuntimeException("Invalid Expression");}
					while (!stk.isEmpty() && priority((char)stk.peek()) >= priority(c)) {
						postfix += " " + (char)stk.pop();
					}
					stk.push(c);
				}
				prev = c;
			}
		}
		// If the stack still has operators in it we append them to the postifix expression
		while (!stk.isEmpty()) {
			if ((char)stk.peek() == '(') {
				throw new RuntimeException("Invalid Expression");
			}
			postfix += " " + (char)stk.pop();
		}
		return postfix.trim();
	}

	/**
	 * This function takes a postfix expression as a string and evaluate it
	 * @param expression the string containing the postfix expression
	 * @return the value  resulted from evaluating the postfix expression
	 */
	@Override
	public int evaluate(String expression) {
		// Stack for operating the operands
		Stack stk = new Stack();
		for (int i = 0; i < expression.length(); i++) {
			char c = expression.charAt(i);
			// If the character is digit or negative unary operator then determine the whole number limits
			//  and parse it then push it to the stack
			if((expression.charAt(i) == '-' && i < expression.length()-1
					&& Character.isDigit(expression.charAt(i+1)))
					|| Character.isDigit(expression.charAt(i))) {
				int start = i;
				if (expression.charAt(i) == '-') { i++;}
				for (; Character.isDigit(expression.charAt(i+1)); i++);
				stk.push(Float.parseFloat(expression.substring(start, i+1)));
			}
			// If the character is operator then pop two numbers to operate them 
			else if (isOperator(c)) {
				if(stk.size() > 1) {
					float val2 = Float.parseFloat(stk.pop().toString());
					float val1 = Float.parseFloat(stk.pop().toString());
					try {
						stk.push(operate(val1, val2, c));
					}
					catch (Exception e) {
						throw new RuntimeException("Arthmetic Error!!");
					}
				}
				// If the stack does not have enough operands then it is invalid expression
				else {
					throw new RuntimeException("The Expression can not be evaluated!!");
				}
			}
			else if (c == ' ') { continue;}
			// If the character is not a digit or an operator or a white space
			// then it is invalid expression
			else {
				throw new RuntimeException("The Expression can not be evaluated!!");
			}
		}
		if(stk.size() != 1) {
			throw new RuntimeException("The Expression can not be evaluated!!");
		}
		int value = (int)Float.parseFloat(stk.pop().toString());
		return value;
	}
}
