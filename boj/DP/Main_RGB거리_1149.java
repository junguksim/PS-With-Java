package boj.DP;
import java.io.*;
import java.util.StringTokenizer;

public class Main_RGB거리_1149 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, min;
    private static int[][] costs;
    private static int[][] dp;

    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        costs = new int[N][3];
        dp = new int[N][3];
        min = Integer.MAX_VALUE;
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < 3; j++) {
                costs[i][j] = Integer.parseInt(st.nextToken());
                if(i == 0) {
                    dp[0][j] = costs[0][j];
                }
            }
        }
    }

    private static void solve() throws IOException {
        for(int i = 1; i < N; i++) {
            dp[i][0] = costs[i][0] + Math.min(dp[i-1][1], dp[i-1][2]);
            dp[i][1] = costs[i][1] + Math.min(dp[i-1][0], dp[i-1][2]);
            dp[i][2] = costs[i][2] + Math.min(dp[i-1][0], dp[i-1][1]);
        }
        min = Math.min(Math.min(dp[N-1][0], dp[N-1][1]), dp[N-1][2]);
        System.out.println(min);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
