package swea;
import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_탈주범검거 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M, R, C, L;
    private static int[][] tunnels;
    private static boolean[][] visited;
    private static Node start;
    private static int[][] dx = {{-1, 1, 0, 0}, {1,-1}, {0,0}, {-1,0}, {1,0} ,{0,1}, {0,-1}};
    private static int[][] dy = {{0, 0, -1, 1} ,{0, 0}, {1,-1}, {0,1}, {0,1} ,{-1,0}, {-1,0}};

    private static class Node {
        int x;
        int y;
        int time;
        int tunnelType;

        public Node(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
    
    private static void input() throws IOException {
        StringTokenizer nmrcl = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nmrcl.nextToken());
        M = Integer.parseInt(nmrcl.nextToken());
        R = Integer.parseInt(nmrcl.nextToken()); // 맨홀 뚜껑 세로
        C = Integer.parseInt(nmrcl.nextToken()); // 맨홀 뚜껑 가로
        L = Integer.parseInt(nmrcl.nextToken()); // 탈출 후 소요 시간
        start = new Node(R, C, 0);
        tunnels = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < M; j++) {
                tunnels[i][j] = Integer.parseInt(line.nextToken());
            }
        }
    }

    private static int bfs() {
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(start);
        visited[start.x][start.y] = true;
        int count = 0;
        while (!deque.isEmpty()) {
            Node n = deque.poll();
            int sx = n.x;
            int sy = n.y;
            int sTime = n.time;
            if(sTime >= L) continue;
            count++;
            int tunnelType = tunnels[sx][sy] - 1;

            int[] ddx = dx[tunnelType];
            int[] ddy = dy[tunnelType];
            for(int i = 0; i < ddx.length; i++) {
                int nx = sx + ddx[i];
                int ny = sy + ddy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(tunnels[nx][ny] == 0 || visited[nx][ny]) continue;
                int[] dddx = dx[tunnels[nx][ny] - 1];
                int[] dddy = dy[tunnels[nx][ny] - 1];
                //System.out.println("(nx , ny ) : (" + nx + " " + ny + ")");
                for(int j = 0 ; j < dddx.length; j++) {
                    int nnx = nx + dddx[j];
                    int nny = ny + dddy[j];
                    //System.out.println("(nnx , nny ) : (" + nnx + " " + nny + ")");
                    if(nnx == sx && nny == sy) {
                        visited[nx][ny] = true;
                        deque.add(new Node(nx, ny, sTime+1));
                    }
                }
            }
        }
//        for(int i = 0 ; i < N; i++) {
//            for(int j = 0 ; j < M; j++) {
//                if(visited[i][j]) System.out.print("1 ");
//                else System.out.print("0 ");
//            }
//            System.out.println();
//        }
        return count;
    }

    private static void solve(int t) throws IOException {
        bufferedWriter.write("#"+t + " " + bfs()+"\n");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 1; i <= T; i++) {
            input();
            solve(i);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
