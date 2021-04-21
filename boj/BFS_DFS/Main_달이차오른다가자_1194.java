package boj.BFS_DFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_달이차오른다가자_1194 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static class Node {
        int x;
        int y;
        int count;
        int key;

        public Node(int x, int y, int count, int key) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.key = key;
        }
    }
    private static int N,M, answer;
    private static char[][] map;
    private static Node start;
    private static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    private static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        map = new char[N][M];
        for(int i = 0 ; i < N; i++) {
            map[i] = bufferedReader.readLine().toCharArray();
            for(int j = 0 ; j < M; j++) {
                if(map[i][j] == '0') {
                    start = new Node(i, j , 0, 0);
                }
            }
        }
        visited = new boolean[N][M][64];
        answer = Integer.MAX_VALUE;
    }

    private static void bfs(int x, int y) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y, 0, 0));
        visited[x][y][0] = true;
        while (!queue.isEmpty()) {
            Node temp = queue.poll();
            int count = temp.count;
            int key = temp.key;

            if(map[temp.x][temp.y] == '1') {
                System.out.println(temp.count);
                return;
            }
            for(int i = 0 ; i < 4; i++) {
                int nx = temp.x + dx[i];
                int ny = temp.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if(map[nx][ny] == '#' || visited[nx][ny][key]) continue;
                if(map[nx][ny] - 'a' >= 0 && map[nx][ny] -'a' < 6) {
                    //  열쇠 만났을 때
                    int tempKey = (1 << (map[nx][ny] - 'a')) | key;
                    if(!visited[nx][ny][tempKey]) {
                        visited[nx][ny][tempKey] = true;
                        visited[nx][ny][key] = true;
                        queue.add(new Node(nx, ny, count+1, tempKey));
                    }
                } else if(map[nx][ny] - 'A' >= 0 && map[nx][ny] - 'A' < 6) {
                    // 문 일때
                    int tempDoor = (1 << (map[nx][ny] - 'A')) & key;
                    if(tempDoor > 0) {
                        visited[nx][ny][key] = true;
                        queue.add(new Node(nx, ny, count + 1, key));
                    }
                } else {
                    visited[nx][ny][key] = true;
                    queue.add(new Node(nx, ny, count + 1, key));
                }
            }
        }
        System.out.println(-1);
    }

    private static void solve() throws IOException {
        bfs(start.x , start.y);
    }
}