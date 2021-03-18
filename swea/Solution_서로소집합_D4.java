package swea;
import java.io.*;
import java.util.StringTokenizer;

public class Solution_서로소집합_D4 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M;
    static int[] parents;
    static StringBuilder result = new StringBuilder();

    static int find(int a) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;

        parents[bRoot] = aRoot;
        return true;
    }

    private static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        parents = new int[N+1];
        for(int i = 1 ; i <= N; i++) {
            parents[i] = i;
        }
        for(int i = 0; i < M; i++) {
            StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
            int op = Integer.parseInt(line.nextToken());
            int a = Integer.parseInt(line.nextToken());
            int b = Integer.parseInt(line.nextToken());
            if(op == 0 ) {
                union(a, b);
            } else {
                int aRoot = find(a);
                int bRoot = find(b);
                if(aRoot == bRoot) {
                    result.append(1);
                } else {
                    result.append(0);
                }
            }
        }
    }

    private static void solve(int t) throws IOException {
        bufferedWriter.write("#" + t + " " + result.toString() + "\n");
        result = new StringBuilder();
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
