package swea;
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution_벌꿀채취 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N,M,C, answer, maxSum;
    private static int[][] map;
    private static int[] aPicked, bPicked;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 1; i <= T; i++) {
            input();
            solve(i);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
    
    private static void input() throws IOException {
        StringTokenizer nmc = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nmc.nextToken());
        M = Integer.parseInt(nmc.nextToken());
        C = Integer.parseInt(nmc.nextToken());
        map = new int[N][N];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static int processCombination() {
        int result = 0, aBenefit = 0, bBenefit;
        for(int i = 0 ; i < N; i++) {
            for(int j = 0; j <= N-M; j++) {
                maxSum = 0;
                makeMaxSubset(i, j, 0,0,0);
                aBenefit = maxSum;
                maxSum = 0;
                bBenefit = 0;
                for(int j2 = j + M; j2 <= N-M; j2++) {
                    makeMaxSubset(i, j2, 0,0,0);
                    if(bBenefit < maxSum) bBenefit = maxSum;
                }
                for(int i2 = i + 1; i2 < N; i2++) {
                    for(int j2 = 0; j2 <= N-M; j2++) {
                        makeMaxSubset(i2, j2, 0,0,0);
                        if(bBenefit < maxSum) bBenefit = maxSum;
                    }
                }
                if(result < aBenefit + bBenefit) result = aBenefit + bBenefit;
            }
        }
        return result;
    }

    private static void makeMaxSubset(int i, int j, int cnt, int sum, int powSum) {
        if(sum > C) return;
        if(cnt == M) {
            if(maxSum < powSum) maxSum = powSum;
            return;
        }
        makeMaxSubset(i, j+1, cnt+1, sum + map[i][j], powSum + map[i][j] * map[i][j]);
        makeMaxSubset(i, j+1, cnt+1, sum, powSum); // 비선택
    }


    private static void solve(int t) throws IOException {
        bufferedWriter.write("#"+t+" "+processCombination()+"\n");
    }
}