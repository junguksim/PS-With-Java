package boj.DP;

import java.io.*;
import java.util.StringTokenizer;

public class boj_다리놓기_심정욱 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int[][] dp;

    static void solve() throws IOException {
        StringTokenizer NM = new StringTokenizer(bufferedReader.readLine());
        int N = Integer.parseInt(NM.nextToken());
        int M = Integer.parseInt(NM.nextToken());
        dp = new int[N+1][M+1];
        for(int n = 2; n <= N; n++) {
            dp[n][1] = 0;
        }
        for(int m = 1; m <= M; m++) {
            dp[1][m] = m;
        }
        for(int i = 2; i <= N; i++) {
            for(int j = 2; j <= M; j++) {
                dp[i][j] = dp[i][j-1] + dp[i-1][j-1];
            }
        }
        bufferedWriter.write(dp[N][M] + "\n");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            solve();
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
