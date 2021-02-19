package boj.BFS_DFS;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_토마토_7576 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N,M, max;
    static int[][] graph;
    static ArrayList<Node> readies;
    static boolean[][] visited;
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

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", depth=" + depth +
                    '}';
        }
    }

    static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        graph = new int[M][N];
        visited = new boolean[M][N];
        readies = new ArrayList<>();
        for(int i = 0 ; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
                if(graph[i][j] == 1) {
                    readies.add(new Node(i,j, 0));
                }
            }
        }
    }

    static void bfs(int sx, int sy, int depth) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(sx,sy, depth));
        visited[sx][sy] = true;

        while(!queue.isEmpty()) {
            Node n = queue.poll();
            int x = n.x;
            int y = n.y;
            int d = n.depth;
            for(int i = 0 ; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx < 0 || ny < 0 || nx >= M || ny >= N) continue;
                if(visited[nx][ny] || graph[nx][ny] == -1) continue;
                queue.add(new Node(nx, ny, d + 1));
                visited[nx][ny] = true;
                graph[nx][ny] = true;
                max = Math.max(d+1, max);
            }
        }
    }

    static void solve() {
        for(Node ready : readies) {
            bfs(ready.x, ready.y, 0);
        }

        for(int i = 0 ; i < M; i++) {
            for(int j = 0 ; j < N; j++) {
                if(!visited[i][j] && graph[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }
        System.out.println(max);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }
}
