package boj.구현;

import java.io.*;

public class Main_회문_17609 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static String s;

    private static class TwoPoints {
        int left;
        int right;

        public TwoPoints(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            input();
            solve();
        }
    }

    private static void input() throws IOException {
        s = bufferedReader.readLine();
    }

    private static TwoPoints isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            if(s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return new TwoPoints(left, right);
            }
        }
        return null;
    }

    private static void solve() throws IOException {
        TwoPoints result = isPalindrome(s);
        if(result == null) {
            System.out.println(0);
        } else {
            //System.out.println(result.left + " " + result.right);
            String leftPlusOne = s.substring(result.left + 1, result.right + 1);
            String rightMinusOne = s.substring(result.left, result.right);
            if(isPalindrome(leftPlusOne)==null || isPalindrome(rightMinusOne) == null) {
                System.out.println(1);
            } else {
                System.out.println(2);
            }
        }
    }
}
