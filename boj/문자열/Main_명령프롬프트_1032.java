package boj.문자열;
import java.io.*;

public class Main_명령프롬프트_1032 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static String[] patterns;
    static void input() throws IOException {
        N=Integer.parseInt(bufferedReader.readLine());
        patterns = new String[N];
        for(int i = 0 ; i < N; i++) {
            patterns[i] = bufferedReader.readLine();
        }
    }

    static void solve() throws IOException {
        String result = "";
        for(int i = 0; i < patterns[0].length(); i++) {
            char c1 = patterns[0].charAt(i);
            for(int j = 1; j < N;j++) {
                char c2 = patterns[i].charAt(i);
                if(c1 != c2) {
                    result += "?";
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
