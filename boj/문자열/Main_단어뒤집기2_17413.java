package boj.문자열;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_단어뒤집기2_17413 {
    public static void printStack(BufferedWriter bw, Stack<Character> stack) throws IOException {
        while(!stack.isEmpty()) {
            bw.write(stack.pop());
        }
    }

    public static void main(String[] args) throws IOException {
        Stack<Character> stack = new Stack<>();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        String s = bufferedReader.readLine();
        boolean isInTag = false;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '<') {
                isInTag = true;
                printStack(bufferedWriter, stack);
                bufferedWriter.write(s.charAt(i));
            } else if(s.charAt(i) == '>') {
                isInTag = false;
                bufferedWriter.write(s.charAt(i));
            } else if(isInTag) {
                bufferedWriter.write(s.charAt(i));
            } else {
                if(s.charAt(i) == ' ') {
                    printStack(bufferedWriter, stack);
                    bufferedWriter.write(s.charAt(i));
                } else {
                    stack.push(s.charAt(i));
                }
            }
        }
        printStack(bufferedWriter, stack);
        bufferedWriter.flush();
        bufferedWriter.close();
        bufferedReader.close();
    }


}
