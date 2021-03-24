package swea;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_하나로_D4 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N, E;

    static class Island {
        int x;
        int y;

        public Island(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void input() throws IOException
    {
        N = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0; i < N; i++) {
            StringTokenizer xy = new StringTokenizer(bufferedReader.readLine());
            
        }
    }

    static void solve(int t) throws IOException {

    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 1; i <= T ; i++) {
            input();
            solve(i);
        }
    }
}