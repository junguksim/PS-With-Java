package boj.BFS_DFS;
import java.io.*;
import java.util.*;

public class Main_BFS와DFS_1260 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N,M,V;
    static ArrayList<Integer>[] adjList;
    static boolean[] visited;

    static void input() throws IOException {
        StringTokenizer nmv = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nmv.nextToken());
        M = Integer.parseInt(nmv.nextToken());
        V = Integer.parseInt(nmv.nextToken());
        adjList = new ArrayList[N+1];
        visited = new boolean[N+1];
        for(int i = 0; i <= N; i++) {
            adjList[i] = new ArrayList();
        }
        for(int i = 0 ; i < M; i++) {
            StringTokenizer edge = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.parseInt(edge.nextToken());
            int to = Integer.parseInt(edge.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }
        for(int i = 1; i <= N; i++) {
            Collections.sort(adjList[i]);
        }
        //System.out.println(Arrays.toString(adjList));
    }

    static void bfs() throws IOException {
        Queue<Integer> queue = new LinkedList<>();
        int current = V;
        visited[current] = true;
        queue.offer(V);
        while(!queue.isEmpty()) {
            current = queue.poll();
            bufferedWriter.write(current + " ");
            for(int i = 0; i < adjList[current].size(); i++) {
                if(!visited[adjList[current].get(i)]) {
                    queue.offer(adjList[current].get(i));
                    visited[adjList[current].get(i)] = true;
                }
            }
        }
    }

    static void dfs(int current) throws IOException {
        visited[current] = true;
        bufferedWriter.write(current + " ");
        for(int i = 0; i < adjList[current].size(); i++) {
            if(!visited[adjList[current].get(i)]) {
                visited[adjList[current].get(i)] = true;
                dfs(adjList[current].get(i));
            }
        }
    }

    static void solve() throws IOException {
        dfs(V);
        bufferedWriter.write("\n");
        visited = new boolean[N+1];
        bfs();
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
