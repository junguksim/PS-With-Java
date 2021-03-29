package boj.DP;
import java.io.*;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main_AtoB_16953 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int A, B;

    private static void input() throws IOException {
        StringTokenizer ab = new StringTokenizer(bufferedReader.readLine());
        A = Integer.parseInt(ab.nextToken());
        B = Integer.parseInt(ab.nextToken());
    }

    private static void solve() throws IOException {
        int result = 1;
        while (B != A) {
            if(B < A) {
                result = -1;
                break;
            }
            String bStr = String.valueOf(B);
            if(bStr.charAt(bStr.length() - 1) != '1' && B % 2 != 0) {
                result = -1;
                break;
            }

            if(B % 2 == 0) {
                B /= 2;
            } else {
                bStr = bStr.substring(0, bStr.length() - 1);
                B = Integer.parseInt(bStr);
            }
            result++;
        }
        bufferedWriter.write(result + "\n");

    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
