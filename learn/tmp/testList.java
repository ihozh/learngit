import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.Stack;
public class testList{
    private static boolean isOperator(char c) {
        return c == '+' || c =='*' || c == '!' || c == '(' || c == ')';
    }
    private static boolean isSpace(char c) {
        return (c == ' ');
    }
    private static boolean lowerPrecedence(char op1, char op2){
        switch(op1) {
            case '+':
                return op2 == '*' || op2 == '!' || op2 == '(';
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
    public static void main(String[] args){
       /* List<Integer> abb = new ArrayList<Integer>();
        abb.add(1);
        abb.add(2);
        abb.add(3);
        for(Iterator i = abb.iterator();i.hasNext();){
            int num = (Integer) i.next();
            System.out.println(num);
        }*/
        /*String line = "abc=def=aaa=bbb";
        String[] tepArr = line.split("=");
        for(String s:tepArr){
             System.out.println(s);
        }
        String line2 = line.substring(4);
        System.out.println(line2);*/

        //List<gpuExecRules> gpurl = new ArrayList<gpuExecRules>();
        ExecRules rl = new ExecRules();
        rl.addRule(3);
        rl.addRule(15);
        rl.addRule(17);
        rl.addRule(6);
        rl.addRule(10);
        rl.addRule(16);
        rl.addRule(39);
        rl.addRule(1);
        List<Integer> rlst = rl.getExecRules();
        /*for (int er:rlst) {
            System.out.println(er);
        }*/
        System.out.println(rlst);
        gpuExecRules gpurl = new gpuExecRules();
        gpurl.addExecRule(rl,5);
        //System.out.println(gpurl.getgpuExecRules().get(1).getExecRules());
        List<ExecRules> gpurlst = gpurl.getgpuExecRules();
        for (ExecRules er:gpurlst) {
                System.out.println(er.getExecRules());
        }

        //正则表达式测试
        String line3 = new String("{");
        String line4 = new String("v1=v2*v3+v4+!v5");
        //String line5 = new String("5:v1=v2*v3;");
        System.out.println(line3.matches("\\**\\{"));;
        //System.out.println(line4.matches(".*\\=.*"));
        //System.out.println(line5.matches("[0-9]*\\:.*"));
        //字符串操作
        char c;
       /* Stack<String> operatorStack = new Stack<String>();
        StringTokenizer parser = new StringTokenizer(line4,"+*!()",true);
        StringBuffer postfix = new StringBuffer(line4.length());
        while (parser.hasMoreTokens()){
            String token = parser.nextToken();
            System.out.println(token);
            c = token.charAt(0);
            if((token.length() == 1) && isOperator(c)){
                while (!operatorStack.empty() && !lowerPrecedence(((String) operatorStack.peek()).charAt(0),c)) {
                    postfix.append(" ").append((String) operatorStack.pop());//栈顶高则弹出
                    System.out.println(postfix.toString()+" operator");
                }
                if(c == ')'){
                    if (operatorStack.empty()){
                        System.err.println("Parenthesed mismatched");
                    }
                    String operator1 = (String) operatorStack.pop();
                    while(operator1.charAt(0) != '('){
                        postfix.append(" ").append(operator1);
                        if(operatorStack.empty()){
                            System.err.println("Parenthesed mismatched");
                        }
                    }
                    operator1 = (String) operatorStack.pop(); //"("
                } else {
                    operatorStack.push(token);
                }
            } else if ((token.length() == 1) && isSpace(c)) {

            } else {
                postfix.append(" ").append(token);
                System.out.println(postfix.toString()+" token");
            }

        }
        System.out.println(postfix.toString());
        while(!operatorStack.empty()){
            String op = operatorStack.pop();
            if (op.equals("(")){
                System.err.println("Parentheses mismatched");
            }
            postfix.append(" ").append(op);
        }
        System.out.println(postfix.toString()+" last");*/



    }





}
