package boj.구현;

import java.io.*;
import java.util.Arrays;

public class Main_비밀이메일_2999 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static String s;
    static int R,C;

    static void input() throws IOException {
        s = bufferedReader.readLine();
        char[] arr = s.toCharArray();
        int len = s.length();
        for(int i = 1; i < len; i++) {
            if(len % i == 0 && i <= len / i) {
                R = i;
                C = len / i;
            }
        }

        if(R == 0 && C == 0) {
            R = 1;
            C = 1;
        }

        int index = 0;
        char[][] map = new char[R][C];
        for(int i = 0 ; i < C; i++) {
            for(int j = 0; j < R; j++) {
                map[j][i] = arr[index++];
            }
        }
        for(int i = 0 ; i < R; i++) {
            for(int j = 0; j < C; j++) {
                bufferedWriter.write(map[i][j]);
            }
        }
    }
    public static void main(String[] args) throws IOException {
        input();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
