package boj.BFS_DFS;
import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_말이되고픈원숭이_1600 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int K, W, H;
    private static int[][] map;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static int[] kx = {-2,-1,1,2,2,1,-1-2};
    private static int[] ky = {1,2,2,1,-1,-2,-2,-1};

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
        K = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer wh = new StringTokenizer(bufferedReader.readLine());
        W = Integer.parseInt(wh.nextToken());
        H = Integer.parseInt(wh.nextToken());
        map = new int[W][H];
    }

    private static void bfs(boolean[][][] visited) {
        Queue<Node> queue = new LinkedList<>();
        visited[0][0][0] = true;
        queue.add(new Node(0,0,1));
        for(int i = 0 ; i < 4; i++) {
            Node start = queue.poll();
            int sx = start.x;
            int sy = start.y;
            int sd = start.depth;
        }
    }

    private static void solve() throws IOException {
        //bfs(0,0, new boolean[W][H][K], 0);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
