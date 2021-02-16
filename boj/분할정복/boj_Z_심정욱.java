package boj.분할정복;

import java.io.*;
import java.util.StringTokenizer;

public class boj_Z_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N,r,c;
    static int cnt = 0;

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
    }


    static void devideAndConquer(int n, int sx, int sy) {
        if(n == 0) {
            if(sx == r && sy == c) {
                System.out.println(cnt);
                return;
            }
            return;
        }
        int middle = (int) Math.pow(2, n-1);
        int end = (int) Math.pow(2, n);
        if(r >= sx && r < sx + middle && c >= sy && c < sy + middle) {
            devideAndConquer(n-1, sx,sy );
        } else if(r >= sx && r < sx + middle && c >= sy + middle && c < sy + end) {
            cnt += (middle * middle);
            devideAndConquer(n-1, sx, sy + middle);
        } else if(r >= sx + middle && r < sx + end && c >= sy && c < sy + middle) {
            cnt += (middle * middle)* 2;
            devideAndConquer(n-1, sx + middle, sy);
        } else{
            cnt += (middle * middle) * 3;
            devideAndConquer(n-1, sx+ middle, sy+ middle);
        }
    }

    static void solve() {
        devideAndConquer(N, 0, 0);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }
}
