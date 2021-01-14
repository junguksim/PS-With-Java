package boj.입출력과사칙연산;

import java.util.Scanner;

public class 곱셈_2588 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int result = a * b;
        String strB = String.valueOf(b);
        int[] digitOfB = new int[3];
        int i = 0;
        while(b > 0) {
            digitOfB[i] = b % 10;
            b /= 10;
            i++;
        }
        for(int digit : digitOfB) {
            System.out.println(a * digit);
        }
        System.out.println(result);
    }
}
