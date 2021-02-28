package boj.구현;
import java.io.*;
import java.util.StringTokenizer;

public class Main_자리배정_10157 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int C, R, K, x, y, idx, value;
    static int[][] map;
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,1,0,-1};
    static void input() throws IOException {
        StringTokenizer cr = new StringTokenizer(bufferedReader.readLine());
        C = Integer.parseInt(cr.nextToken());
        R = Integer.parseInt(cr.nextToken());
        K = Integer.parseInt(bufferedReader.readLine());

        x = R;
        y = 1;
        idx = 0;
        value = 2;
        map = new int[R+1][C+1];
        map[R][1] = 1;
    }

    static void solve() throws IOException {
        if(R*C < K) {
            bufferedWriter.write("0");
            return;
        }
        while (value <= K) {
            map[x][y] = value;
            int nx = x + dx[idx];
            int ny = y + dy[idx];

            if(nx < 1 || ny < 1 || nx > R || ny > C || map[nx][ny] != 0) {
                idx++;
                if (idx == 4) idx = 0;
                nx = x + dx[idx];
                ny = y + dy[idx];
            }
            x = nx;
            y = ny;

            value++;
        }
        bufferedWriter.write(y+ " " + (R+1-x));
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.close();
    }
}
