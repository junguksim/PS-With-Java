package boj.수학;
import java.io.*;
import java.util.StringTokenizer;

public class Main_소수구하기_1929 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int M,N;
    private static void input() throws IOException {
        StringTokenizer mn = new StringTokenizer(bufferedReader.readLine());
        M = Integer.parseInt(mn.nextToken());
        N = Integer.parseInt(mn.nextToken());
    }

    private static void solve() throws IOException {
        boolean[] isFiltered = new boolean[N+1];
        for(int i = 2 ; i <= N; i++) {
            if(!isFiltered[i]) {
                if( i >= M) {
                    bufferedWriter.write(i + "\n");
                }
                int num = i;
                int index = 2;
                while (num * index <= N) {
                    isFiltered[num * index] = true;
                    index++;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
