package boj.DP;

import java.math.BigInteger;
import java.util.Scanner;

public class boj_피보나치수4_심정욱 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        if(n <= 1) {
            System.out.println(n);
            return;
        }
        BigInteger[] dp = new BigInteger[n+1];
        dp[0] = new BigInteger("0");
        dp[1] = new BigInteger("1");
        for(int i = 2; i <= n; i++) {
            dp[i] = dp[i-1].add(dp[i-2]);
        }
        System.out.println(dp[n]);
    }
}
