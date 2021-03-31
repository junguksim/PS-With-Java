package boj.DP;
import java.io.*;
import java.util.Arrays;

public class Main_BOJ거리_12026 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static final int MAX = 987654321;
    private static int N;
    private static char[] street;
    private static int[] dp;
    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        street = new char[N];
        street = bufferedReader.readLine().toCharArray();
        dp = new int[N];
        Arrays.fill(dp, MAX);
    }

    private static void solve() throws IOException {
        dp[0] = 0;
        for(int i = 0; i < N-1; i++) {
            int now = i;
            char nowBlock = street[now];
            if(nowBlock == 'B') {
                for(int j = i +1; j < N; j++) {
                    int next = j;
                    char nextBlock = street[next];
                    if(nextBlock == 'O') {
                        dp[next] = Math.min(dp[next], dp[now] + (next-now) * (next-now));
                    }
                }
            } else if(nowBlock == 'O') {
                for(int j = i +1; j < N; j++) {
                    int next = j;
                    char nextBlock = street[next];
                    if(nextBlock == 'J') {
                        dp[next] = Math.min(dp[next], dp[now] + (next-now) * (next-now));
                    }
                }
            } else {
                for(int j = i +1; j < N; j++) {
                    int next = j;
                    char nextBlock = street[next];
                    if(nextBlock == 'B') {
                        dp[next] = Math.min(dp[next], dp[now] + (next-now) * (next-now));
                    }
                }
            }
        }
        if(dp[N-1] == MAX) {
            System.out.println("-1\n");
        } else {
            System.out.println(dp[N-1]);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
