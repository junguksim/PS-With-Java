package 스택;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class 괄호_9012 {
    public static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


    public static String isVPS() throws IOException {
        Stack<String> stringStack = new Stack<>();
        char[] psArray = bufferedReader.readLine().toCharArray();
        for(char ps : psArray) {
            if(ps == '(') {
                stringStack.push("(");
            }
            else if(ps == ')' && stringStack.size() > 0 &&stringStack.peek().equals("(")) {
                stringStack.pop();
            }
            else {
                stringStack.push(")");
            }
        }
        if(stringStack.size() > 0) {
            return "NO\n";
        }
        else return "YES\n";
    }
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            bufferedWriter.write(isVPS());
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
