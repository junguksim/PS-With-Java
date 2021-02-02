package swea;

import java.io.*;
import java.util.Arrays;

public class Solution_달팽이숫자_D2 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    static void solve(int t) throws IOException {
        int n = Integer.parseInt(bufferedReader.readLine());
        int[][] arr = new int[n][n];
        for (int[] line : arr) {
            Arrays.fill(line, -1);
        }
        int[] dx = {1, 0 ,-1, 0};
        int[] dy = {0, 1, 0, -1};
        int i = 1, idx = 0, x = 0, y = 0;
        while(i <= n * n) {
            arr[y][x] = i;
            if(x + dx[idx] < 0 || y + dy[idx] < 0 || x + dx[idx] >= n ||y + dy[idx] >= n || arr[y+dy[idx]][x+dx[idx]] != -1) {
                idx++;
            }
            if(idx >= 4) {
                idx %= 4;
            }
            x += dx[idx];
            y += dy[idx];

            i++;
        }
        bufferedWriter.write("#"+t+"\n");
        for(int j = 0 ; j < n; j++) {
            for(int k = 0; k < n; k++) {
                bufferedWriter.write(arr[j][k] + " ");
            }
            bufferedWriter.write("\n");
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0; i < T; i++) {
            solve(i + 1);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
