package src;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;
import src.gpuUtility;

public class Utility {
	public static void parseElements(List<Element> eleList, BufferedReader br,
			Map<String, Element> eleMap, Map<String,Integer> eleMapM) {
		String line = "";
		try {
			int i = 0;
			while ((line = br.readLine()) != null) {
				if (line.contains("Rules:"))
					return;
				if (line.length() == 0)
					continue;
				Element tmpEle = new Element(line);
				eleList.add(tmpEle);
				if (eleMap.containsKey(tmpEle.getName())) {
					System.err
						.println("Repeating element: "+ tmpEle.getName());
					System.exit(-1);
				}
				eleMap.put(tmpEle.getName(), tmpEle);
				eleMapM.put(tmpEle.getName(), i);
				i++;
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void parseRules(List<ExecRules> erList,
			BufferedReader br,Map<String,Integer> eleMapM) {
		String line = "";
		try {
			while ((line = br.readLine()) != null) {
				if (line.length() == 0)
					continue;
				line = line.trim();
				String lineIndex = mapIndex(line,eleMapM);
				ExecRules er = new ExecRules();
				er.addRule(new Rule(lineIndex));


				erList.add(er);
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String mapIndex(String line, Map<String,Integer> eleMapM) {
		StringTokenizer parser = new StringTokenizer(line, "+*!(); ", true);
		StringBuffer newLine = new StringBuffer(line.length());
		while (parser.hasMoreTokens()){
			String token = parser.nextToken();
			if (eleMapM.get(token)!=null) {
				int index = eleMapM.get(token);
				newLine.append(String.valueOf(index));
			} else {
				newLine.append(token);
			}
		}
		return newLine.toString();

	}
	/*public static void parseToMatrix(int[][] eleMatrix, List<Element> eleList, int run){
		int maxele = eleList.size();
		for (int i = 0;i < run; i++) {
			int j = 0;
			for (Element e:eleList) {
				eleMatrix[i][j] = e.getCurVal();
				j++;
			}
		}
	}*/
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
	public static void caSimulation (int run, int cycles,List<Element> eleList, 
			List<ExecRules> ruleList,
			Map<String, Element> eleMap) {
		int[][] eleM;
		String[][] ruleLM;
		String[][] ruleRM;
		int elesize = eleList.size();
		eleM = new int[run][elesize];
		for (int i = 0; i<run; i++) {
			List<Element> tmpE = new ArrayList<Element>(eleList);
			for(int j = 0;j<elesize;j++) {
				eleM[i][j] = tmpE.get(j).getCurVal();
			}
		}
		int rulesize = 0;
		for (ExecRules er : ruleList) {
			List<Rule> tmp = er.getExecRules();
			for(Rule r : tmp) {
				rulesize++;
			}
		}
		for (int c = 0; c < cycles; c++) {
			ruleLM = new String[run][rulesize];
			ruleRM = new String[run][rulesize];

			for (int i = 0;i<run;i++) {
				List<ExecRules> tmpER = new ArrayList<ExecRules>(ruleList);
				Collections.shuffle(tmpER);
				int j = 0;
				for (ExecRules er : tmpER) {
					List<Rule> tmpR = er.getExecRules();
					for(Rule r:tmpR) {
						ruleLM[i][j] = r.getLval();
						ruleRM[i][j] = r.getRval();
						j++;
					}
				}
			}
			
			
			eleM = gpuUtility.caSimNoRank(eleM,ruleLM,ruleRM);
			for (int i = 0;i<run;i++) {
				for (int j=0;j<eleM[0].length;j++) {
					System.out.print(eleM[i][j]+" ");
				}
				System.out.println();
			}
			for (int i = 0;i<run;i++) {
				for(int j=0;j<ruleLM[0].length;j++) {
					System.out.print(ruleRM[i][j]+" ");
				}
				System.out.println();
			}
		}
			//List<ExecRules> tmpER = new ArrayList<ExecRules>(rule);
			//Collections.shuffle(tmpER);
			//for (gpuExecRules gpuer : tmpR) {
				//for (ExecRules er : tmpER)
					//evalRule(er,eleMap);
			//}
	}


	public static void evalRule (ExecRules er, Map<String, Element> eleMap) {
		for (Rule rule : er.getExecRules()) {
			Element ele = eleMap.get(rule.getLval());
			if (ele == null) {
				System.err.println("Element does not exist: "+ rule.getLval());
				System.exit(-1);
			}
			int val = evalExpr(rule.getRval(), eleMap);
			ele.setCurVal(val);
		}
	}

	public static boolean isOp(String op) {
		return op.equals("+") || op.equals("*") || op.equals("!");
	}

	public static int evalExpr(String expr, Map<String, Element> eleMap) {
		String[] exprArr = expr.split(" ");
		Stack<Integer> stack = new Stack<Integer>();
		for (int i =0; i < exprArr.length;i++) {
			if (!isOp(exprArr[i])) {
				Element ele = eleMap.get(exprArr[i]);
				if (ele == null) {
					System.out.println("Element: "+exprArr[i]+" doesn't exist'");
					System.exit(-1);
				}
				stack.push(ele.getCurVal());
			} else if (exprArr[i].equals("!")) {
				int op1 = stack.pop();
				int result = procOp(op1, 0, exprArr[i]);
				stack.push(result);
			} else {
				int op1 = stack.pop();
				int op2 = stack.pop();
				int result = procOp(op1, op2, exprArr[i]);
				stack.push(result);
			}
		}
		int answer = stack.pop();
		if (stack.empty())
			return answer;
		else {  //shouldn't reach here
			System.err.println("ERROR!");
			System.exit(-1);
			return 0;
		}
	}
	public static int procOp(int op1, int op2,String op) {
		if (op.equals("!")) {
			return op1 == 1 ? 0:1;
		} else if (op.equals("+")) {
			return (op1+op2)> 0 ? 1 : 0;
		} else if (op.equals("*")) {
			return op1 * op2;
		} else {
			System.err.println("Wrong operator: " + op);
			System.exit(-1);
			return 0;
		}
	}
}
