package swea;

import java.util.Scanner;

public class Solution_칩생산 {
    private static int W,H, answer;
    private static int[][] map, dp;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        int T = scanner.nextInt();
        for(int i = 1; i <= T; i++) {
            input();
            solve(i);
        }
    }
    
    private static void input() throws Exception {
        H = scanner.nextInt();
        W = scanner.nextInt();
        answer = 0;
        map = new int[H][W];
        dp = new int[(1 << H)][W-1];
        for(int i = 0 ; i < (1 << H); i++) {
            for(int j = 0; j < W-1; j++) {
                dp[i][j] = -1;
            }
        }
        for(int i = 0 ; i < H; i++) {
            //StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < W; j++) {
                map[i][j] = scanner.nextInt();
            }
        }
    }


    private static boolean check(int h, int w) {
        if(h + 1 >= H || w + 1 >= W) return false;
        return map[h][w] == 0 && map[h + 1][w] == 0 && map[h][w + 1] == 0 && map[h + 1][w + 1] == 0;
    }

    private static void dfs(int h, int w, int cnt) {
        //System.out.println(h + " " + w);
        if(h >= H-1) {
            h = 0;
            w++;
        }
        if(w == W-1) {
            if(answer < cnt) answer = cnt;
            return;
        }
        if(h == 0) {
            int bit = 0;
            for(int i = 0 ; i < H ;i++) {
                bit |= (map[i][w] << i);
            }
            if(dp[bit][w] >= cnt) return;
            dp[bit][w] = cnt;
        }
        if(check(h, w)) {
            map[h][w] = map[h+1][w] = map[h][w+1] = map[h+1][w+1] = 1;
            dfs(h+2, w, cnt+1);
            map[h][w] = map[h+1][w] = map[h][w+1] = map[h+1][w+1] = 0;
        }
        dfs(h+1, w, cnt);
    }

    private static void solve(int t) throws Exception {
        dfs(0,0,0);
        System.out.println("#"+t+" "+answer);
    }
}
