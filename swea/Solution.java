package swea;

import java.util.*;
import java.io.*;

public class Solution {
    static StringBuilder sb = new StringBuilder();
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N,L,max,arr[][],sumCal,sumScore;

    static void solution(int index) {
        if(sumCal>L)return;
        max = Math.max(max, sumScore);
        if(index==N)return;
        sumScore += arr[index][0];
        sumCal += arr[index][1];
        solution(index+1);

        sumScore -= arr[index][0];
        sumCal -= arr[index][1];
        solution(index+1);
    }
    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for(int t=1;t<=T;t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            arr = new int[N][];
            for(int i =0;i<N;i++) {
                st = new StringTokenizer(br.readLine());
                arr[i] = new int[] {Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())};
            }
            solution(0);
            System.out.println("#"+t+" "+max);
        }
    }
}