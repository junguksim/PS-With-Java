package swea;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_키순서 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, M, taller, smaller;
    private static int[][] adj;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedWriter.close();
        bufferedReader.close();
    }
    
    private static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        adj = new int[N+1][N+1];
        for(int m = 1; m <= M; m++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a][b] = 1;
        }
    }
    private static void tallerBFS(int start) {
        Queue<Integer> q =new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        q.add(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            int k = q.poll();
            for(int i = 1; i <= N; i++) {
                if(adj[k][i] == 1 && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                    taller++;
                }
            }
        }
    }

    private static void smallerBFS(int start) {
        Queue<Integer> q =new LinkedList<>();
        boolean[] visited = new boolean[N+1];
        q.add(start);
        visited[start] = true;
        while (!q.isEmpty()) {
            int k = q.poll();
            for(int i = 1; i <= N; i++) {
                if(adj[i][k] == 1 && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                    smaller++;
                }
            }
        }
    }

    private static void solve() throws IOException {
        int result = 0;
        for(int k = 1; k <= N; k++) {
            smaller = 0;
            taller = 0;
            tallerBFS(k);
            smallerBFS(k);
            if(smaller + taller == N-1) result++;
        }
        bufferedWriter.write(result+"\n");
    }
}