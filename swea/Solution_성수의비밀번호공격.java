package swea;
import java.io.*;
import java.util.StringTokenizer;

public class Solution_성수의비밀번호공격 {
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int N,M,K;
    private static final long P = 1000000007;


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
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        K = 0;
    }

    private static long power(long x, long y) {
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
        return power(n, p-2);
    }

    private static long nCr(long n, long r, long p) {
        if( r == 0 || r == N) {
            return 1L;
        } else if(K == 1 || K == N-1) {
            return N % P;
        }
        long[] fact = new long[101];
        fact[0] = 1;
        for(int i = 1; i < fact.length; i++) {
            fact[i] = fact[i-1]*i % P;
        }
        if(N < P) {
            return (fact[N] * modInverse(fact[K], P) % P * modInverse(fact[(N-K)], P) % P) % P;
        } else {
            long ret = 1;
            while (N > 0 || K > 0) {
                long a = N % P;
                long b = K % P;
                if(a < b) ret=0;
                if(ret == 0) break;
                ret *= fact[(int)a];
                ret %= P;

                ret *= modInverse((fact[(int)b] * fact[(int)a - (int)b]) % P, P);
                ret %= P;

                N /= P;
                K /= P;
            }
            return ret;
        }
    }

    private static void solve(int t) throws IOException {
        long sum = 0L;
        for(int i = 0 ; i < N; i++) {
            K = i;
            System.out.println(((power(-1, i) % P) +" " +( nCr(N, K, P) % P) +" "+ (power(N-K, M) % P) ));
            sum += ((power(-1, i) % P) * (nCr(N, i, P) % P) * (power(N-K, M) % P) )% P;
            System.out.println(sum);
        }
        sum %= P;
        if(sum < 0) sum += P;
        bufferedWriter.write("#"+t+" "+sum+"\n");
    }
}