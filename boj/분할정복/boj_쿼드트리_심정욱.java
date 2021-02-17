package boj.분할정복;

import java.io.*;

public class boj_쿼드트리_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder result = new StringBuilder();
    static int N, cnt, nums;
    static char[][] map;

    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        map = new char[N][N];
        for(int i = 0 ; i <N;i++) {
            map[i] = bufferedReader.readLine().toCharArray();
        }
    }

    static void devide(int n,int sx, int sy) {
        //System.out.printf("%d %d %d\n", n, sx, sy);
        if(n == 1) {
            result.append(map[sx][sy]);
            return;
        }
        boolean isSame = true;
        for(int i = sx; i < sx + n; i++) {
            for(int j = sy; j < sy + n; j++) {
                if(map[i][j] != map[sx][sy]) {
                    isSame = false;
                    break;
                }
            }
        }
        if(isSame) {
            result.append(map[sx][sy]);
            return;
        }
        int middle = n / 2;
        result.append("(");
        devide(n/2, sx, sy);
        devide(n/2, sx, sy + middle);
        devide(n/2, sx + middle, sy);
        devide(n/2, sx + middle, sy + middle);
        result.append(")");
    }

    static void solve() {
        devide(N,0,0);
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }
}
