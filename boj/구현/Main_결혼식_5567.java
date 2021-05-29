package boj.구현;

import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_결혼식_5567 {
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int n, m;
    private static boolean[][] isFriend;

    private static class Friend {
        int no;
        int depth;

        public Friend(int no, int depth) {
            this.no = no;
            this.depth = depth;
        }
    }
    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.close();
    }
    
    private static void input() throws IOException {
        n = Integer.parseInt(bufferedReader.readLine());
        m = Integer.parseInt(bufferedReader.readLine());
        isFriend = new boolean[n+1][n+1];
        for(int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            isFriend[a][b] = true;
            isFriend[b][a] = true;
        }
    }

    private static void solve() {
        Queue<Friend> queue = new LinkedList<>();
        queue.add(new Friend(1, 0));
        boolean[] isInvited = new boolean[n+1];
        isInvited[1] = true;
        while (!queue.isEmpty()) {
            Friend start = queue.poll();
            int sno = start.no;
            int sDepth = start.depth;
            for(int next = 1; next <= n; next++) {
                if(isInvited[next]) continue;
                if(isFriend[sno][next] && sDepth < 2) {
                    isInvited[next] = true;
                    queue.add(new Friend(next, sDepth+1));
                }
            }
        }
        int ans = 0;
        for(int i = 2; i <= n; i++) {
            if(isInvited[i]) ans++;
        }
        //System.out.println(Arrays.toString(isInvited));
        System.out.println(ans);
    }
}