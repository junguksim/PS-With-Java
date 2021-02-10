package boj.구현;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_배열돌리기1_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n, m, r, group;
    static int[][] arr;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};

    static void swap(int sy, int sx, int ey, int ex) {
        int temp = arr[sy][sx];
        arr[sy][sx] = arr[ey][ex];
        arr[ey][ex] = temp;
    }

    private static void rotate() {
        for(int i = 0 ; i <group; i++) {
            int x = i;
            int y = i;

            int value = arr[x][y];
            int idx = 0;
            while(idx < 4) {
                int nx = x + dx[idx];
                int ny = y + dy[idx];

                if(nx >= i && ny >= i && nx < n-i && ny < m-i) {
                    arr[x][y] = arr[nx][ny];
                    x = nx;
                    y = ny;
                }
                else idx++;
            }
            arr[i+1][i] = value;
        }
    }


    public static void main(String[] args) throws IOException {
        StringTokenizer nmr = new StringTokenizer(bufferedReader.readLine());
        n = Integer.parseInt(nmr.nextToken());
        m = Integer.parseInt(nmr.nextToken());
        r = Integer.parseInt(nmr.nextToken());
        arr = new int[n][m];
        group = Math.min(n,m )/ 2;
        for(int i = 0 ; i < n; i++) {
            StringTokenizer nums = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(nums.nextToken());
            }
        }
        for(int i = 0 ; i < r; i++) {
            rotate();
        }
        for(int i = 0; i < n ; i++) {
            for(int j = 0; j < m ; j++) {
                bufferedWriter.write(arr[i][j] + " ");
            }
            bufferedWriter.write("\n");
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
