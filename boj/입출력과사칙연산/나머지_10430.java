package boj.입출력과사칙연산;

import java.util.Scanner;

public class 나머지_10430 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int a = Integer.parseInt(input[0]);
        int b = Integer.parseInt(input[1]);
        int c = Integer.parseInt(input[2]);
        System.out.println((a+b) % c);
        System.out.println(((a%c) + (b%c)) % c);
        System.out.println((a*b) % c);
        System.out.println(((a%c) * (b%c)) % c);
    }
}
