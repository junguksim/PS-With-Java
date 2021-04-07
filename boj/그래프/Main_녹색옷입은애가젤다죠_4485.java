package boj.그래프;
import java.io.*;
import java.util.*;

public class Main_녹색옷입은애가젤다죠_4485 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N;
    private static int[][] map, distance;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};

    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int lost;

        public Node(int x, int y, int lost) {
            this.x = x;
            this.y = y;
            this.lost = lost;
        }

        @Override
        public int compareTo(Node o) {
            return this.lost - o.lost;
        }
    }
    private static void input() throws IOException {
        map = new int[N][N];
        distance = new int[N][N];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                distance[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    private static boolean canGo(int x, int y) {
        if (x < 0 || x >= N || y < 0 || y >= N)
            return false;
        return true;
    }

    private static void solve(int t) throws IOException {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        distance[0][0] = map[0][0];
        pq.offer(new Node(0,0, map[0][0]));

        while (!pq.isEmpty()) {
            Node p = pq.poll();
            for(int i = 0 ; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if(canGo(nx, ny)) {
                    if(distance[nx][ny] > distance[p.x][p.y] + map[nx][ny]) {
                        distance[nx][ny] =  distance[p.x][p.y] + map[nx][ny];
                        pq.offer(new Node(nx, ny, distance[nx][ny]));
                    }
                }
            }
        }
        bufferedWriter.write("Problem "+t + ": " + distance[N-1][N-1]+"\n");
    }

    public static void main(String[] args) throws IOException {
        int t = 1;
        while (true) {
            N = Integer.parseInt(bufferedReader.readLine());
            if(N == 0) {
                break;
            }
            input();
            solve(t);
            t++;
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
