package boj.구현;
import java.io.*;
import java.util.StringTokenizer;

public class Main_개미_10158 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int w,h,p,q,t;
    static void input() throws IOException {
        StringTokenizer wh = new StringTokenizer(bufferedReader.readLine());
        w = Integer.parseInt(wh.nextToken());
        h = Integer.parseInt(wh.nextToken());
        StringTokenizer pq = new StringTokenizer(bufferedReader.readLine());
        p = Integer.parseInt(pq.nextToken());
        q = Integer.parseInt(pq.nextToken());
        t = Integer.parseInt(bufferedReader.readLine());
    }

    static void solve() {
        p = (p+t) % (2 * w);
        q = (q+t) % (2 * h);
        p = w - Math.abs(w-p);
        q = h - Math.abs(h-q);
        System.out.println(p + " " + q);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }
}
