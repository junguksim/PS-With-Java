package boj.구현;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_N번째큰수_2693 {
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int T;
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        input();
        bufferedReader.close();
        bufferedWriter.close();
    }

    private static void input() throws IOException {
        T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0; i < T; i++) {
            arr = new int[10];
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < 10 ;j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            solve();
        }
    }

    private static void solve() throws IOException {
        Arrays.sort(arr);
        bufferedWriter.write(arr[7] + "\n");
    }
}