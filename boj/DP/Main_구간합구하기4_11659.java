package boj.DP;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_구간합구하기4_11659 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N,M;
    private static int[] arr, dp;

    private static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        arr = new int[N+1];
        dp = new int[N+1];
        StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(line.nextToken());
            dp[i] = dp[i-1] + arr[i];
        }
    }

    private static void solve() throws IOException {
        for(int i = 0; i < M; i++) {
            StringTokenizer section = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(section.nextToken()) - 1;
            int to = Integer.parseInt(section.nextToken());
            bufferedWriter.write((dp[to] - dp[from]) + "\n");
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
