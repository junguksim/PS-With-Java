package boj.문자열;

import java.io.*;
import java.util.StringTokenizer;

public class Main_팰린드롬_10942 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static boolean[][] dp;
    private static int N, M;
    private static int[] arr;


    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        arr = new int[N+1];
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void solve() throws IOException {
        dp = new boolean[N+1][N+1];
        for(int f = 1; f <= N; f++) {
            dp[f][f] = true;
        }
        for(int i = 1; i <= N-1; i++) {
            if(arr[i] == arr[i+1]) dp[i][i+1]= true;
        }
        for(int i = 2; i < N; i++) {
            for(int j = 1; j <= N-i; j++) {
                if(arr[j] == arr[j+i] && dp[j+1][j+i-1]) {
                    dp[j][j+i] = true;
                }
            }
        }
        M = Integer.parseInt(bufferedReader.readLine());

        for(int i = 0; i < M; i++) {
            StringTokenizer op = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(op.nextToken());
            int to = Integer.parseInt(op.nextToken());
            bufferedWriter.write((dp[from][to] ? 1 : 0) + "\n");
        }
    }
}