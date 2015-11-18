import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class Utility {
	public static void parseElements(List<Element> eleList, BufferedReader br) {
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				if (line.contains("Rules:"))
					return;
				if (line.length() == 0)
					continue;
				Element tmpEle = new Element(line);
				eleList.add(tmpEle);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void parseRules(List<ExecRules> erList, BufferedReader br) {
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				if (line.length() == 0)
					continue;
				line = line.trim();
				ExecRules er = new ExecRules();
				er.addRule(new Rule(line));
				erList.add(er);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	private static boolean isOperator (char c) {
		return c =='+' || c == '*' || c == '!' || c == '(' || c == ')';
	}

	private static boolean isSpace(char c) {
		return (c == ' ');
	}
	private static boolean lowerPrecedence(char op1, char op2) {
		// Tell whether op1 has lower precedence than op2
		switch (op1) {
		case '+':
			return op2 == '*' || op2 == '!' ||op2 == '(';
		case '*':
			return op2 == '(' || op2 == '!';
		case '!':
			return op2 == '(';
		case '(':
			return true;
		default:
			return false;
		}
	}

	public static String convertToPostfix(String infix) {
		// Return a postfix representation of the expression in infix.
		Stack<String> operatorStack = new Stack<String>(); //the stack of opeartors
		char c; //the first character of a token
		StringTokenizer parser = new StringTokenizer(infix, "+*!() ", true);
		// StringTokenizer for the input string
		StringBuffer postfix = new StringBuffer(infix.length());//result

		// Process the tokens.
		while (parser.hasMoreTokens()) {
			// get the next token and let c be the first character of this token
			String token = parser.nextToken();
			c = token.charAt(0);

			if((token.length() == 1) && isOperator(c)) {
				//if token is an operator
				while (!operatorStack.empty()
						&& !lowerPrecedence(((String) operatorStack.peek()).charAt(0),c))
					// Operator on the stack does not have lower precedence
					// so it goes befor this one.
					postfix.append(" ").append((String) operatorStack.pop());
				if (c == ')') {
					// Output the remaining operators in the parenthesized part.
					if (operatorStack.empty()) {
						System.err.println("Parentheses mismatched");
						return null;
					}
					String operatora = (String) operatorStack.pop();
					while (operatora.charAt(0) != '(') {
						postfix.append(" ").append(operatora);
						if (operatorStack.empty()) {
							System.err.println("Parentheses mismatched");
							return null;
						}
						operatora = (String) operatorStack.pop();
					}
				} else {
					// Push this operator onto the stack.
					operatorStack.push(token);
				}
			} else if ((token.length() == 1) && isSpace(c)) {
				//else if token was a space igore it
			} else { // it is on operand, output the operand
				postfix.append(" ").append(token);
			}
		}
		//Output the remaining operators ont the stack.
		while (!operatorStack.empty()) {
			String op = operatorStack.pop();
			if (op.equals("(")) {
				System.err.println("Parentheses mismatched");
				return null;
			}
			postfix.append(" ").append(op);
		}
		return postfix.toString();
	}
}
