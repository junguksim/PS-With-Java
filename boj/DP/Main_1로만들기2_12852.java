package boj.DP;
import java.io.*;

public class Main_1로만들기2_12852 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    static void solve() throws IOException{
        int N = Integer.parseInt(bufferedReader.readLine());
        if(N == 1) {
            System.out.println(0);
            System.out.println(1);
            return;
        }
        int[] dp = new int[N+1];
        dp[1] = 0;
        dp[2] = 1;
        int i = 3;
        while(i <= N) {
            int minusOneResult = dp[i-1] + 1;
            int divByThreeResult = Math.min(minusOneResult, dp[i/3] + 1);
            int divByTwoResult = Math.min(minusOneResult, dp[i/2] + 1);
            if(i % 3 == 0) {
                if(i % 2 == 0) {
                    dp[i] = Math.min(divByThreeResult,divByTwoResult );
                }
                else {
                    dp[i] = divByThreeResult;
                }
            }
            else if(i % 2 == 0) {
                dp[i] = divByTwoResult;
            } else {
                dp[i] = minusOneResult;
            }
            i++;
        }
        int n = N;
        bufferedWriter.write(dp[N] + "\n");
        bufferedWriter.write(N + " ");
        while(n > 1) {
            if(n % 3 == 0 && dp[n / 3] + 1 < dp[n-1] + 1) {
                n /= 3;
                bufferedWriter.write(n + " ");
            } else if(n % 2 == 0 && dp[n / 2] + 1 < dp[n-1] + 1) {
                n /= 2;
                bufferedWriter.write(n + " ");
            } else {
               n -= 1;
               bufferedWriter.write(n + " ");
            }
        }


    }

    public static void main(String[] args) throws IOException {
        solve();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
