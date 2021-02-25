package boj.구현;

import java.io.*;
import java.util.StringTokenizer;

public class Main_색종이_10163 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] map;
    static int[] counts;


    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        map = new int[101][101];
        counts = new int[N+1];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = 100 - Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            putPaper(x,y,w,h,i+1);
        }
    }

    static void putPaper(int x, int y, int w, int h, int idx) {
        for(int i = x; i > x - h; i--) {
            for(int j = y; j < y + w; j++) {
                if(i < 0 || i > 100 || j < 0 || j > 100) continue;
                if(map[i][j] == 0) {
                    counts[idx]++;
                } else {
                    counts[map[i][j]]--;
                    counts[idx]++;
                }
                map[i][j] = idx;
            }
        }
    }

    static void solve() {
        for(int i = 1 ; i <= N; i++) {
            System.out.println(counts[i]);
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }
}
