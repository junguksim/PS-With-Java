package boj.트리;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_30번_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int a, b;
    static boolean[] isAVisited, isBVisited, isVisited;
    static int[][] graph;

    static void input() throws IOException {
        StringTokenizer ab = new StringTokenizer(bufferedReader.readLine());
        a = Integer.parseInt(ab.nextToken());
        b = Integer.parseInt(ab.nextToken());
        isAVisited = new boolean[1024];
        isBVisited = new boolean[1024];
        isVisited = new boolean[1024];
    }

    static void dfs(int v) {
        Stack<Integer> stack = new Stack<>();
        isVisited[v] = true;
        stack.push(v);
        while(!stack.isEmpty()) {
            for(int i = v; i >= 1; i--) {
                if(graph[v][i] == 1 && !isVisited[i]) {
                    //System.out.printf("%d -> %d \n",v,i);
                    stack.add(i);
                    dfs(i);
                }
            }
            stack.pop();
        }
    }
    static void solve() throws IOException {
        dfs(a);
        isAVisited = isVisited;
        isVisited = new boolean[1024];
        dfs(b);
        isBVisited = isVisited;
        for(int i = 1023; i >= 1; i--) {
            if( isAVisited[i]&& isBVisited[i] == isAVisited[i]) {
                bufferedWriter.write((i*10)+"\n");
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        graph = new int[1024][1024];
        for(int i = 1; i <= 511; i++) {
            graph[i][i*2] = 1;
            graph[i*2][i] = 1;
            graph[i][(i*2) + 1] = 1;
            graph[(i*2) + 1][i] = 1;
        }
        for (int i = 0; i < T; i++) {
            input();
            solve();
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
