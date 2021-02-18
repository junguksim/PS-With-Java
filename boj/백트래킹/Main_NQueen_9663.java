package boj.백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_NQueen_9663 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N, ans;
    static int[] col;

    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        col = new int[N+1];
    }

    static boolean isAvailable(int rowNo) {
        for(int i = 1;i < rowNo; i++) {
            if(col[rowNo] == col[i] || Math.abs(col[rowNo] - col[i]) == rowNo - i) {
                return false;
            }
        }
        return true;
    }

    static void setQueen(int rowNo) {
        if(!isAvailable(rowNo)) {
            return;
        }
        if(rowNo == N) {
            ans++;
            return;
        }
        for(int i = 1; i <= N; i++) {
            col[rowNo+1] = i;
            setQueen(rowNo+1);
        }
    }

    static void solve() {
        setQueen(0);
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
