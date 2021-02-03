package boj.BFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_치즈_심정욱 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int width, height;
    static int[][] board;
    static int[][] direct = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };
    static boolean[][] visited;
    // 바깥부터 접근해야함
    // 안쪽의 공백은 하면안됨
    public static void bfs(int sy, int sx) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{sx,sy});
        visited[sy][sx] = true;
        while(!queue.isEmpty()) {
            int x = queue.peek()[0];
            int y = queue.peek()[1];
            queue.remove();

            for(int i = 0 ; i < 4; i++) {
                int nx = x + direct[i][0];
                int ny = y + direct[i][1];

                if(nx < 0 || ny < 0 || nx >= width || ny >= height) {
                    continue;
                }
                if(board[ny][nx] >= 1) {
                    board[ny][nx] = 2;

                }
                queue.add(new int[] {nx, ny});
            }
        }

    }

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        board = new int[height][width];
        visited = new boolean[height][width];
        for(int i = 0 ; i < height; i++) {
            st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0; j < width; j++) {
                int num = Integer.parseInt(st.nextToken());
                board[i][j] = num;
            }
        }
        int time = 0;
        bfs(0,0);
        bfs(0,width-1);
        bfs(height-1, 0);
        bfs(height-1, width-1);
        System.out.println(time);
        bufferedReader.close();
        bufferedWriter.close();
    }
}
