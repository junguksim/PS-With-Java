package boj.정렬;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_배열합치기_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(nm.nextToken());
        int m = Integer.parseInt(nm.nextToken());
        int[] arr = new int[n+m];
        int idx = 0;
        for(int i = 0; i < 2; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            while (st.hasMoreTokens()) {
                arr[idx] = Integer.parseInt(st.nextToken());
                idx++;
            }

        }
        Arrays.sort(arr);
        for(int i = 0 ; i < arr.length; i++) {
            bufferedWriter.write(arr[i]+" ");
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
