package boj.문자열;

import java.io.*;
import java.util.StringTokenizer;

public class boj_문자열_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static String A,B;
    static char aFront, aRear, bFront, bRear;
    static int min;

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        A = st.nextToken();
        B = st.nextToken();
        min = Integer.MAX_VALUE;
    }

    static void solve() {
        for(int i = 0; i < B.length() - A.length() + 1; i++) {
            int diff = 0;
            for(int j = 0; j < A.length(); j++) {
                if(A.charAt(j) != B.charAt(i+ j)) {
                    diff++;
                }
            }
            min = Math.min(min, diff);
        }
        System.out.println(min);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
