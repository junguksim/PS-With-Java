package boj.BFS_DFS;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_보물섬_2589 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N,M, answer;
    private static char[][] map;
    private static ArrayList<Node> lands;
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
        bufferedReader.close();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        map = new char[N][M];
        lands = new ArrayList<>();
        for(int i = 0 ; i < N; i++) {
            map[i] = bufferedReader.readLine().toCharArray();
            for(int j = 0 ; j < M; j++) {
                if(map[i][j] == 'L') {
                    lands.add(new Node(i,j, 0));
                }
            }
        }
    }

    private static void solve() throws IOException {
        for(Node land : lands) {
            bfs(land);
        }
        System.out.println(answer);
    }

    private static void bfs(Node start) {
        int max = 0;
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        boolean[][] visited = new boolean[N][M];
        visited[start.x][start.y] = true;
        while (!queue.isEmpty()) {
            Node s = queue.poll();
            int sx = s.x;
            int sy = s.y;
            int sd = s.d;
            max = Math.max(max, sd);
            for(int i = 0 ; i < 4; i++) {
                int nx = sx + dx[i];
                int ny = sy + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visited[nx][ny] || map[nx][ny] == 'W') continue;
                visited[nx][ny] = true;
                queue.add(new Node(nx, ny, sd + 1));
            }
        }
        //System.out.println(start.x + " " + start.y + " 에서의 최대값 : " + max );
        answer = Math.max(max, answer);
    }
}
