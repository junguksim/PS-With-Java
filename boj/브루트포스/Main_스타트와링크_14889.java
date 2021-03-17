package boj.브루트포스;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_스타트와링크_14889 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, startSum, linkSum, answer;
    private static int[] people;
    private static boolean[] isStart;
    private static int[][] S;
    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        people = new int[N];
        S = new int[N][N];
        startSum = 0;
        linkSum = 0;
        answer = Integer.MAX_VALUE;
        isStart = new boolean[N];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            people[i] = i+1;
            for(int j = 0; j < N; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void startCombi(int cnt, int start, int[] arr) {
        if(cnt == N / 2) {
            for(int i = 0 ; i < N/2; i++) {
                isStart[arr[i] - 1] = true;
            }
            for(int i = 0; i < N-1; i++) {
                for(int j = i+1; j < N; j++) {
                    if(isStart[i] && isStart[j]) {
                        startSum += S[i][j] + S[j][i];
                    } else if(!isStart[i] && !isStart[j]) {
                        linkSum += S[i][j] + S[j][i];
                    }
                }
            }
            answer = Math.min(answer, Math.abs(linkSum - startSum));
            startSum = 0;
            linkSum = 0;
            isStart = new boolean[N];
            return;
        }
        for(int i = start; i < N; i++) {
            arr[cnt] = people[i];
            startCombi(cnt+1, i + 1, arr);
        }
    }

    private static void solve() throws IOException {
        startCombi(0, 0, new int[N/2]);
        System.out.println(answer);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.close();
    }
}
