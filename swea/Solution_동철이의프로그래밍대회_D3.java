package swea;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_동철이의프로그래밍대회_D3 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N,M;
    static int cnt, winner;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            input();
            solve(i+1);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }

    private static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        cnt = -1;
        winner = 1;
        for(int i = 0 ; i < N; i++) {
            StringTokenizer solves = new StringTokenizer(bufferedReader.readLine());
            int count = 0;
            while (solves.hasMoreTokens()) {
                if(solves.nextToken().equals("1")) {
                    count++;
                }
            }
            if(cnt < count) {
                winner = 1;
                cnt = count;
            } else if(cnt == count) {
                winner++;
            }
        }
    }

    private static void solve(int idx) throws IOException {
        bufferedWriter.write("#"+idx+" "+winner+" "+cnt+"\n");
    }
}
