package boj.DP;

import java.io.*;

public class boj_123더하기_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] dp;
    static void solve() throws IOException {
        int n = Integer.parseInt(bufferedReader.readLine());
        if(n <= 2) {
            bufferedWriter.write(n+"\n");
            return;
        }
        dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for(int i = 4; i <= n; i++) {
            dp[i] = dp[i-1]+dp[i-2]+dp[i-3];
        }
        bufferedWriter.write(dp[n]+"\n");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            solve();
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
