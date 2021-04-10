package boj.DP;

import java.io.*;
public class Main_123더하기8_15993 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static final int EVEN = 0, ODD= 1;
    private static long[][] dp = new long[100001][2];

    // 1 -> 1  : 홀 1, 짝 0
    // 2 - > 2, 1+1 : 홀1, 짝1
    // 3 -> 1+1+1, 1+2, 2+1, 3 : 홀 2, 짝2
    // 4 -> 1+1+1+1, 1+1+2, 1+2+1, 2+1+1, 2+2, 1+3, 3+1 : 홀3, 짝4
    // 5 -> 1+1+1+1+1, 1+1+2+1 * 4, 2+2+1 * 3, 3+1+1 * 3, 2+3 * 2 : 홀7, 짝6
   private static void input() throws IOException {
       N = Integer.parseInt(bufferedReader.readLine());
    }

    private static void solve() throws IOException {
        bufferedWriter.write((dp[N][1] % 1000000009) + " " + (dp[N][0] % 1000000009) + "\n");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        dp[1][EVEN] = 0;
        dp[1][ODD] = 1;
        dp[2][EVEN] = 1;
        dp[2][ODD] = 1;
        dp[3][EVEN] = 2;
        dp[3][ODD] = 2;
        for(int i = 4; i <= 100000; i++) {
            dp[i][0] = (dp[i-1][1] + dp[i-2][1] + dp[i-3][1]) % 1000000009;
            dp[i][1] = (dp[i-1][0] + dp[i-2][0] + dp[i-3][0]) % 1000000009;
        }
        for(int i = 0; i < T; i++) {
            input();
            solve();
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
