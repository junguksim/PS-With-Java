package jungol;
import java.io.*;
import java.util.StringTokenizer;

public class Main_회전초밥_2577 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N, d, k, c;
    private static int[] sushies;

    private static void input() throws IOException {
        StringTokenizer ndkc = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(ndkc.nextToken());
        d = Integer.parseInt(ndkc.nextToken());
        k = Integer.parseInt(ndkc.nextToken());
        c = Integer.parseInt(ndkc.nextToken());
        sushies = new int[N];
        for(int i = 0 ; i < N; i++) {
            sushies[i] = Integer.parseInt(bufferedReader.readLine());
        }
    }

    private static void solve() throws IOException {
        int[] ate = new int[d+1];
        int result = 0;
        int cnt = 0;
       for(int i = 0 ; i < k; i++) {
           if(ate[sushies[i]] == 0) {
               result++;
           }
           ate[sushies[i]]++;
       }
        cnt = result;
        result = (ate[c] == 0) ? cnt + 1 : cnt;
        int start = k;
        while (true) {
            ate[sushies[(start - k) % N]]--;
            if(ate[sushies[(start - k) % N]] == 0) cnt--;
            if(ate[sushies[start % N]] == 0) {
                cnt++;
            }
            ate[sushies[start % N]]++;
            result = Math.max(result, (ate[c] == 0) ? cnt + 1 : cnt);
            start++;
            if(start == (N-1) + k) break;
        }
        System.out.println(result);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
