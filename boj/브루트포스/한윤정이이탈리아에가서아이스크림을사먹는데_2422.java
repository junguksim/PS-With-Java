package boj.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 한윤정이이탈리아에가서아이스크림을사먹는데_2422 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static int N;
    public static int M;
    public static boolean[][] graph;
    public static int ans = 0;
    public static int[][] notGoodCombis;
    public static int[] pick = new int[3];
    public static void NM() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new boolean[N+1][N+1];
    }
    public static void getInput(int i) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int key = Integer.parseInt(st.nextToken());
        int value = Integer.parseInt(st.nextToken());
        graph[key][value] = true;
        graph[value][key] = true;
    }
    public static int getCombi(int count) {
        return (count * (count - 1) * (count -2)) / 6;
    }
    public static void solve() {
        for(int i = 1; i <= N-1; i++) {
            for(int j = i + 1; j <= N;j++) {
                if(graph[i][j]) continue;
                for(int k = j + 1; k <= N; k++) {
                    if(graph[i][k] || graph[k][j]) continue;
                    ans++;
                }
            }
        }
    }
    public static void main(String[] args) throws IOException {
        NM();
        for(int i = 0; i < M; i++) {
            getInput(i);
        }
        solve();
        System.out.println(ans);
    }
}
