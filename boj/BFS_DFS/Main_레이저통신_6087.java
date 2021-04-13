package boj.BFS_DFS;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_레이저통신_6087 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int W, H, answer = Integer.MAX_VALUE;
    private static char[][] map;
    private static int[][] mirrors;
    private static ArrayList<Node>  laser;
    private static int[] dx = {-1,0,1,0};
    private static int[] dy = {0,1,0,-1};

    private static class Node {
        int x;
        int y;
        int dir;
        int count;

        public Node(int x, int y, int dir, int count) {
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    ", dir=" + dir +
                    ", count=" + count +
                    '}';
        }
    }

    private static void input() throws IOException {
        StringTokenizer wh = new StringTokenizer(bufferedReader.readLine());
        W = Integer.parseInt(wh.nextToken());
        H = Integer.parseInt(wh.nextToken());
        map = new char[H][W];
        mirrors = new int[H][W];
        laser = new ArrayList<>();
        int c = 0;
        for(int i =  0 ; i < H ; i++) {
            map[i] = bufferedReader.readLine().toCharArray();
            for(int j = 0; j < W; j++) {
                mirrors[i][j] = Integer.MAX_VALUE;
                if(map[i][j] == 'C') {
                    laser.add(new Node(i, j , -1 ,0));
                }
            }
        }
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
        Node start = laser.get(0);
        Node end = laser.get(1);
        queue.add(start);
        mirrors[start.x][start.y] = 0;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            int sx = node.x;
            int sy = node.y;
            int sDir = node.dir;
            int sCount = node.count;
            if(sx == end.x && sy == end.y) {
                answer = Math.min(sCount, answer);
            }
            for(int i = 0 ; i < 4; i++) {
                int nx = sx + dx[i];
                int ny = sy + dy[i];
                int nd = i;
                if(nx < 0 || ny < 0 || nx >= H || ny >= W || map[nx][ny]=='*') continue;
                int temp = sCount;
                if(sDir != nd && sDir != -1) {
                    temp++;
                }
                if(mirrors[nx][ny] < temp) {
                    continue;
                }
                mirrors[nx][ny] = temp;
                queue.add(new Node(nx, ny, nd, temp));
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
