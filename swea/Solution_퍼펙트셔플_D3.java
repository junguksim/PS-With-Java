package swea;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_퍼펙트셔플_D3 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N, middle;
    static Stack<String> beforeMiddle, afterMiddle;


    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0; i < T; i++) {
            input();
            solve(i + 1);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }

    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        beforeMiddle = new Stack<>();
        afterMiddle = new Stack<>();
        middle = (N % 2 == 0) ? (N / 2) : (N / 2) + 1;
        for(int i = 0 ; i < N; i++) {
            if(i < middle) {
                beforeMiddle.add(st.nextToken());
            } else {
                afterMiddle.add(st.nextToken());
            }
        }
//        System.out.println(beforeMiddle.toString());
//        System.out.println(afterMiddle.toString());

    }

    private static void solve(int idx) throws IOException {
        String[] result = new String[N];
        for(int i = N-1; i >= 0; i--) {
            if(i % 2 == 0) {
                result[i] = beforeMiddle.pop();
            } else {
                result[i] = afterMiddle.pop();
            }
        }
        bufferedWriter.write("#"+idx+" ");
        for(int i = 0 ; i < N; i++) {
            bufferedWriter.write(result[i] + " ");
        }
        bufferedWriter.write("\n");
    }
}
