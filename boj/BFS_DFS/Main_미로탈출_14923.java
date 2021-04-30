package boj.BFS_DFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_미로탈출_14923 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M, ans;
    private static int[][] map;
    private static Node start, end;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};

    private static class Node {
        int x;
        int y;
        int d;

        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    private static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        start = new Node(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, 0);
        StringTokenizer ed = new StringTokenizer(bufferedReader.readLine());
        end = new Node(Integer.parseInt(ed.nextToken()) - 1, Integer.parseInt(ed.nextToken()) - 1, 0);
        map = new int[N][M];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < M; j++) {
                map[i][j] = Integer.parseInt(line.nextToken());
            }
        }
        ans = Integer.MAX_VALUE;
    }
    private static void bfs(boolean[][][] visited) {
        visited[start.x][start.y][0] = true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Node s = queue.poll();
            int sx = s.x;
            int sy = s.y;
            int sd = s.d;
            if(sx == end.x && sy == end.y) {
                ans = Math.min(sd, ans);
                continue;
            }
            for(int i = 0 ; i < 4; i++) {
                int nx = sx + dx[i];
                int ny = sy + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visited[sx][sy][0]) {
                    if(visited[nx][ny][0]) continue;
                    if(map[nx][ny] == 1) {
                        visited[nx][ny][1] = true;
                    } else {
                        visited[nx][ny][0] = true;
                    }
                } else if(visited[sx][sy][1]) {
                    if(visited[nx][ny][1]) continue;
                    if(map[nx][ny] == 1) continue;
                    else visited[nx][ny][1] = true;
                }
                queue.add(new Node(nx, ny, sd + 1));
            }
        }
    }

    private static void solve() throws IOException {
        bfs(new boolean[N][M][2]);
        ans = ans == Integer.MAX_VALUE ? -1 : ans;
        System.out.println(ans);
    }
}
