package 스택;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class 스택_10828 {
    public static Stack<Integer> stack = new Stack<>();
    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    public static void getInput() throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        switch (stringTokenizer.nextToken()) {
            case "push":
                push(Integer.parseInt(stringTokenizer.nextToken()));
                break;
            case "top":
                top();
                break;
            case "size":
                size();
                break;
            case "empty":
                empty();
                break;
            case "pop":
                pop();
        }
    }
    public static boolean isEmpty() {
        return stack.empty();
    }
    public static void push(int x) {
        stack.push(x);
    }
    public static void size() throws IOException {
        bufferedWriter.write(stack.size() + "\n");
    }
    public static void pop() throws IOException {
        if(isEmpty()) {
            bufferedWriter.write("-1\n");
        }
        else {
            bufferedWriter.write(stack.pop() + "\n");
        }
    }
    public static void top() throws IOException {
        if(isEmpty()) {
            bufferedWriter.write("-1\n");
        }
        else {
            bufferedWriter.write( stack.peek()+ "\n");
        }
    }
    public static void empty() throws IOException {
        if(isEmpty()) {
            bufferedWriter.write("1\n");
        }
        else {
            bufferedWriter.write("0\n");
        }
    }
    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0; i < N; i++) {
            getInput();
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
