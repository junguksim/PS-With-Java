package boj.조합론;

import java.io.*;
import java.util.Arrays;

public class boj_부녀회장이될테야_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    static void solve() throws IOException {
        int k = Integer.parseInt(bufferedReader.readLine());
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] building = new int[k+1][n+1];
        for(int i = 0 ; i <= k; i++) {
            for(int j = 1; j <= n; j++) {
                if(i == 0) {
                    building[i][j] = j;
                } else {
                    for(int m = 1; m <= j; m++) {
                        building[i][j] += building[i-1][m];
                    }
                }
            }
        }
        bufferedWriter.write(building[k][n]+"\n");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            solve();
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
