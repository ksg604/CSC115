/** 
 *  Name       : Kevin San Gabriel
 *  Student ID : V00853258
 */

import java.util.regex.Pattern;
import java.util.regex.Matcher;


public class ArithExpression {

	private TokenList postfixTokens;
	private TokenList infixTokens;

	/**
	 * Sets up a legal standard Arithmetic expression.
	 * The only parentheses accepted are "(" and ")".
	 * @param word An arithmetic expression in standard infix order.
	 * 		An invalid expression is not expressly checked for, but will not be
	 * 		successfully evaluated, when the <b>evaluate</b> method is called.
	 * @throws InvalidExpressionException if the expression cannot be properly parsed,
	 *  	or converted to a postfix expression.
	 */
	public ArithExpression(String word) {
		if (Tools.isBalancedBy("()",word)) {
			tokenizeInfix(word);
			infixToPostfix();
		} else {
			throw new InvalidExpressionException("Parentheses unbalanced");
		}
	}

	/*
	 * A private helper method that tokenizes a string by separating out
	 * any arithmetic operators or parens from the rest of the string.
	 * It does no error checking.
	 * The method makes use of Java Pattern matching and Regular expressions to
	 * isolate the operators and parentheses.
	 * The operands are assumed to be the substrings delimited by the operators and parentheses.
	 * The result is captured in the infixToken list, where each token is 
	 * an operator, a paren or a operand.
	 * @param express The string that is assumed to be an arithmetic expression.
	 */
	private void tokenizeInfix(String express) {
		infixTokens  = new TokenList(express.length());

		// regular expression that looks for any operators or parentheses.
		Pattern opParenPattern = Pattern.compile("[-+*/^()]");
		Matcher opMatcher = opParenPattern.matcher(express);

		String matchedBit, nonMatchedBit;
		int lastNonMatchIndex = 0;
		String lastMatch = "";

		// find all occurrences of a matched substring
		while (opMatcher.find()) {
			matchedBit = opMatcher.group();
			// get the substring between matches
			nonMatchedBit = express.substring(lastNonMatchIndex, opMatcher.start());
			nonMatchedBit = nonMatchedBit.trim(); //removes outside whitespace
			// The very first '-' or a '-' that follows another operator is considered a negative sign
			if (matchedBit.charAt(0) == '-') {
				if (opMatcher.start() == 0 || 	
					!lastMatch.equals(")") && nonMatchedBit.equals("")) {
					continue;  // ignore this match
				}
			}
			// nonMatchedBit can be empty when an operator follows a ')'
			if (nonMatchedBit.length() != 0) {
				infixTokens.append(nonMatchedBit);
			}
			lastNonMatchIndex = opMatcher.end();
			infixTokens.append(matchedBit);
			lastMatch = matchedBit;
		}
		// parse the final substring after the last operator or paren:
		if (lastNonMatchIndex < express.length()) {
			nonMatchedBit = express.substring(lastNonMatchIndex,express.length());
			nonMatchedBit = nonMatchedBit.trim();
			infixTokens.append(nonMatchedBit);
		}
	}
	

	/**
	 * Determines whether a single character string is an operator.
	 * The allowable operators are {+,-,*,/,^}.
	 * @param op The string in question.
	 * @return True if it is recognized as a an operator.
	 */
	public static boolean isOperator(String op) {
		switch(op) {
			case "+":
			case "-":
			case "/":
			case "*":
			case "^":
				return true;
			default:
				return false;
		}
	}
	
	/**
	 * Private method that works with operatorPrecedence to check which operation to perform first in an expression
	 * @param op The operator in string form
	 */
	private int getPrecedence(String op) {
		int i = 0;
		if (op.equals("+") || op.equals("-")) {
			i = 1;
		} else if (op.equals("*") || op.equals("/")) {
			i = 2;
		} else if (op.equals("^")) {
			i = 3;
		}
		return i;
	}
	
	/**
	 * Private method that works with getPrecedence to check which operation to perform first in an expression
	 * @ param op1 The first operator
	 * @ param op2 The second operator
	 */
	private boolean operatorPrecedence(String op1, String op2) {
		return (getPrecedence(op1) >= getPrecedence(op2));
	}
	

	
	
		
	/**
	 * NOTE TO STUDENT: These requirements don't show up in the 
	 * java documentation because it is a private method.
	 * It is private because it directly accesses the data fields.	
	 * 
	 * A private method that initializes the postfixTokens data field.
	 * It takes the information from the infixTokens data field.
	 * If, during the process, it is determined that the expression is invalid,
	 * an InvalidExpressionException is thrown.
 	 * Note that since the only method that calls this method is the constructor,
	 * the Exception is propogated through the constructor.
	 */
	private void infixToPostfix() {
		postfixTokens = new TokenList();
		StringStack operatorStack = new StringStack();
		if (postfixTokens == null) {
			throw new InvalidExpressionException("Invalid Expression.");
		}
		
		for(int i = 0; i < infixTokens.size(); i++) {
			if(!isOperator(infixTokens.get(i)) && !infixTokens.get(i).equals("(") && !infixTokens.get(i).equals(")")) { 
				postfixTokens.append(infixTokens.get(i));	//add operands to list
			}
			if(infixTokens.get(i).equals("(")) {
			operatorStack.push(infixTokens.get(i));		//push left parentheses to stack
			}
			if(infixTokens.get(i).equals(")")) {
				while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
					postfixTokens.append(operatorStack.pop());			//
				}
				operatorStack.pop();
			}
			if(isOperator(infixTokens.get(i))) {
				if(operatorStack.isEmpty() || operatorStack.peek().equals("(")) {
					operatorStack.push(infixTokens.get(i));
				}else {
					while(!operatorStack.isEmpty() && !operatorStack.peek().equals("(") && operatorPrecedence(operatorStack.peek(), infixTokens.get(i))) {
						postfixTokens.append(operatorStack.pop());
					}
					operatorStack.push(infixTokens.get(i));
				}
			}		
		}
		
		while(!operatorStack.isEmpty()) {
				postfixTokens.append(operatorStack.pop());
		}		
	}
	
	
	/**
	 * Gets and returns the String form of an infix expression
	 */
	public String getInfixExpression() {
		return infixTokens.toString();
	}

	/**
	 * Gets and returns the String form of a postfix expression
	 */
	public String getPostfixExpression() {
		return postfixTokens.toString();

	}
	
	/**
	 * Evaluates a postfix expression
	 */
	public double evaluate() {
	StringStack stack = new StringStack();
	double result = 0;
	for (int i = 0; i < postfixTokens.size(); i++) {
		if (!isOperator(postfixTokens.get(i)) && !postfixTokens.get(i).equals("(") && !postfixTokens.get(i).equals(")")) {
			stack.push(postfixTokens.get(i));	//pushes operands to the stack
		}else if (isOperator(postfixTokens.get(i))) {	
			double op1 = Double.parseDouble(stack.pop());	//pops two operands to perform an operation
			double op2 = Double.parseDouble(stack.pop());
			switch(postfixTokens.get(i)) {
				case "+": result = op1 + op2; stack.push(Double.toString(result)); break;
				case "-": result = op2 - op1; stack.push(Double.toString(result)); break;
				case "/": result = op2 / op1; stack.push(Double.toString(result)); break;
				case "*": result = op1 * op2; stack.push(Double.toString(result)); break;
				}
				if(postfixTokens.get(i).equals("^")) {
					if(op1 < 0) {
						throw new InvalidExpressionException("Invalid Expression");
					}else {
						 result = Math.pow(op2, op1); stack.push(Double.toString(result));
					}
				}
			}
		}
		return result;
	}
	

	//main method to test the instance methods					
	public static void main(String[] args) {
	ArithExpression exp = new ArithExpression("(5+1)^2 + 5 ");
	System.out.println("Infix Expression: "+exp.getInfixExpression());
	System.out.println("Postfix Expression: "+exp.getPostfixExpression());
	System.out.println("Result: "+exp.evaluate());

	
	}
			
}
