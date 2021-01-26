package 스택;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class 제로_10773 {
    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    public static Stack<Integer> stack = new Stack<>();

    public static void getInput() throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int incomeNum = Integer.parseInt(stringTokenizer.nextToken());
        if(incomeNum == 0 && stack.size() > 0) {
            stack.pop();
        } else {
            stack.push(incomeNum);
        }
    }

    public static int getSumOfStack() {
        int sum = 0;
        while(stack.size() > 0) {
            sum += stack.pop();
        }
        return sum;
    }
    public static void main(String[] args) throws IOException {
        int K = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0; i < K; i++) {
            getInput();
        }
        bufferedWriter.write(getSumOfStack() + "\n");
        bufferedWriter.close();
        bufferedReader.close();
    }
}
