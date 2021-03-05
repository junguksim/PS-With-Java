package boj.DP;
import java.io.*;
import java.util.StringTokenizer;

public class Main_구간합구하기_11660 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[][] map, dp;
    static Op[] ops;

    static class Op {
        int x1;
        int y1;
        int x2;
        int y2;

        public Op(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    /**
     * (0,0) 부터 (N-1,N-1) 까지
     * x-1,y + x,y
     *
     * @throws IOException
     */

    static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        map = new int[N+1][N+1];
        dp = new int[N+1][N+1];
        ops = new Op[M];
        for(int i = 1 ; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 1 ; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + map[i][j];
            }
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            ops[i] = new Op(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }

    static void solve() {
        for(int i = 0 ; i < M; i++) {
            Op op = ops[i];
            System.out.println(dp[op.x2][op.y2] - dp[op.x2][op.y1-1] - dp[op.x1-1][op.y2] + dp[op.x1-1][op.y1-1]);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }
}
