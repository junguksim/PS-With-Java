package boj.투포인터;
import java.io.*;
import java.util.StringTokenizer;

public class Main_수들의합2_2003 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N,M;
    static int[] arr;

    static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        arr = new int[N+1];
        StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(line.nextToken());
        }
    }

    static void solve() {
        if(N == 1 && arr[0] == M) {
            System.out.println(1);
            return;
        }
        int ans = 0, start = 0, end = 0, sum = 0;
        while(end <= N) {
            if(sum >= M) {
                sum -= arr[start++];
            } else if(sum < M) {
                sum += arr[end++];
            }
            if(sum == M){
                ans++;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }
}
