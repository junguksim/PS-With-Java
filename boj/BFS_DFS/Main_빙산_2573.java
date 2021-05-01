package boj.BFS_DFS;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_빙산_2573 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N,M;
    private static int[][] map;
    private static ArrayList<Node> ices;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};

    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
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
        map = new int[N][M];
        ices = new ArrayList<>();
        for(int i = 0 ; i < N; i++) {
            StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < M; j++) {
                map[i][j] = Integer.parseInt(line.nextToken());
                if(map[i][j] > 0) {
                    ices.add(new Node(i, j));
                }
            }
        }
    }
    private static void setIces() {
        ices = new ArrayList<>();
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < M; j++) {
                if(map[i][j] > 0) {
                    ices.add(new Node(i, j));
                }
            }
        }
    }

    private static void melt() {
        int[][] counts = new int[N][M];
        for(Node ice : ices) {
            int ix = ice.x;
            int iy = ice.y;
            int water = 0;
            for(int i = 0 ; i < 4; i++) {
                int nx = ix + dx[i];
                int ny = iy + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(map[nx][ny] == 0) {
                    water++;
                }
            }
            counts[ix][iy] = water;
        }
        for(Node ice : ices) {
            int ix = ice.x;
            int iy = ice.y;
            if(map[ix][iy] <= counts[ix][iy]) map[ix][iy] = 0;
            else map[ix][iy] -= counts[ix][iy];
        }
    }

    private static boolean isOneIsland() {
        if(ices.size() == 0) return true;
        boolean[][] visited = new boolean[N][M];
        Queue<Node> queue = new LinkedList<>();
        Node a = ices.get(0);
        queue.add(a);
        visited[a.x][a.y] = true;
        while (!queue.isEmpty()) {
            Node n = queue.poll();
            int sx = n.x;
            int sy = n.y;
            for(int i = 0 ; i < 4; i++) {
                int nx = sx + dx[i];
                int ny = sy + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visited[nx][ny] || map[nx][ny] == 0) continue;
                queue.add(new Node(nx, ny));
                visited[nx][ny] = true;
            }
        }
        for(Node ice : ices) {
            if(!visited[ice.x][ice.y]) return false;
        }
        return true;
    }

    private static void solve() throws IOException {
        int ans = 0;
        while (isOneIsland()) {
            if(ices.size() == 0) {
                System.out.println(0);
                System.exit(0);
            } else {
                melt();
                setIces();
                ans++;
            }
        }
        System.out.println(ans);
    }
}