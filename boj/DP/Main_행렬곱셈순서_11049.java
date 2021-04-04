package boj.DP;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_행렬곱셈순서_11049 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static int[][] dp;
    private static Matrix[] matrices, results;

    private static class Matrix {
        int r;
        int c;

        public Matrix(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        dp = new int[N+1][N+1];
        matrices = new Matrix[N+1];
        results = new Matrix[N+1];
        for(int i = 1; i <= N; i++) {
            StringTokenizer m = new StringTokenizer(bufferedReader.readLine());
            matrices[i] = new Matrix(Integer.parseInt(m.nextToken()), Integer.parseInt(m.nextToken()));
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
    }

    private static int getMin(int start, int end) {
        if(start == end ) return 0;
        if(dp[start][end] != Integer.MAX_VALUE) {
            return dp[start][end];
        }
        for(int i = start; i < end; i++) {
            int cost = getMin(start, i) + getMin(i+1, end) + matrices[start].r * matrices[i].c * matrices[end].c;
            dp[start][end] = Math.min(cost, dp[start][end]);
        }
        return dp[start][end];
    }

    private static void solve() throws IOException {
        if(N == 1) {
            System.out.println(0);
            return;
        } else if(N == 2) {
            System.out.println(matrices[1].r * matrices[1].c * matrices[2].r);
            return;
        }
        System.out.println(getMin(1, N));
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
