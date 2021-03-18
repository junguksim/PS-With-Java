package jungol;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class jungol_종교_1863 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N,M;
    private static int[] parents, rank;

    static int find(int a) {
        if(parents[a] == -1) return a;
        return parents[a] = find(parents[a]);
    }

    static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return;

        if(rank[aRoot] > rank[bRoot]) {
            parents[bRoot] = aRoot;
        } else {
            parents[aRoot] = bRoot;
            if(rank[aRoot] == rank[bRoot]) {
                rank[bRoot]++;
            }
        }
    }

    private static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        parents = new int[N+1];
        Arrays.fill(parents, -1);
        rank = new int[N+1];
        for(int i = 0; i < M; i++) {
            StringTokenizer ab = new StringTokenizer(bufferedReader.readLine());
            union(Integer.parseInt(ab.nextToken()), Integer.parseInt(ab.nextToken()));
        }
    }

    private static void solve() throws IOException {
        int result = 0;
        for(int i = 1; i <= N; i++) {
            if(parents[i] == -1) {
                result++;
            }
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
