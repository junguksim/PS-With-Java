package boj.트리;

import java.io.*;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_트리_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N, leaves, delete, root;
    static int[][] graph;
    static boolean[] isVisited;

    static void dfs(int v) {
        if(v == delete) {
            return;
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(v);
        isVisited[v] = true;
        int child = 0;
        while(!stack.isEmpty()) {
            for(int i = 0; i < N; i++) {
                if(graph[v][i] == 1 && !isVisited[i] && i != delete) {
                    child++;
                    System.out.printf("%d -> %d\n", v, i);
                    stack.push(i);
                    dfs(i);
                }
            }
            stack.pop();
        }
        System.out.printf("v = %d child = %d\n",v , child);
        if(child == 0) {
            leaves++;
        }
    }

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        isVisited = new boolean[N];
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        leaves = 0;
        root = 0;
        delete = -1;
        graph = new int[N][N];
        for(int i = 0 ; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if(parent == -1) {
                root = i;
                continue;
            }
            graph[parent][i] = 1;
            graph[i][parent] = 1;
        }
        delete = Integer.parseInt(bufferedReader.readLine());
        dfs(root);
        System.out.println(leaves);
        bufferedReader.close();
    }
}
