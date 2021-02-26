package boj.구현;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_롤케이크_3985 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int L, N, expect, expectIdx, realIdx, max;
    static int[] cake;

    static void input() throws IOException {
        L = Integer.parseInt(bufferedReader.readLine());
        N = Integer.parseInt(bufferedReader.readLine());
        cake = new int[L+1];
        realIdx = -1;
        expectIdx = -1;
        int[] counts = new int[N+1];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st=  new StringTokenizer(bufferedReader.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if(expect < end - start) {
                expectIdx = i+1;
                expect = end- start;
            }
            for(int j = start; j <= end; j++) {
                if(cake[j] == 0) {
                    cake[j] = i+1;
                    counts[i+1]++;
                }
            }
        }
        for(int i = 1 ; i <= N; i++) {
            max = Math.max(counts[i], max);
        }
        for(int i = 1 ; i <= N; i++) {
            if(realIdx == -1 && counts[i] == max) {
                realIdx = i;
            }
        }
        System.out.println(expectIdx);
        System.out.println(realIdx);
    }

    public static void main(String[] args) throws IOException {
        input();
        bufferedReader.close();
    }
}
