package boj.DP;

import java.io.*;
import java.util.Arrays;

public class boj_피보나치함수_심정욱 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        int T =Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            solve();
        }
        bufferedWriter.close();
        bufferedReader.close();
    }

    private static void solve() throws IOException {
        int N = Integer.parseInt(bufferedReader.readLine());
        if(N == 0) {
            bufferedWriter.write("1 0\n");
        } else if(N == 1) {
            bufferedWriter.write("0 1\n");
        } else {
            dp = new int[2][N+1];
            dp[0][0] = 1;
            dp[0][1] = 0;
            dp[1][0] = 0;
            dp[1][1] = 1;
            for(int i = 2; i <= N; i++) {
                dp[0][i] = dp[0][i-1] + dp[0][i-2];
                dp[1][i] = dp[1][i-1] + dp[1][i-2];
            }
            bufferedWriter.write(dp[0][N]+ " "+ dp[1][N]+"\n");
        }
    }
}
