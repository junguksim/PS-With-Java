package boj.수학;

import java.io.*;
import java.util.StringTokenizer;

public class Main_이항계수구하기_16134 {
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int N,R;
    private static long P = 1000000007;
    private static long[] fact;
    public static void main(String[] args) throws IOException  {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.close();
    }

    private static void input() throws IOException {
        StringTokenizer nr = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nr.nextToken());
        R = Integer.parseInt(nr.nextToken());
        fact = new long[N+1];
        fact[0] = 1;
        for(int i = 1; i <= N; i++) {
            fact[i] = (fact[i-1] * i) % P;
        }
    }

    private static long power(long x, long y) {
        if(y == 0) return 1;
        if(y % 2 == 1) return (power(x, y-1) * x) % P;
        long half = power(x, y/2) % P;
        return half * half % P;
    }

    private static void solve() throws IOException {
        long ans = 0;
        long[] inverse = new long[N+1];
        inverse[N] = power(fact[N], P-2);
        for(int i = N-1; i >= 0; i--) {
            inverse[i] = inverse[i+1] * (i+1) % P;
        }
        long top = fact[N];
        long bottom = (inverse[N-R] * inverse[R]) % P;
        ans = top * bottom % P;
        System.out.println(ans);
    }
}
