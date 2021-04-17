package boj.구현;
import java.io.*;
import java.util.StringTokenizer;

public class Main_쉽게푸는문제_1292 {
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int A,B;
    private static void input() throws IOException {
        StringTokenizer ab = new StringTokenizer(bufferedReader.readLine());
        A = Integer.parseInt(ab.nextToken());
        B = Integer.parseInt(ab.nextToken());
    }

    private static void solve() {
        int[] arr = new int[B+1];
        int num = 1;
        int cnt = 0;
        int ans = 0;
        for(int i = 1; i <= B; i++) {
            arr[i] = num;
            cnt += 1;
            if(i >= A) {
                ans += num;
            }
            if(cnt == num) {
                cnt = 0;
                num++;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.close();
    }
}