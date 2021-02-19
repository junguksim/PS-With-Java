package swea;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_준환이의양팔저울_D4 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[] weights;
    static int answer, sum;
    static boolean[] visited;

    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        weights = new int[N];
        sum = 0;
        visited = new boolean[N];
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        for(int i = 0 ; i < N; i++) {
            weights[i] = Integer.parseInt(st.nextToken());
            sum += weights[i];
        }
        Arrays.sort(weights);
        answer = 0;
    }

    static int fact(int num) {
        int res=1;
        for(int i=num;i>=1;i--) {
            res*=i;
        }
        return res;
    }

    static void makePermutation(int cnt, int left, int right) {
        if(cnt == N) {
            answer++;
            return;
        }
        if(sum - left <= left) {
            answer += fact(N-cnt) * Math.pow(2, N-cnt);
            return;
        }
        for(int i = 0; i < N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            makePermutation(cnt+1, left + weights[i], right);
            visited[i]= false;

            if(left >= right + weights[i]) {
                visited[i] = true;
                makePermutation(cnt+1, left, right + weights[i]);
                visited[i]= false;
            }
        }
    }

    static void solve(int idx) throws IOException{
        makePermutation(0, 0,0);
        bufferedWriter.write("#"+idx+" "+answer+"\n");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 1; i <= T; i++) {
            input();
            solve(i);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
