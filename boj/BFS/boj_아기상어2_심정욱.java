package boj.BFS;

import java.io.*;
import java.util.*;

public class boj_아기상어2_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N,M, max;
    static int[][] graph;
    static int[] dx = {-1,-1,-1,0,0,1,1,1};
    static int[] dy = {-1,0,1,-1,1,-1,0,1};
    static int[][] counts;
    static Queue<Point> queue;

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        graph = new int[N][M];
        counts = new int[N][M];
        queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                counts[i][j] = Integer.MAX_VALUE;
                if(graph[i][j] == 1) {
                    queue.add(new Point(i,j));
                    counts[i][j] = 0;
                }
            }
        }
    }

    static void bfs() {
        while(!queue.isEmpty()) {
            Point point = queue.poll();
            int x = point.x;
            int y = point.y;
            int idx = 0;
            while(idx < 8) {
                int nx = x + dx[idx];
                int ny = y + dy[idx];
                idx++;
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if(counts[nx][ny] > counts[x][y] + 1) {
                    counts[nx][ny] = counts[x][y] + 1;
                    queue.add(new Point(nx,ny));
                    max = Math.max(counts[nx][ny], max);
                }
            }
        }
    }
    static void solve() {
        bfs();
        System.out.println(max);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }
}
