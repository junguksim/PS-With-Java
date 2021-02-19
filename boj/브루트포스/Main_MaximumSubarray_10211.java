package boj.브루트포스;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_MaximumSubarray_10211 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, max;
    static int[] X;
    static int[][] dp;
    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        X = new int[N];
        dp = new int[N][2];
        max = Integer.MIN_VALUE;
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        for(int i = 0 ; i < N; i++) {
            X[i] = Integer.parseInt(st.nextToken());
            max = Math.max(X[i], max);
        }
    }

    static void solve() throws IOException {
        dp[0] = new int[] {X[0],X[0]};
        for(int i = 0; i < N - 1; i++) {
            dp[i][0] = Math.max(X[i], dp[i][0]);
            dp[i][1] = Math.max(dp[i][1], X[i]);
            for(int j = i + 1; j < N; j++) {
                dp[j] = new int[]{dp[j-1][0] + X[j], Integer.MIN_VALUE};
            }
        }
        for(int i = 0 ; i < N; i++) {
            //System.out.println(dp[i][0] + " " + dp[i][1]);
            max = Math.max(Math.max(max, dp[i][0]),dp[i][1]);
        }
        bufferedWriter.write(max + "\n");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            input();
            solve();
        }
        bufferedReader.close();
        bufferedWriter.close();
    }

}
