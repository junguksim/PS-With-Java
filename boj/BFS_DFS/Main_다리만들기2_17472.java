package boj.BFS_DFS;
import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main_다리만들기2_17472 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N,M, islandsCount;
    private static int[][] map;
    private static ArrayList<Island> islands;
    private static int[][] adjIslands;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};

    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }

    private static class Island {
        int no;
        ArrayList<Node> grounds;

        public Island(int no, ArrayList<Node> grounds) {
            this.no = no;
            this.grounds = grounds;
        }

        @Override
        public String toString() {
            return "Island{" +
                    "no=" + no +
                    ", grounds=" + grounds +
                    '}';
        }
    }

    private static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        map = new int[N][M];
        for(int i = 0 ; i < N ; i++) {
            StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < M; j++) {
                map[i][j] = Integer.parseInt(line.nextToken());
            }
        }
        islands = new ArrayList<>();
    }

    private static void setIslands() {
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < M; j++) {
                if(map[i][j] == 1) {
                    setIsland(i, j);
                }
            }
        }
        islandsCount = islands.size();
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        adjIslands = new int[islandsCount][islandsCount];
        System.out.println(islands);
    }

    private static void setIsland(int x, int y) {
        ArrayList<Node> grounds = new ArrayList<>();
        Deque<Node> deque = new ArrayDeque<>();
        deque.offer(new Node(x, y));
        while (!deque.isEmpty()) {
            Node node = deque.poll();
            int sx = node.x;
            int sy = node.y;
            for(int i = 0; i < 4; i++) {
                int nx = sx + dx[i];
                int ny = sy + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(map[nx][ny] != 1) continue;
                deque.offer(new Node(nx, ny));
                grounds.add(new Node(nx, ny));
                map[nx][ny] = 2;
            }
        }
        islands.add(new Island(islandsCount++, grounds));
    }

    private static void findConnectableIslands() {
        for(Island from : islands) {
            int fromNo = from.no;
            ArrayList<Node> fromGrounds = from.grounds;
            for(Node fromGround : fromGrounds) {
                int sx = fromGround.x;
                int sy = fromGround.y;
                for(int d = 0; d < 4; d++) {
                    int nx = sx + dx[d];
                    int ny = sy + dy[d];
                    while (nx >= 0 || ny >= 0 || nx < N || ny < M) {
                        nx += dx[d];
                        ny += dy[d];
                    }
                }
            }
        }
    }

    private static void solve() throws IOException {
        setIslands();
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
