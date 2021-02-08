package swea;

import java.io.*;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_암호문1_D3 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, K;
    static LinkedList<String> passwords;


    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        passwords = new LinkedList<>();
        for(int i = 0; i < N; i++) {
            passwords.add(st.nextToken());
        }
        K = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer op = new StringTokenizer(bufferedReader.readLine(), "I");
        while(op.hasMoreTokens()) {
            StringTokenizer opDetail = new StringTokenizer(op.nextToken());
            while(opDetail.hasMoreTokens()) {
                int x = Integer.parseInt(opDetail.nextToken());
                int y = Integer.parseInt(opDetail.nextToken());
                String[] inputs = new String[y];
                for(int i = y-1; i >= 0; i--) {
                    inputs[i] = opDetail.nextToken();
                }
                for(int i = 0; i < y; i++) {
                    passwords.add(x, inputs[i]);
                }
            }
        }
    }

    static void solve(int index) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("#").append(index).append(" ");
        for(int i = 0 ; i < 10; i++) {
            sb.append(passwords.get(i)).append(" ");
        }
        sb.append("\n");
        bufferedWriter.write(sb.toString());
    }

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 10; i++) {
            input();
            solve(i);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
