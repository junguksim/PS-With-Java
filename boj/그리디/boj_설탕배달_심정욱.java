package boj.그리디;

import java.io.*;

public class boj_설탕배달_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int min;

    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        min = Integer.MAX_VALUE;
    }

    static void solve() {
        if(N % 5 == 0) {
            System.out.println(N / 5);
            return;
        }
        for(int five = 0; five < N; five++) {
            int n = N - five * 5;
            if(n < 0) break;
            if(n % 3 == 0) {
                min = Math.min(min, five + n / 3);
            }
        }
        if(min == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }
        System.out.println(min);
    }
    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }
}
