package boj.구현;

import java.util.Scanner;

public class Main_수이어쓰기1_1748 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int ans = 0;
        String s = "";
        int i = 1;
        int cnt = 1;
        while(N - i * 9 > 0) {
            ans += i * 9 * cnt;
            cnt++;
            N -= i * 9;
            i *= 10;
           //System.out.println(ans);

        }
        if(N!=0) {
            ans += N * cnt;
        }
        System.out.println(ans);
    }
}
