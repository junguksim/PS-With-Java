package boj.BFS_DFS;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_벽부수고이동하기_2206 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N,M, ans = Integer.MAX_VALUE;
    static int[][] map;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Node {
        int x;
        int y;
        int depth;

        public Node(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }

    static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        map = new int[N][M];
        for(int i = 0 ; i < N; i++) {
            char[] line = bufferedReader.readLine().toCharArray();
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(Character.toString(line[j]));
            }
        }
    }

    static void bfs(boolean[][][] visited) {
        Queue<Node> queue = new LinkedList<>();
        visited[0][0][0] = true;
        queue.add(new Node(0,0,1));
        while (!queue.isEmpty()) {
            Node start = queue.poll();
            int sx = start.x;
            int sy = start.y;
            int sd = start.depth;
            if(sx == N-1 && sy == M-1) {
                ans = Math.min(ans, sd);
                continue;
            }
            for(int i = 0 ; i < 4; i++) {
                int nx = sx + dx[i];
                int ny = sy + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visited[sx][sy][0]) {
                    if(visited[nx][ny][0]) continue;
                    //System.out.println(nx + " " + ny + " " + (sd + 1));
                    if(map[nx][ny] == 1) {
                        visited[nx][ny][1] = true;
                    } else {
                        visited[nx][ny][0] = true;
                    }
                } else if(visited[sx][sy][1]) {
                    if(visited[nx][ny][1]) continue;
                    //System.out.println(nx + " " + ny + " " + (sd + 1));
                    if(map[nx][ny] == 1) {
                        continue;
                    } else {
                        visited[nx][ny][1] = true;
                    }
                }
                queue.add(new Node(nx, ny, sd+1));
            }
        }
    }

    static void solve() {
        bfs(new boolean[N][M][2]);
        if(ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
