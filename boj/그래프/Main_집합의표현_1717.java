package boj.그래프;
import java.io.*;
import java.util.StringTokenizer;

public class Main_집합의표현_1717 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N,M;
    private static int[] parents;

    private static int find(int a) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
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
        for(int i = 0; i <= N; i++) {
            parents[i] = i; // 0~N번 까지의 번호의 부모를 0~N으로 설정.
        }
        for (int i = 0; i < M; i++) {
            StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
            int op = Integer.parseInt(line.nextToken());
            int n1 = Integer.parseInt(line.nextToken());
            int n2 = Integer.parseInt(line.nextToken());
            if(op == 0) {
                union(n1, n2);
            } else if(op == 1) {
                if(find(n1) == find(n2)) {
                    bufferedWriter.write("YES\n");
                } else {
                    bufferedWriter.write("NO\n");
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
