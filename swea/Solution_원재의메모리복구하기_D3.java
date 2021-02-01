package swea;

import java.io.*;

public class Solution_원재의메모리복구하기_D3 {
    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    public static String input;
    public static void getInput() throws IOException {
        input = bufferedReader.readLine();
    }

    public static void solve(int idx) throws IOException {
        char cp = '0';
        int cnt = 0;
        for(int i = 0 ; i < input.length(); i++) {
            if(input.charAt(i) != cp) {
                cnt++;
                cp = input.charAt(i);
            }
        }
        bufferedWriter.write("#"+(idx+1)+ " "+cnt+"\n");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            getInput();
            solve(i);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
