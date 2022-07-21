package boj.수학;

import java.io.*;
import java.util.StringTokenizer;

public class Main_분산처리_1009 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int T,a,b;
    static void input() throws IOException {
        T =Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            StringTokenizer ab = new StringTokenizer(bufferedReader.readLine());
            a = Integer.parseInt(ab.nextToken());
            b = Integer.parseInt(ab.nextToken());
            solve();
        }
    }

    static void solve() throws IOException {
        a = a % 10; // a 의 끝자리
        int[][] lastOfPow = new int[][]{{0},{1},{2,4,8,6},{3,9,7,1},{4,6},{5},{6},{7,9,3,1},{8,4,2,6},{9, 1}}; // 끝자리에 따른 제곱 끝자리의 갯수
        b = b % lastOfPow[a].length - 1; // 제곱 끝자리 갯수 만큼 나눠주고,
        // 딱 나눠떨어지면 마지막 번째에서 끝났다는 거니까
        // 인덱스를 마지막으로 맞춰줌
        if(b < 0) b = lastOfPow[a].length - 1;
        int value = lastOfPow[a][b];
        int result = value % 10;
        if(result == 0) {
            bufferedWriter.write("10\n");
        } else {
            bufferedWriter.write(result + "\n");
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
