package swea;

import java.io.*;
import java.util.Stack;

public class Solution_괄호짝짓기_D4 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


    static void solve(int idx) throws IOException {
        int len = Integer.parseInt(bufferedReader.readLine());
        Stack<Character> parentheses = new Stack<>();
        Stack<Character> curlyBrackets = new Stack<>();
        Stack<Character> brackets = new Stack<>();
        Stack<Character> angelBrackets = new Stack<>();
        char[] input = bufferedReader.readLine().toCharArray();
        for(int i = 0 ; i < len; i++) {
            switch (input[i]) {
                case '(':
                    parentheses.push(input[i]);
                    break;
                case ')':
                    if(!parentheses.isEmpty()) {
                        parentheses.pop();
                        break;
                    }
                case '{':
                    curlyBrackets.push(input[i]);
                    break;
                case '}':
                    if(!curlyBrackets.isEmpty()) {
                        curlyBrackets.pop();
                        break;
                    }
                case '[':
                    brackets.push(input[i]);
                    break;
                case ']':
                    if(!brackets.isEmpty()) {
                        brackets.pop();
                        break;
                    }
                case '<':
                    angelBrackets.push(input[i]);
                    break;
                case '>':
                    if(!angelBrackets.isEmpty()) {
                        angelBrackets.pop();
                        break;
                    }
            }
        }
        if(parentheses.isEmpty() && curlyBrackets.isEmpty() && brackets.isEmpty() && angelBrackets.isEmpty()) {
            bufferedWriter.write("#"+idx +" "+1+"\n");
        } else {
            bufferedWriter.write("#"+idx+" "+0+"\n");
        }
    }

    public static void main(String[] args) throws IOException {
        for(int i = 0 ; i < 10; i++) {
            solve(i+1);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
