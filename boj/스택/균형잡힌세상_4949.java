package 스택;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;

public class 균형잡힌세상_4949 {
    public static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    public static String isBalanced() throws IOException {
        String[] chars = bufferedReader.readLine().split("");
        if(chars[0].equals(".")) {
            return "end";
        }
        Stack<String> stack = new Stack<>();
        for(String ch : chars) {
            int isPs = "()[]".indexOf(ch);
            if(isPs >= 0) {
                if(stack.size() > 0) {
                    if((stack.peek()).equals("(") && ch.equals(")"))
                        stack.pop();
                    else if((stack.peek()).equals("[") && ch.equals("]"))
                        stack.pop();
                    else
                        stack.push(ch);
                }
                else {
                    stack.push(ch);
                }
            }
        }
        if(stack.size() > 0) {
            return "no\n";
        }
        else return "yes\n";
    }
    public static void main(String[] args) throws IOException {
        while(true) {
            String result = isBalanced();
            if(result.equals("end")) {
                break;
            }
            bufferedWriter.write(result);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
