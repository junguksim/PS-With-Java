package boj.구현;
import java.io.*;
import java.util.StringTokenizer;

/**
 * 1. 수 3개를 이용, (지구 E, 태양 S, 달 M)
 *  E <= 15, S <= 28, M <= 19
 * 2. 1년 -> 1 1 1 / 1년 지날 때 마다 모두 1씩 증가
 * 3. 어떤 수가 범위를 넘어간다면 다시 1이 된다.
 * INPUT : E S M 이 주어짐
 * RESULT : 몇년인지 구하기
 */
public class Main_날짜계산_1476 {
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int E,S,M;

    private static void input() throws IOException {
        StringTokenizer esm = new StringTokenizer(bufferedReader.readLine());
        E = Integer.parseInt(esm.nextToken());
        S = Integer.parseInt(esm.nextToken());
        M = Integer.parseInt(esm.nextToken());
    }

    private static void solve() {
        int e = 1;
        int s = 1;
        int m = 1;
        int count = 1;
        while (true) {
            if(e == E && s == S && m == M) {
                break;
            }
            e = e % 15 + 1;
            s = s % 28 + 1;
            m = m % 19 + 1;
            count++;
        }
        System.out.println(count);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.close();
    }
}