package boj.구현;

import java.io.*;
import java.util.*;

public class Main_한줄로서기_1138 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static int[] tall;
    private static List<Integer> ans = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        tall = new int[N+1];
        ans = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            tall[i] = Integer.parseInt(st.nextToken());
        }

    }

    private static void solve() throws IOException {
        for(int i = N; i >= 1; i--) {
            ans.add(tall[i], i);
        }
        for(int k : ans) {
            bufferedWriter.write(k + " ");
        }
    }
}
