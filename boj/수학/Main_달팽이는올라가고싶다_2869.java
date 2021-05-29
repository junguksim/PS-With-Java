package boj.수학;

import java.io.*;
import java.util.StringTokenizer;

public class Main_달팽이는올라가고싶다_2869 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int a,b,v;

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    private static void input() throws IOException {
        StringTokenizer abv = new StringTokenizer(bufferedReader.readLine());
        a = Integer.parseInt(abv.nextToken());
        b = Integer.parseInt(abv.nextToken());
        v = Integer.parseInt(abv.nextToken());
    }

    private static void solve() throws IOException {
        int oneDayClimb = a-b;
        int startDay = (v-a) / (a-b);
        int sum = startDay;
        int namUnDis = v - oneDayClimb * startDay;
        if(namUnDis > a) {
            sum += 2;
        } else {
            sum += 1;
        }
        System.out.println(sum);
    }
}