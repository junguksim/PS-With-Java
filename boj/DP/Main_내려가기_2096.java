package boj.DP;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_내려가기_2096 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int[][] dp1, dp2;
    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        dp1 = new int[2][3];
        dp2 = new int[2][3];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0; j < 3; j++) {
                dp1[1][j] = Integer.parseInt(st.nextToken());
                dp2[1][j] = dp1[1][j];
                if(j == 0) {
                    dp1[1][j] = Math.max(dp1[0][0], dp1[0][1]) + dp1[1][0];
                    dp2[1][j] = Math.min(dp2[0][0], dp2[0][1]) + dp2[1][0];
                } else if(j == 2) {
                    dp1[1][j] = Math.max(dp1[0][2], dp1[0][1]) + dp1[1][2];
                    dp2[1][j] = Math.min(dp2[0][2], dp2[0][1]) + dp2[1][2];
                } else {
                    dp1[1][1] = Math.max(Math.max(dp1[0][0], dp1[0][1]), dp1[0][2]) + dp1[1][1];
                    dp2[1][1] = Math.min(Math.min(dp2[0][0], dp2[0][1]), dp2[0][2]) + dp2[1][1];
                }
            }
            dp1[0] = dp1[1].clone();
            dp2[0] = dp2[1].clone();
            Arrays.fill(dp1[1], 0);
            Arrays.fill(dp2[1], 0);
        }
        for(int j = 0 ; j < 3; j++) {
            max = Math.max(max, dp1[0][j]);
            min = Math.min(min, dp2[0][j]);
        }
        bufferedWriter.write(max + " " + min);
    }


    public static void main(String[] args) throws IOException {
        input();
        bufferedReader.close();
        bufferedWriter.close();
    }
}
