package boj.브루트포스;

import java.io.*;

public class boj_백설공주와일곱난쟁이_심정욱 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int[] nums;
    static boolean[] visited;


    static void input() throws IOException {
        nums = new int[9];
        visited = new boolean[9];
        for(int i = 0 ; i < 9; i++) {
            nums[i] = Integer.parseInt(bufferedReader.readLine());
        }
    }

    static void combi(int depth, int r) throws IOException {
        if(r == 0) {
            int sum = 0;
            for(int i = 0 ; i < 9 ; i++) {
                if(visited[i]) {
                   sum += nums[i];
                }
            }
            if(sum == 100) {
                for(int i = 0 ; i < 9; i++) {
                    if(visited[i]) {
                        bufferedWriter.write(nums[i]+"\n");
                    }
                }
            }
            return;
        }
        if(depth == 9) {
            return;
        }
        visited[depth] = true;
        combi(depth+1, r - 1);
        visited[depth] = false;
        combi(depth+1, r);
    }
    static void solve() throws IOException {
        combi(0, 7);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
