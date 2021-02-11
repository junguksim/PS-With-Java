package boj.BFS;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_유기농배추_심정욱 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int M, N, K, ans;
    static int[][] field;
    static int[][] direct = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static boolean[][] visited;

    static void input() throws IOException {
        StringTokenizer MNK = new StringTokenizer(bufferedReader.readLine());
        M = Integer.parseInt(MNK.nextToken());
        N = Integer.parseInt(MNK.nextToken());
        K = Integer.parseInt(MNK.nextToken());
        field = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0 ; i < K; i++) {
            StringTokenizer xy = new StringTokenizer(bufferedReader.readLine());
            int x = Integer.parseInt(xy.nextToken());
            int y = Integer.parseInt(xy.nextToken());
            field[y][x] = 1;
        }
    }

    static void bfs(int sx, int sy) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {sx, sy});
        visited[sy][sx] = true;
        while(!queue.isEmpty()) {
            int x = queue.peek()[0];
            int y = queue.peek()[1];
            queue.remove();

            for(int i = 0 ; i < 4; i++) {
                int nx = x + direct[i][0];
                int ny = y + direct[i][1];

                if(nx < 0 || ny < 0 || nx >= M || ny >= N) {
                    continue;
                }
                if(visited[ny][nx] || field[nx][ny] == 0) {
                    continue;
                }
                queue.add(new int[] {nx, ny});
                visited[ny][nx] = true;
            }
        }
    }

    static void solve() throws IOException {
        ans = 0;
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < M ;j++) {
                if(field[i][j] == 1) {
                    if(visited[i][j])
                        continue;
                    bfs(j, i);
                    ans++;
                }
            }
        }
        bufferedWriter.write(ans+"\n");
    }
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            input();
            solve();
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
