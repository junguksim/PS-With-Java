package boj.DP;

import java.util.Arrays;
import java.util.Scanner;

public class boj_1로만들기_심정욱 {
    static int cnt;
    static int[] dp;

    static void solve(int n) {
        if(n == 1) {
            System.out.println(cnt);
            return;
        }
        if(n == 2) {
            System.out.println(cnt + 1);
            return;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if(n <= 1) {
            System.out.println(0);
            return;
        }
        dp = new int[n+1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        dp[1] = 0;
        dp[2] = 1;
        int num = 2;
        while(num <= n) {
            if(num % 3 == 0 && num % 2 == 0) {
                dp[num] = Math.min(Math.min(dp[num / 3] + 1, dp[num / 2] + 1), dp[num-1] + 1);
            } else if(num % 3 == 0) {
                dp[num] = Math.min(dp[num / 3] + 1, dp[num-1] + 1);
            } else if(num % 2 == 0) {
                dp[num] = Math.min(dp[num / 2] + 1, dp[num-1] + 1);
            } else {
                dp[num] = dp[num-1]+1;
            }
            num++;
        }
        System.out.println(dp[n]);
    }
}
