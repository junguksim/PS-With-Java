package swea;
import java.io.*;
import java.util.StringTokenizer;

public class Solution_이항계수구하기_D5 {
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static long N,R;
    private static int P;
    public static void main(String[] args) throws IOException  {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 1; i <= T; i++) {
            input();
            solve(i);
        }
        bufferedReader.close();
        bufferedWriter.close();
    }

    private static void input() throws IOException {
        StringTokenizer nr = new StringTokenizer(bufferedReader.readLine());
        N = Long.parseLong(nr.nextToken());
        R = Long.parseLong(nr.nextToken());
        P = Integer.parseInt(nr.nextToken());
    }

    private static long power(long x, long y, long p) {
        long res = 1L;
        x = x % P;
        while (y > 0) {
            if(y % 2 == 1) res = (res * x) % P;
            y = y >> 1;
            x = (x * x) % P;
        }
        return res;
    }

    private static long modInverse(long n, long p) {
        return power(n, p-2, p);
    }

    private static long nCr(long n, long r, long p) {
        if( r == 0 || r == N) {
            return 1L;
        } else if(R == 1 || R == N-1) {
            return N % P;
        }
        long[] fact = new long[P+1];
        fact[0] = 1;
        for(int i = 1; i < fact.length; i++) {
            fact[i] = fact[i-1]*i % P;
        }
        if(N < P) {
            return (fact[(int)N] * modInverse(fact[(int)R], P) % P * modInverse(fact[(int)(N-R)], P) % P) % P;
        } else {
            long ret = 1;
            while (N > 0 || R > 0) {
                long a = N % P;
                long b = R % P;
                if(a < b) ret=0;
                if(ret == 0) break;
                ret *= fact[(int)a];
                ret %= P;

                ret *= modInverse((fact[(int)b] * fact[(int)a - (int)b]) % P, P);
                ret %= P;

                N /= P;
                R /= P;
            }
            return ret;
        }
    }

    private static void solve(int t) throws IOException {
        bufferedWriter.write("#"+t+" "+nCr(N,R,P)+"\n");
    }
}