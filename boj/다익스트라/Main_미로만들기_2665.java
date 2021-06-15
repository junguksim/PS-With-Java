package boj.다익스트라;

import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main_미로만들기_2665 {
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static boolean[][] visited;
    private static int[][] dist;
    private static char[][] map;
    private static final int MAX = 987654321;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,1,-1};

    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int cost;

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", cost=" + cost +
                    '}';
        }

        public Node(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.close();
    }
    
    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        map = new char[N][N];
        dist = new int[N][N];
        visited = new boolean[N][N];
        for(int i = 0 ; i < N; i++) {
            map[i] = bufferedReader.readLine().toCharArray();
            Arrays.fill(dist[i], MAX);
        }
    }

    private static void solve() {
        dist[0][0] = 0;
        visited[0][0] = true;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0,0,0));

        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int sx = node.x;
            int sy = node.y;
            int sc = node.cost;
            if(sx == N-1 && sy == N-1) break;
            if(dist[sx][sy] < sc) continue;
            for (int i = 0 ; i < 4; i++) {
                int nx = sx + dx[i];
                int ny = sy + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(visited[nx][ny]) continue;
                if(map[nx][ny] == '1') {
                    if(dist[nx][ny] > dist[sx][sy]) dist[nx][ny] = dist[sx][sy];
                } else {
                    if(dist[nx][ny] > dist[sx][sy] + 1) dist[nx][ny] = dist[sx][sy] + 1;
                }
                pq.add(new Node(nx, ny, dist[nx][ny]));
                visited[nx][ny] = true;
            }
        }
        System.out.println(dist[N-1][N-1]);
    }
}