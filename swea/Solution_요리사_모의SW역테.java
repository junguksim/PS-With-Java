package swea;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution_요리사_모의SW역테 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[] visited;
    static int N, ans;
    static int[][] synergies;
    static int[] comb;
    static boolean[] isA;


    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        synergies = new int[N][N];
        comb = new int[N/2];
        visited = new boolean[N];
        ans = Integer.MAX_VALUE;
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j  <N; j++) {
                synergies[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void solve(int idx) throws IOException {
        makeCombination(0, 0, N/2);
        bufferedWriter.write("#"+idx+" "+ans+"\n");
    }

    static void makeSynergySum() {
        int aSum = 0, bSum = 0;
        ArrayList<Integer> aList = new ArrayList<>();
        ArrayList<Integer> bList = new ArrayList<>();
        for(int i = 0 ; i < N/2; i++) {
            isA[comb[i]] = true;
            aList.add(comb[i]);
        }
        for(int i = 0 ; i < N; i++) {
            if(!isA[i]) bList.add(i);
        }
        for(int i = 0; i < N / 2; i++) {
            int start = aList.get(i);
            for(int j = i+1; j < N / 2; j++) {
                int end = aList.get(j);
                aSum += synergies[start][end] + synergies[end][start];
            }
        }

        for(int i = 0; i < N / 2; i++) {
            int start = bList.get(i);
            for(int j = i+1; j < N / 2; j++) {
                int end = bList.get(j);
                bSum += synergies[start][end] + synergies[end][start];
            }
        }
        ans = Math.min(Math.abs(bSum-aSum), ans);

    }

    static void makeCombination(int cnt, int start, int r) {
        if(cnt == r) {
            isA = new boolean[N];
            makeSynergySum();
            return;
        }
        for(int i = start; i < N; i++) {
            comb[cnt] = i;
            makeCombination(cnt + 1, i + 1, r);
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 1; i <= T; i++) {
            input();
            solve(i);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
