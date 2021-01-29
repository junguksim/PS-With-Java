package boj.브루트포스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 체스판다시칠하기_1018 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer stringTokenizer;
    static char ch[][];
    static int N, M, ans = 2500;

    static void func(int x, int y) {
        int cnt1 = 0; // 처음이 화이트일때
        int cnt2 = 0; // 처음이 블랙일때
        for (int i = x; i < x + 8; i++) {
            for (int j = y; j < y + 8; j++) {
                if (i % 2 == 0) {
                    if (j % 2 == 0) {
                        if (ch[i][j] == 'B')
                            cnt2++;
                        else
                            cnt1++;
                    } else {
                        if (ch[i][j] == 'B')
                            cnt1++;
                        else
                            cnt2++;
                    }
                } else {
                    if (j % 2 == 0) {
                        if (ch[i][j] == 'B')
                            cnt1++;
                        else
                            cnt2++;
                    } else {
                        if (ch[i][j] == 'B')
                            cnt2++;
                        else
                            cnt1++;
                    }
                }
            }
        }

        ans = Math.min(ans, Math.min(cnt1, cnt2));
    }

    static void solve() {
        for (int i = 0; i <= N - 8; i++) {
            for (int j = 0; j <= M - 8; j++) {
                func(i, j);
            }
        }

        System.out.println(ans);
    }

    static void input() throws Exception {
        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(stringTokenizer.nextToken());
        M = Integer.parseInt(stringTokenizer.nextToken());
        ch = new char[N][M];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            ch[i] = stringTokenizer.nextToken().toCharArray();
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        solve();
    }
}
