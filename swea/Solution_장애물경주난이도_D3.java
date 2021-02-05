package swea;

import java.io.*;
import java.util.StringTokenizer;

public class Solution_장애물경주난이도_D3 {
    static int maxUp, maxDown, N;
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    private static void input() throws IOException {
        int N = Integer.parseInt(bufferedReader.readLine());
        maxDown = 0;
        maxUp = 0;
        StringTokenizer heights = new StringTokenizer(bufferedReader.readLine());
        int beforeHeight = 0;
        for(int i = 0 ; i < N; i++) {
            int height = Integer.parseInt(heights.nextToken());
            if(beforeHeight == 0) {
                beforeHeight = height;
                continue;
            }
            if(height > beforeHeight && maxUp < height - beforeHeight) {
                maxUp = height - beforeHeight;
            }
            else if(height < beforeHeight && maxDown < beforeHeight - height) {
                maxDown = beforeHeight - height;
            }
            beforeHeight = height;
        }
    }

    static void solve(int idx) throws IOException {
        bufferedWriter.write("#"+idx+" "+maxUp + " "+maxDown+"\n");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0; i < T; i++) {
            input();
            solve(i+1);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
