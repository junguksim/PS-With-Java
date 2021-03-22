package jungol;
import java.io.*;
import java.util.StringTokenizer;

public class Main_해밀턴순환회로_1681 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, min = Integer.MAX_VALUE;
    private static int[][] graph;
    private static boolean[] visited;

    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        graph = new int[N][N];
        visited = new boolean[N];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0; j < N; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        visited[0] = true;
    }

    private static void dfs(int now, int depth, int cost) {
        if(depth == N-1) {
            if(graph[now][0] == 0) return;
            cost += graph[now][0];
            min = Math.min(cost, min);
            return;
        }
        if(cost > min) return;

        for(int i = 0 ; i < N; i++) {
            if(visited[i]) continue;
            if(graph[now][i] == 0) continue;

            visited[i] = true;
            dfs(i, depth+1, cost + graph[now][i]);
            visited[i] = false;
        }
    }

    private static void solve() throws IOException {
        dfs(0,0,0);
        System.out.println(min);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
