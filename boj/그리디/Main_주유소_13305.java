package boj.그리디;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_주유소_13305 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static long[] costs;
    private static long[] distances;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        costs = new long[N];
        distances = new long[N-1];
        StringTokenizer d = new StringTokenizer(bufferedReader.readLine());
        for(int i = 0; i < N-1; i++) {
            distances[i] = Integer.parseInt(d.nextToken());
        }
        StringTokenizer c = new StringTokenizer(bufferedReader.readLine());
        for(int i = 0 ; i < N; i++) {
            costs[i] = Integer.parseInt(c.nextToken());
        }

    }

    private static void solve() throws IOException {
        long cost = 0;
        int now = 0;
        while (now < N - 1) {
            cost += costs[now] * distances[now];
            int next = now + 1;
            while (next < N -1 && costs[now] < costs[next]) {
                cost += costs[now] * distances[next];
                next++;
            }
            now = next;
        }
        System.out.println(cost);
    }
}