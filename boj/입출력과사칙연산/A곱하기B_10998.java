package boj.입출력과사칙연산;

import java.util.Scanner;

public class A곱하기B_10998 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int result = 1;
        for(int i = 0; i < 2; i++) {
            result *= Integer.parseInt(input[i]);
        }
        System.out.println(result);
    }
}
