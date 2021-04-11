package boj.수학;
import java.io.*;
import java.util.StringTokenizer;

public class Main_분수합_1735 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int a1,b1,a2,b2;

    private static int gcd(int a, int b) {
        int temp, n;
        if(a < b) {
            temp = a;
            a = b;
            b = temp;
        }
        while (b != 0) {
            n = a % b;
            a = b;
            b = n;
        }
        return a;
    }

    private static int lcm(int a, int b) {
        return (a * b) / gcd(a,b);
    }
    private static void input() throws IOException {
        StringTokenizer ab = new StringTokenizer(bufferedReader.readLine());
        a1 = Integer.parseInt(ab.nextToken());
        b1 = Integer.parseInt(ab.nextToken());
        StringTokenizer ab2 = new StringTokenizer(bufferedReader.readLine());
        a2 = Integer.parseInt(ab2.nextToken());
        b2 = Integer.parseInt(ab2.nextToken());
        int b3 = lcm(b1, b2);
        int a3 = (a1 * (b3 / b1)) + (a2 * (b3 / b2));
        System.out.println(a3 / gcd(a3, b3) + " " + b3 / gcd(a3, b3) );
    }

    private static void solve() throws IOException {

    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
