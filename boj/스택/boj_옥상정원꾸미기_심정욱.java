package boj.스택;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Stack;

public class boj_옥상정원꾸미기_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static long cnt;
    static String[] arr;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        arr = new String[N];
        Stack<Long> stack = new Stack<>();
        for(int i = 0 ; i < N; i++) {
            Long input = Long.parseLong(bufferedReader.readLine());

            while(!stack.isEmpty()) {
                if(stack.peek() <= input)
                    stack.pop();
                else
                    break;
            }
            cnt += stack.size();
            stack.push(input);

        }
        bufferedReader.close();
        System.out.println(cnt);
    }
}
