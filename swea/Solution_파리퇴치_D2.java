package swea;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_파리퇴치_D2 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N, M;
    static int[][] flies;

    static void input() throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        flies = new int[N][N];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer flyInput = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0; j < N; j++) {
                flies[i][j] = Integer.parseInt(flyInput.nextToken());
            }
        }
    }

    static void solve(int idx) throws IOException {
        int sum = 0;
        for(int i = 0; i <= N - M; i++) {
            for(int j = 0; j <= N - M; j++) {
                int temp = 0;
                for(int k = i; k < i + M; k++) {
                    for(int l = j; l < j + M; l++) {
                        temp += flies[k][l];
                    }
                }
                sum = Math.max(temp, sum);
            }
        }
        bufferedWriter.write("#"+idx+" "+sum+"\n");
    }


    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            input();
            solve(i+1);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }

}
