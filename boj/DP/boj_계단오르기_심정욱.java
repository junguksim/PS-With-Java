package boj.DP;

import java.io.*;

public class boj_계단오르기_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(bufferedReader.readLine());
        int[] floor = new int[n+1];
        for(int i = 1; i<= n; i++) {
            floor[i] = Integer.parseInt(bufferedReader.readLine());
        }
        if(n == 1) {
            System.out.println(floor[1]);
            return;
        } else if(n == 2) {
            System.out.println(floor[1] + floor[2]);
            return;
        }
        int[] dp = new int[n+1];
        dp[1] = floor[1];
        dp[2] = floor[1] + floor[2];
        for(int i = 3; i <= n; i++) {
            int n1 = dp[i-2] + floor[i]; // 2개 이전 칸과 해당 칸
            int n3 = dp[i-3] +floor[i-1] + floor[i]; // 1개 이전 칸과 해당 칸
            dp[i] = Math.max(n1, n3);
        }
        System.out.println(dp[n]);
    }
}
