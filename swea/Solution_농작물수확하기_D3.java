package swea;

import java.io.*;
import java.util.Arrays;

public class Solution_농작물수확하기_D3 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    static void solve(int idx) throws IOException {
        int N = Integer.parseInt(bufferedReader.readLine());
        int[][] farm = new int[N][N];
        for(int i = 0 ; i < N; i++) {
            String s = bufferedReader.readLine();
            for(int j = 0 ; j  < N; j++) {
                farm[i][j] = Character.getNumericValue(s.charAt(j));
            }
        }
        int start = N / 2;
        int end = start;
        int middle = N / 2;
        int sum = 0;
        for(int i = 0 ; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(j >= start && j <= end) {
                    sum += farm[i][j];
                }
            }
            if(i < middle) {
                start--;
                end++;
            }
            else {
                start++;
                end--;
            }
        }
        bufferedWriter.write("#"+idx+" "+sum+"\n");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            solve(i+1);
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
