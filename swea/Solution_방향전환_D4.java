package swea;
import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_방향전환_D4 {
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,1,-1};
    private static Node start, end;
    private static int answer;

    private static class Node {
        int x;
        int y;
        int dir;
        int d;

        public Node(int x, int y, int dir, int d) {
            this.x = x;
            this.y = y;
            this.dir = dir;
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
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        int y = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        start = new Node(x,y, 0, 0);
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        end = new Node(x,y, 0, 0);
        answer = Integer.MAX_VALUE;
    }

    private static void bfs(Node start) {
        boolean[][] visited = new boolean[202][202];
        Deque<Node> deque = new ArrayDeque<>();
        deque.add(start);
        visited[start.x + 100][start.y + 100] = true;
        int dis = Integer.MAX_VALUE;
        while (!deque.isEmpty()) {
            Node n = deque.poll();
            int sx = n.x;
            int sy = n.y;
            int sDir = n.dir;
            int sd = n.d;
            if(sx == end.x && sy == end.y) {
                dis = Math.min(dis, sd);
                continue;
            }
            for(int i = 0; i < 4; i++) {
                if(((sDir == 0 || sDir == 1) && i >= 2) ||((sDir == 2 || sDir == 3) && i < 2) ) {
                    int nx = sx + dx[i];
                    int ny = sy + dy[i];
                    if(nx < -100|| ny < -100 || nx > 100 || ny > 100) {
                        continue;
                    };
                    if(visited[nx+100][ny+100]) {
                        continue;
                    };
                    visited[nx+100][ny+100] = true;
                    //System.out.println((sd+1) + " 만큼 움직인다");
                    deque.add(new Node(nx, ny, i, sd+1));
                }
            }
        }
        answer = Math.min(dis, answer);
    }
    private static void solve(int t) throws IOException {
        bfs(start);
        bfs(new Node(start.x, start.y, 2, 0));
        bufferedWriter.write("#"+t+" " + answer  + "\n");
    }
}