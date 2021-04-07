package boj.DP;
import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;

public class Main_이친수찾기_2201 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static long[] lenDp;
    private static long K, nth, len;
    private static final int MAX = 90;
    private static long[][] dp;
    //1, 10, 100, 101, 1000, 1001, 1010, 10000, 10001, 10010, 10100, 10101
    // 1 의 자리 1개, 10의 자리 1개, 100의 자리 2개, 1000의 자리 3개, 10000의 자리 5개...

    private static long getCount(int num) {
        if(num <= 1) return num;
        if(lenDp[num] > 0) return lenDp[num];
        return lenDp[num] = getCount(num - 1) + getCount(num - 2);
    }

    private static void getNth() {
        long sum = 0;
        for(int i = 1; i < MAX; i++) {
            if(sum < K) sum += getCount(i);
            else {
                K -= (sum - lenDp[i-1]);
                len = i - 1;
                nth = K;
                break;
            }
        }
    }

    private static long getResult(int length, int last) {
        if(length == len) return dp[length][last] = 1;
        if(dp[length][last] > 0) return dp[length][last];
        if(last == 1) dp[length][last] += getResult(length + 1, 0);
        else dp[length][last] += (getResult(length + 1, 0) + getResult(length + 1, 1));
        return dp[length][last];
    }

    private static void print(int length, int last) throws IOException {
        bufferedWriter.write(last + "");
        if(length == len) {
            bufferedWriter.write("\n");
            return;
        }
        if(last == 0 && dp[length + 1][0] < nth) {
            nth -= dp[length + 1][0];
            print(length + 1, 1);
        } else print(length + 1, 0);
    }

    public static void main(String[] args) throws IOException {
        K = Long.parseLong(bufferedReader.readLine());
        lenDp = new long[MAX];
        dp = new long[MAX][2];
        getNth();
        getResult(1,1);
        print(1,1);
        bufferedWriter.close();
        bufferedReader.close();
    }
}
