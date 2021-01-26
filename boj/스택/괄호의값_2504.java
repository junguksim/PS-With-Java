package 스택;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 괄호의값_2504 {
    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        int sum = 0;
        int value = 1;
        int leftRoundCount = 0;
        int leftAngularCount = 0;
        Stack<String> stack = new Stack<>();
        boolean isWrong = false;
        String[] strings = bufferedReader.readLine().split("");
        for(int i = 0 ; i < strings.length; i++) {
            if(strings[i].equals("(")) {
                stack.push(strings[i]);
                value *= 2;
            }
            if(strings[i].equals("[") ) {
                stack.push(strings[i]);
                value *= 3;
            }
            if(strings[i].equals(")")) {
                if(stack.empty() || !stack.peek().equals("(")) {
                    isWrong = true;
                    break;
                }
                if(strings[i-1].equals("(")) {
                    sum += value;
                }
                stack.pop();
                value /= 2;
            }
            else if(strings[i].equals("]")){
                if(stack.empty() || !stack.peek().equals("[")) {
                    isWrong = true;
                    break;
                }
                if(strings[i-1].equals("[")) {
                    sum += value;
                }
                stack.pop();
                value /= 3;
            }

        }
        if(isWrong || !stack.empty()) {
            System.out.println(0);
        }
        else {
            System.out.println(sum);
        }
    }
}
