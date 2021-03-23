package boj.DP;

import java.util.Scanner;

public class Main_1로만들기_1463 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int X = sc.nextInt();
        if(X <= 2) {
            System.out.println(X-1);
            return;
        }
        int[] dp = new int[X+1];
        dp[1] = 0;
        dp[2] = 1;

        int min = Integer.MAX_VALUE;
        for(int i = 3; i <= X ;i++) {
            if(i % 2 == 0 && i % 3 == 0) {
                min = Math.min(Math.min(dp[i / 2], dp[i / 3]), dp[i-1]) + 1;
            } else if(i % 2 == 0) {
                min = Math.min(dp[i/2], dp[i-1])+1;
            } else if(i % 3 == 0) {
                min = Math.min(dp[i/3], dp[i-1]) + 1;
            } else {
                min = dp[i-1] + 1;
            }
            dp[i] = min;
        }
        System.out.println(dp[X]);
    }
}
