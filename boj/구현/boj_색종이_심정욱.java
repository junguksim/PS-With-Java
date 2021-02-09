package boj.구현;

import java.io.*;
import java.util.StringTokenizer;

public class boj_색종이_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int[][] paper;

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(bufferedReader.readLine());
        paper = new int[101][101];
        int cnt = 0;
        for(int i = 0 ; i < n; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            int sx = Integer.parseInt(st.nextToken());
            int sy = Integer.parseInt(st.nextToken());
            for(int y = sy; y < sy+10; y++) {
                for(int x = sx; x < sx + 10; x++) {
                    if(y > 100 || x > 100) {
                        break;
                    }
                    if(paper[y][x] == 0) {
                        paper[y][x] = 1;
                        cnt++;
                    }
                }
            }
        }
        System.out.println(cnt);
        bufferedReader.close();
    }
}
