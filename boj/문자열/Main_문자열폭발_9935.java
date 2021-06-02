package boj.문자열;

import java.io.*;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class Main_문자열폭발_9935 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static String str, bomb;

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.flush();
        bufferedWriter.close();
    }

    private static void input() throws IOException {
        str = bufferedReader.readLine();
        bomb = bufferedReader.readLine();
    }

    private static void solve() throws IOException {
        char[] result = new char[str.length()];
        int idx = 0;

        for(int i = 0 ; i < str.length(); i++) {
            result[idx] = str.charAt(i);
            if(isBomb(result,idx)) idx -= bomb.length();
            idx++;
        }
        String res = String.valueOf(result, 0, idx);

        bufferedWriter.write(res.length() == 0 ? "FRULA" :  res);
    }

    private static boolean isBomb(char[] result, int idx) {
        if(idx < bomb.length() - 1) return false;
        for(int i = 0 ; i < bomb.length(); i++) {
            if(bomb.charAt(i) != result[idx - bomb.length() + 1 + i]) return false;
        }
        return true;
    }
}
