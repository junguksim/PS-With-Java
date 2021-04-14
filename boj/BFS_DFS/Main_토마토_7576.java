package boj.BFS_DFS;
import java.io.*;
import java.util.*;

public class Main_토마토_7576 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int width, height, result, shouldRipeCount;
    private static int[][] map;
    private static ArrayList<Node> tomatoes;
    private static boolean[][] visited;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};


    private static class Node {
        int x;
        int y;
        int depth;

        public Node(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }

    private static void input() throws IOException {
        StringTokenizer wh = new StringTokenizer(bufferedReader.readLine());
        width = Integer.parseInt(wh.nextToken());
        height = Integer.parseInt(wh.nextToken());
        map = new int[height][width];
        visited = new boolean[height][width];
        tomatoes = new ArrayList<>();
        for(int i = 0 ; i < height; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < width; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    tomatoes.add(new Node(i,j, 0));
                    visited[i][j] = true;
                } else if(map[i][j] == 0) {
                    shouldRipeCount++;
                }
            }
        }
    }

    private static boolean bfs() {
        ArrayDeque<Node> queue = new ArrayDeque<>();
        queue.addAll(tomatoes);
        int ripeCount = 0;
        while (!queue.isEmpty()) {
            Node s = queue.poll();
            int sx = s.x;
            int sy = s.y;
            int sd = s.depth;
            result = sd;
            for(int i = 0 ; i < 4; i++) {
                int nx = sx + dx[i];
                int ny = sy + dy[i];
                if(nx < 0 || ny < 0 || nx >= height || ny >= width) continue;
                if(visited[nx][ny]) continue;
                if(map[nx][ny] == 0) {
                    map[nx][ny] = 1;
                    ripeCount++;
                    visited[nx][ny] = true;
                    queue.add(new Node(nx, ny, sd + 1));
                }
            }
        }
        if(ripeCount == shouldRipeCount) {
            return true;
        } else return false;
    }

    private static void solve() throws IOException {
        if(bfs()) {
            System.out.println(result);
        } else {
            System.out.println(-1);
        }

    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
