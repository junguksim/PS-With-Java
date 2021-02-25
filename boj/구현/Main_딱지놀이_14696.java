package boj.구현;

import java.io.*;
import java.util.StringTokenizer;

public class Main_딱지놀이_14696 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] A,B;
    // 별->동그라미->네모->세모
    // 4 -> 3 -> 2-> 1
    // 라운드수 N
    // 그림개수 a , 그림 종류들
    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0; i < N; i++) {
            StringTokenizer a = new StringTokenizer(bufferedReader.readLine());
            StringTokenizer b = new StringTokenizer(bufferedReader.readLine());
            A = new int[5];
            B = new int[5];
            int aCount = Integer.parseInt(a.nextToken());
            int bCount = Integer.parseInt(b.nextToken());
            for(int j = 0 ; j < aCount; j++) {
                A[Integer.parseInt(a.nextToken())]++;
            }
            for(int j = 0 ; j < bCount; j++) {
                B[Integer.parseInt(b.nextToken())]++;
            }
            for(int j = 4; j >= 0; j--) {
                if(A[j] > B[j]) {
                    bufferedWriter.write("A\n");
                    break;
                } else if(A[j] < B[j]) {
                    bufferedWriter.write("B\n");
                    break;
                } else {
                    if(j == 0) {
                        bufferedWriter.write("D\n");
                        break;
                    }
                }
            }
        }
    }

    static void solve() {

    }

    public static void main(String[] args) throws IOException {
        input();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
