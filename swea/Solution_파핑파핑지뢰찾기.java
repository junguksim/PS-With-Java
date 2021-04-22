package swea;
import java.io.*;
import java.util.ArrayList;

public class Solution_파핑파핑지뢰찾기 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, ans;
    private static char[][] map;
    private static boolean[][] visited;
    private static ArrayList<Node> grounds, zeros;
    private static int[] dx = {-1,1,0,0, -1, -1, 1, 1};
    private static int[] dy = {0,0,-1,1, -1, 1, -1, 1};
    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
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
    
    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        map = new char[N][N];
        grounds = new ArrayList<>();
        visited = new boolean[N][N];
        for(int i = 0 ; i < N; i++) {
            char[] line = bufferedReader.readLine().toCharArray();
            for(int j = 0 ; j < N; j++) {
                map[i][j] = line[j];
                if(map[i][j] == '.') {
                    map[i][j] = '0';
                    grounds.add(new Node(i,j));
                }
            }
        }
    }

    private static void dfs(Node start) {
        int sx = start.x;
        int sy = start.y;
        visited[sx][sy] = true;
        for(int i = 0; i < 8; i++) {
            int nx = sx + dx[i];
            int ny = sy + dy[i];
            if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
            if(visited[nx][ny]) continue;
            visited[nx][ny] = true;
            if(map[nx][ny] == '0') {
                dfs(new Node(nx, ny));
            }
        }
    }

    private static void checkHowManyMinesNear() {
        zeros = new ArrayList<>();
        for(Node g : grounds) {
            int count = 0;
            for(int i = 0 ; i < 8; i++) {
                int nx = g.x + dx[i];
                int ny = g.y + dy[i];
                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(map[nx][ny] == '*') {
                    count++;
                }
            }
            map[g.x][g.y] = String.valueOf(count).charAt(0);
            if(count == 0) {
                zeros.add(g);
            }
        }
    }

    private static void solve(int t) throws IOException {
        checkHowManyMinesNear();
//        for(int i = 0 ; i < N; i++) {
//            for(int j = 0 ; j < N; j++) {
//                System.out.print(map[i][j]);
//            }
//            System.out.println();
//        }
        int count = 0;
        for(Node z : zeros) {
            if(!visited[z.x][z.y]) {
                //System.out.println(z.x + " " + z.y);
                dfs(z);
                count++;
            }
        }
//        for(int i = 0 ; i < N; i++) {
//            for(int j = 0 ; j < N; j++) {
//                if(visited[i][j]) System.out.print("O");
//                else if(map[i][j] == '*') System.out.print("*");
//                else System.out.print("X");
//            }
//            System.out.println();
//        }
        for(Node g : grounds) {
            if(!visited[g.x][g.y]) {
                count++;
            }
        }
       bufferedWriter.write("#"+t+" "+count+"\n");
    }
}
