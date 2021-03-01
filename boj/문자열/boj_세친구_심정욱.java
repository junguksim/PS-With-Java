package boj.문자열;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class boj_세친구_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static char[] ch;

    /**
     *
     * S + S -> T
     * T 의 어딘가에 문자열 하나 추가 -> U
     * 1. 중간을 기준으로 좌우 대칭인지 확인하는 함수 필요-> T가 되는지
     * 2. U의 한 문자씩 빼면서, 1의 함수를 적용해서 안되는 것들은 빼고, 되는 것들의 개수를 센다.
     * 3. 되는 게 0이면 not possible, 1이면 출력, 2 이상이면 NOT UNIQUE
     *  -> 시간 초과남
     * 만약 지혼자 1인 거면 -> 얘 삭제하면 됨.
     * 하나만 홀수인 거면, ACACA -> A 3 C 2 -> AC or CA
     */
    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        ch = bufferedReader.readLine().toCharArray();
    }

    static boolean func(char[] s1, char[] s2) {
        int cnt = 0;
        for(int i = 0 , j = 0; i < s1.length; i++, j++) {
            if(s1[i] != s2[j]) {
                if(cnt > 0) {
                    return false;
                }
                cnt++;
                i--;
            }
        }
        return true;
    }

    static void solve() {
        if(N % 2 == 0) {
            System.out.println("NOT POSSIBLE");
            return;
        }
        char[] s1 = new char[N/2];
        char[] s2 = new char[N/2 + 1];
        char[] s3 = new char[N/2];
        char[] s4 = new char[N/2 + 1];

        System.arraycopy(ch, 0, s1, 0, N/2);
        System.arraycopy(ch, N/2, s2, 0, N/2 + 1);

        System.arraycopy(ch, 0, s4, 0, N/2 + 1);
        System.arraycopy(ch, N/2+1, s3, 0, N/2);

        boolean result1 = func(s1, s2);
        boolean result2 = func(s3, s4);

        if(result1 && result2) {
            String str1 = String.valueOf(s1);
            String str2 = String.valueOf(s3);
            if(str1.contains(str2)) {
                System.out.println(str1);
            } else {
                System.out.println("NOT UNIQUE");
            }
        } else if(!result1 && !result2) {
            System.out.println("NOT POSSIBLE");
        } else if(result1) {
            System.out.println(String.valueOf(s1));
        } else {
            System.out.println(String.valueOf(s3));
        }

    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
