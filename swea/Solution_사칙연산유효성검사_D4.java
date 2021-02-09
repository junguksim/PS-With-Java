package swea;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_사칙연산유효성검사_D4 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, ans;

    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        ans = 0;
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(st.nextToken());
            char op = st.nextToken().charAt(0);
            if(st.countTokens() >= 2) {
                if(op == '+' || op == '*' || op == '/' || op == '*') {
                    ans = 1;
                }
            } else {
                if(op == '+' || op == '*' || op == '/' || op == '*') {
                    ans = 0;
                }
            }
        }
    }

    static void solve(int idx) throws IOException {
        bufferedWriter.write("#"+idx+" "+ans+"\n");
    }

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 10; i++) {
            input();
            solve(i);
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
