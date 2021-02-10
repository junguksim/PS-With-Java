package boj.스택;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_오큰수_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] arr;

    static void solve() throws IOException {
        Stack<Integer> stack = new Stack<>();
        int[] ans = new int[N];
        for(int j = N - 1; j >= 0; j--) {
            while(!stack.isEmpty() && stack.peek() <= arr[j]) {
                stack.pop();
            }
            if(stack.isEmpty()) {
                ans[j] = -1;
            } else {
                ans[j] = stack.peek();
            }
            stack.push(arr[j]);
        }
        StringBuilder sb = new StringBuilder();
        for(int k : ans) {
            sb.append(k + " ");
        }
        bufferedWriter.write(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        arr = new int[N];
        StringTokenizer nums = new StringTokenizer(bufferedReader.readLine());
        for(int i = 0 ; i < N; i++) {
            arr[i] = Integer.parseInt(nums.nextToken());
        }
        solve();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
