package boj.문자열;

import java.util.Scanner;

public class 숫자의합_11720 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int length = scanner.nextInt();
        String number = scanner.next();
        int ans = 0;
        for(int i = 0 ; i < length; i++) {
            ans += number.charAt(i)-48;
        }
        System.out.println(ans);
        scanner.close();
    }
}
