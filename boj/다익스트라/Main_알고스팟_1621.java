package boj.다익스트라;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_알고스팟_1621 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N,M;
    private static int[][] map;
    private static final int MAX = 987654321;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};

    private static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        M = Integer.parseInt(nm.nextToken());
        N = Integer.parseInt(nm.nextToken());
        map = new int[N][M];
        for(int i = 0; i < N; i++) {
            String[] line = bufferedReader.readLine().split("");
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
        }
    }

    private static boolean isValidPos(int x, int y) {
        return !(x < 0 || y < 0 || x >= N || y >= M);
    }

    private static void dijkstra(int x, int y) {
        boolean[][] visited = new boolean[N][M];
        int[][] distance = new int[N][M];
        for(int i = 0 ; i < N; i++) {
            Arrays.fill(distance[i], MAX);
        }
        visited[x][y] = true;
        distance[x][y] = 0;

        for(int i = 0 ; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(isValidPos(nx,ny) && !visited[nx][ny]) {
                distance[nx][ny] = map[nx][ny];
            }
        }

        for(int a = 0 ; a < N * M - 2; a++) {
            int min = MAX;
            int minX = -1;
            int minY = -1;

            for(int i = 0 ; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(!visited[i][j] && distance[i][j] != MAX) {
                        if(distance[i][j] < min) {
                            min = distance[i][j];
                            minX = i;
                            minY = j;
                        }
                    }
                }
            }
            visited[minX][minY] = true;
            for(int i = 0 ; i < 4; i++) {
                int nx = minX + dx[i];
                int ny = minY + dy[i];
                if(isValidPos(nx, ny) && !visited[nx][ny] && distance[nx][ny] > distance[minX][minY] + map[nx][ny]) {
                    distance[nx][ny] = distance[minX][minY] + map[nx][ny];
                }
            }
        }
        System.out.println(distance[N-1][M-1]);
    }
    private static void solve() throws IOException {
        dijkstra(0, 0);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
