package swea;
import java.io.*;
import java.util.StringTokenizer;

public class Solution_최장증가부분수열_D3 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static int[] arr, dp;

    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        arr = new int[N];
        dp = new int[N];
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void solve() throws IOException {
        dp[0] = 1;
        for(int i = 1; i < N; i++) {
            dp[i] = 1;
            for(int j = 0; j < i; j++) {
                if(arr[j] < arr[i] && dp[i] <= dp[j]) {
                    dp[i] = dp[j] + 1;
                }
            }
        }
        int max = 0;
        for(int d : dp) {
            max = Math.max(max, d);
        }
        bufferedWriter.write(max+"\n");
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
