package swea;
import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_원점으로집합 {
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static Node[] nodes;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,1,-1};
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
    public static void main(String[] args) throws IOException  {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 1; i <= T; i++) {
            input();
            solve(i);
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
    
    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        nodes = new Node[N];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            nodes[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), 1);
        }
    }

    private static int getDisToZeroPoint(int x, int y) {
        return Math.abs(x) + Math.abs(y);
    }

    private static int bfs() {
        boolean[] xVisit = new boolean[200000002];
        boolean[] yVisit = new boolean[200000002];
        Deque<Node> deque = new ArrayDeque<>();
        for(int i = 0 ; i < N; i++) {
            deque.add(nodes[i]);
        }
        int dis = Integer.MAX_VALUE;
        while (!deque.isEmpty()) {
            Node n = deque.poll();
            int sx = n.x;
            int sy = n.y;
            int sd = n.d;
            xVisit[sx + 100000000] = true;
            yVisit[sy + 100000000] = true;
            //System.out.println(sx + " " + sy);
            if(sx == 0 && sy == 0) {
                dis = sd;
                continue;
            }
            for(int i = 0; i < 4; i++) {
                int nx = sx + dx[i] * (sd + 1);
                int ny = sy + dy[i] * (sd + 1);
                if(nx < -100000000|| ny < -100000000 || nx > 100000000 || ny > 100000000) continue;
                if(xVisit[nx + 100000000] && yVisit[ny + 100000000]) continue;
                //System.out.println((sd+1) + " 만큼 움직인다");
                deque.add(new Node(nx, ny, sd+1));
            }
        }
        return dis;
    }
    private static void solve(int t) throws IOException {
        System.out.println("결과 : "  +  bfs());
        bufferedWriter.write("#"+t+" ");
    }
}