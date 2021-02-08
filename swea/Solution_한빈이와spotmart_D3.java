package swea;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_한빈이와spotmart_D3 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, best;
    static int[] snacks;


    static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        snacks = new int[N];
        StringTokenizer snackInput = new StringTokenizer(bufferedReader.readLine());
        for(int i = 0; i < N; i++) {
            snacks[i] = Integer.parseInt(snackInput.nextToken());
        }
    }

    static void combination(int cnt, int start, int total) {
        if(total > M) {
            return;
        }
        if(cnt == 2) {
            best = Math.max(best, total);
            return;
        }
        for(int i = start; i < N; i++) {
            combination(cnt+1, i+1, total + snacks[i]);
        }
    }

    static void solve(int index) throws IOException {
        best = 0;
        combination(0,0,0);
        StringBuilder sb = new StringBuilder();
        sb.append("#").append(index).append(" ").append(best == 0? -1 : best).append("\n");
        bufferedWriter.write(sb.toString());
    }
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 1; i <= T; i++) {
            input();
            solve(i);
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
