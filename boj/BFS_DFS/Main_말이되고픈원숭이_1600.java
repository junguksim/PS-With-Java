package boj.BFS_DFS;
import java.io.*;
import java.util.*;

public class Main_말이되고픈원숭이_1600 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int K, W, H, ans;
    private static int[][] map;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static int[] kx = {-2,-1,1,2,2,1,-1,-2};
    private static int[] ky = {1,2,2,1,-1,-2,-2,-1};

    private static class Node {
        int x;
        int y;
        int depth;
        int k;

        public Node(int x, int y, int depth, int k) {
            this.x = x;
            this.y = y;
            this.depth = depth;
            this.k = k;
        }
    }

    private static void input() throws IOException {
        K = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer wh = new StringTokenizer(bufferedReader.readLine());
        W = Integer.parseInt(wh.nextToken()); // 가로 길이
        H = Integer.parseInt(wh.nextToken()); // 세로 길이
        map = new int[H][W];
        for(int i = 0; i < H; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0;j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        ans = Integer.MAX_VALUE;
    }

    private static void bfs(Node start, boolean[][][] visited) {
        Deque<Node> deque = new ArrayDeque<>();
        deque.offer(start);
        visited[start.x][start.y][0] = true;
        while (!deque.isEmpty()) {
            Node node = deque.poll();
            int sx = node.x;
            int sy = node.y;
            int sd = node.depth;
            int sk = node.k;
            if(sx == H-1 && sy == W-1) {
                ans = Math.min(ans, sd);
            }
            for(int i = 0 ; i < 4; i++) {
                int nx = sx + dx[i];
                int ny = sy + dy[i];
                if(nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                if(sk > K || visited[nx][ny][sk] || map[nx][ny] == 1) continue;
                deque.offer(new Node(nx, ny, sd+1, sk));
                visited[nx][ny][sk] = true;
            }
            if(sk <= K-1) {
                for(int i = 0 ; i < 8; i++) {
                    int nx = sx + kx[i];
                    int ny = sy + ky[i];
                    if(nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
                    if(visited[nx][ny][sk+1] || map[nx][ny] == 1) continue;
                    deque.offer(new Node(nx, ny, sd+1, sk + 1));
                    visited[nx][ny][sk+1] = true;
                }
            }

        }
    }

    private static void solve() throws IOException {
        bfs(new Node(0,0,0, 0), new boolean[H][W][K+1]);
        if(ans==Integer.MAX_VALUE) {
            bufferedWriter.write(-1 + "\n");
        } else {
            bufferedWriter.write(ans + "\n");
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
