package boj.구현;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_ATM_11399 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    static void input() throws IOException {
        int N = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        int[] times = new int[N];
        for(int i = 0 ; i < N; i++) {
            times[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(times);
        //System.out.println(Arrays.toString(times));
        int ans = 0;
        for(int i = 0 ; i < N; i++) {
            ans += times[i];
            if(i < N-1) {
                times[i+1] += times[i];
            }
        }
        System.out.println(ans);
    }

    static void solve() {

    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

}
