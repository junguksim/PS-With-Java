package boj.문자열;

import java.io.*;
import java.util.StringTokenizer;

public class 문자열반복_2675 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0; i < T; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int R = Integer.parseInt(stringTokenizer.nextToken());
            String p = stringTokenizer.nextToken();
            char[] chars = p.toCharArray();
            for(int k = 0 ; k < chars.length; k++) {
                for(int j = 0; j < R; j++) {
                    bufferedWriter.write(chars[k]);
                }
            }
            bufferedWriter.write('\n');
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
