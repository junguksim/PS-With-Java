package boj.BFS_DFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_레이저통신_6087 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int W, H, answer = Integer.MAX_VALUE;
    private static char[][] map;
    private static boolean[][][] visited;
    private static Node start, end;
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};

    private static class Node {
        int x;
        int y;
        int mirror;

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", mirror=" + mirror +
                    '}';
        }

        public Node(int x, int y, int mirror) {
            this.x = x;
            this.y = y;
            this.mirror = mirror;
        }
    }

    private static void input() throws IOException {
        StringTokenizer wh = new StringTokenizer(bufferedReader.readLine());
        W = Integer.parseInt(wh.nextToken());
        H = Integer.parseInt(wh.nextToken());
        map = new char[H][W];
        visited = new boolean[H][W][2];
        int c = 0;
        for(int i =  0 ; i < H ; i++) {
            map[i] = bufferedReader.readLine().toCharArray();
            for(int j = 0; j < W; j++) {
                if(map[i][j] == 'C') {
                    if(c == 0) {
                        start = new Node(i, j, 0);
                        c++;
                    } else {
                        end = new Node(i, j, 0);
                    }
                }
            }
        }
        System.out.println(start.toString());
        System.out.println(end.toString());
    }

    private static char[][] cloneMap() {
        char[][] clone = new char[H][W];
        for(int i = 0 ; i < H; i++) {
            clone[i] = map[i].clone();
        }
        return clone;
    }

    private static int turnRight(int d) {
        // 0 -> 1, 3
        // 1 -> 2, 0
        // 2 -> 1, 3
        // 3 -> 0, 2
        return (d + 1) % 4;
    }

    private static int turnLeft(int d) {
        // 0 -> 1, 3
        // 1 -> 2, 0
        // 2 -> 1, 3
        // 3 -> 0, 2
        if(d == 0) return 3;
        else return d - 1;
    }

    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        visited[start.x][start.y][0] = true;
        queue.add(start);
        while (!queue.isEmpty()) {
            Node s = queue.poll();
            int sx = s.x;
            int sy = s.y;
            System.out.println(sx + " " + sy);
            int sm = s.mirror;

            if(sx == end.x && sy == end.y) {
                answer = Math.min(sm, answer);
                continue;
            }
            for(int i = 0 ; i < 4; i++) {
                int nx = sx + dx[i];
                int ny = sy + dy[i];
                if(nx < 0 || ny < 0 || nx >= H || ny >= W) {
                   continue;
                }
                if(map[nx][ny] == '*') continue;
                if(visited[sx][sy][0]) {
                    visited[nx][ny][0] = true;
                } else if(visited[sx][sy][1]) {

                }

                int nnx = nx + dx[i];
                int nny = ny + dy[i];
                if(nnx < 0 || nny < 0 || nnx >= H || nny >= W) {
                    nnx = nx + dx[turnLeft(i)];
                    nny = ny + dy[turnLeft(i)];
                    System.out.println("왼쪽으로 꺾으면 " + nnx + " " + nny + " 에 도착");
                    queue.add(new Node(nnx, nny, sm + 1));
                    nnx = nx +dx[turnRight(i)];
                    nny = ny + dy[turnRight(i)];
                    System.out.println("오른쪽으로 꺾으면 " + nnx + " " + nny + " 에 도착");
                    queue.add(new Node(nnx, nny, sm + 1));
                    continue;
                }
                queue.add(new Node(nx, ny, sm + 1));
            }
        }
    }

    private static void solve() throws IOException {
        bfs();
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
