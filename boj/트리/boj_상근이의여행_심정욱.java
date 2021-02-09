package boj.트리;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class boj_상근이의여행_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m, cnt;
    static int[][] countries;
    static boolean[] isVisited;

    static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(nm.nextToken());
        m = Integer.parseInt(nm.nextToken());
        cnt = 0;
        countries = new int[n+1][n+1];
        isVisited = new boolean[n+1];
        for(int i = 0 ; i < m; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            countries[start][end] = 1;
            countries[end][start] = 1;
        }
    }

    static void dfs(int v) {
        Stack<Integer> stack = new Stack<>();
        isVisited[v] = true;
        stack.push(v);
//        System.out.println("방문 : " + v);
//        System.out.println("비행기 탄 대수 : " + cnt);
        while(!stack.isEmpty()) {
            for(int i = 1; i <= n; i++) {
                //System.out.printf("%d -> %d\n",v,i);
                if(countries[v][i] == 1 && !isVisited[i]) {
                    //System.out.printf("%d -> %d 안가봄 \n",v,i);
                    stack.add(i);
                    cnt++;
                    dfs(i);
                }
            }
            stack.pop();
        }
    }

    static void solve() throws IOException {
        dfs(1);
        bufferedWriter.write(cnt+"\n");
    }
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            input();
            solve();
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
