package swea;
import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution_보급로_D4 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, answer;
    private static final int MAX = 987654321;
    private static int[][] map, times;
    private static boolean[][] visited;
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

    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        map = new int[N][N];
        times = new int[N][N];
        visited = new boolean[N][N];
        answer = MAX;
        for(int i = 0 ; i < N; i++) {
            String[] line = bufferedReader.readLine().split("");
            for(int j = 0 ; j < N; j++) {
                map[i][j] = Integer.parseInt(line[j]);
            }
            Arrays.fill(times[i] , MAX);
        }
        times[0][0] = 0;
    }

    private static void bfs() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(0, 0));
        visited[0][0] = true;
        while (!queue.isEmpty()) {
            Node s =  queue.poll();
            int sx = s.x;
            int sy = s.y;
            if(sx == N-1 && sy == N-1) {
                answer = Math.min(times[N-1][N-1], answer);
            }
            if(answer <= times[sx][sy]) continue;
            for (int i = 0 ; i < 4; i++) {
                int nx = sx + dx[i];
                int ny = sy + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(!visited[nx][ny] || times[nx][ny] > times[sx][sy] + map[nx][ny]) { // (sx, sy) 에서 다음 위치로 가는데 걸리는 시간이 더 작다면 최소 경로이므로
                    times[nx][ny] = times[sx][sy] + map[nx][ny]; // 갱신해준다
                    visited[nx][ny] = true;
                    queue.add(new Node(nx, ny));
                }
            }
        }
    }

//    private static void dijkstra(int x, int y) {
//        boolean[][] visited;
//        int[][] distance;
//
//        visited = new boolean[N][N];
//        distance = new int[N][N];
//
//        for(int i = 0 ; i < N; i++) {
//            Arrays.fill(distance[i], MAX);
//        }
//        distance[x][y] = 0;
//        visited[x][y] = true;
//
//        for(int i = 0 ; i < 4; i++) {
//            int nx = x + dx[i];
//            int ny = y + dy[i];
//            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
//            if(visited[nx][ny]) continue;
//            distance[nx][ny] = map[nx][ny];
//        }
//
//        for(int a = 0 ; a < N * N - 2; a++) {
//            int min = MAX;
//            int minX = -1;
//            int minY = -1;
//
//            for(int i = 0; i < N; i++) {
//                for(int j = 0 ; j < N; j++) {
//                    if(!visited[i][j] && distance[i][j] != MAX) {
//                        if(distance[i][j] < min) {
//                            min = distance[i][j];
//                            minX = i;
//                            minY = j;
//                        }
//                    }
//                }
//            }
//            visited[minX][minY] = true;
//            for(int i = 0 ; i < 4; i++) {
//                int nx = minX + dx[i];
//                int ny = minY + dy[i];
//                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
//                if(visited[nx][ny]) continue;
//                if(distance[nx][ny] > distance[minX][minY] + map[nx][ny]) {
//                    distance[nx][ny] = distance[minX][minY] + map[nx][ny];
//                }
//            }
//        }
//        answer = distance[N-1][N-1];
//    }

    private static void solve(int t) throws IOException {
        bfs();
        //dijkstra(0,0 );
        bufferedWriter.write("#" + t + " "+answer+"\n");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int t = 1; t <= T; t++) {
            solve(t);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
