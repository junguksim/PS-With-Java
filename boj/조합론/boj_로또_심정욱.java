package boj.조합론;

import org.omg.PortableInterceptor.INACTIVE;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_로또_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] numbers, combinations;
    static int k;
    static void combination(int cnt, int start) throws IOException {
        if(cnt == 6) {
            for(int i = 0; i < 6; i++) {
                bufferedWriter.write(combinations[i] + " ");
            }
            bufferedWriter.write("\n");
            return;
        }
        for(int i = start; i < k; i++) {
            combinations[cnt] = numbers[i];
            combination(cnt+1, i+1);
        }
    }

    static void solve(String input) throws IOException {
        StringTokenizer st = new StringTokenizer(input);
        k = Integer.parseInt(st.nextToken());
        numbers = new int[k];
        combinations = new int[6];
        for(int i = 0 ; i < k; i++) {
            numbers[i] = Integer.parseInt(st.nextToken());
        }
        combination(0, 0);
    }

    public static void main(String[] args) throws IOException {
        while (true) {
            String input = bufferedReader.readLine();
            if(input.equals("0")) {
                break;
            }
            solve(input);
            bufferedWriter.write("\n");
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
