package swea;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_최적경로_D5 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, ans;
    static Node[] clients;
    static Node company, home;
    static boolean[][] visited;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};
    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = y;
            this.y = x;
        }
    }


    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        clients = new Node[N];
        visited = new boolean[101][101];
        ans = Integer.MAX_VALUE;
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        int idx = 0;
        while (st.hasMoreTokens()) {
            if(idx == 0) {
                company =new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            } else if(idx == 1) {
                home = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            } else {
                clients[idx-2] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            idx++;
        }
    }

    static void makePermutation(int cnt, Node[] tempClients, boolean[] visited) {
        if(cnt == N) {
            ans = Math.min(ans, getSum(company.x, company.y, tempClients));
            return;
        }
        for(int i = 0; i < N; i++) {
            if(visited[i]) continue;
            tempClients[cnt] = clients[i];
            visited[i] = true;
            makePermutation(cnt+1, tempClients, visited);
            visited[i] = false;
        }
    }

    static int getSum(int sx, int sy, Node[] tempClients) {
        int sum = 0;
        for(int i = 0 ; i < N; i++) {
            int tx = tempClients[i].x;
            int ty = tempClients[i].y;
            sum += Math.abs(tx - sx) + Math.abs(ty - sy);
            sx = tx;
            sy = ty;
        }
        sum += Math.abs(home.x - sx) + Math.abs(home.y - sy);
        return sum;
    }

    static void solve(int idx) throws IOException {
        Node[] tempClients = new Node[N];
        boolean[] visited = new boolean[N];
        makePermutation(0, tempClients, visited);
        bufferedWriter.write("#"+idx+" "+ans+"\n");
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
