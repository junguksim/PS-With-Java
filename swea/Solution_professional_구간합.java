package swea;
import java.io.*;
import java.util.StringTokenizer;

public class Solution_professional_구간합 {
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static long A,B;
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
        StringTokenizer ab = new StringTokenizer(bufferedReader.readLine());
        A = Long.parseLong(ab.nextToken());
        B = Long.parseLong(ab.nextToken());
    }

    private static long power(long x, long y) {
        long res = 1L;
        while (y > 0) {
            if(y % 2 == 1) res = (res * x);
            y = y >> 1;
            x = (x * x);
        }
        return res;
    }

    private static void solve(int t) throws IOException {
        long point = 1;
        long[] ans=  new long[10];
        while (A <= B) {
            while (B % 10 != 9 && A <= B) {
                cal(B, ans, point);
                B--;
            } // 32, 31, 30...
            if(B < A) break;
            while (A % 10 != 0 && A <= B) {
                cal(A, ans, point);
                A++;
            } // 5+6+7+8+9 ..

            A /= 10;
            B /= 10;
            for(int i = 0;i < 10; i++) {
                ans[i] += (B - A +1 ) * point;
            }
            point *= 10;
        }
        long sum = 0;
        for(int i = 0 ; i < 10; i++) {
            sum += (ans[i] * i);
        }
        bufferedWriter.write("#"+t+" " + sum + "\n");
    }

    private static void cal(long x, long[] ans, long point) {
        while (x > 0) {
            String s = String.valueOf(x);
            int xx = s.charAt(s.length()-1)-'0';
            ans[xx] += point;
            x /= 10;
        }
    }
}