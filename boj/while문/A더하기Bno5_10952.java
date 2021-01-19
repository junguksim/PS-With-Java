package boj.while문;

import java.util.Scanner;

public class A더하기Bno5_10952 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String[] input = scanner.nextLine().split(" ");
            int a = Integer.parseInt(input[0]);
            int b = Integer.parseInt(input[1]);
            if( a== 0 && b == 0) {
                break;
            }
            System.out.println(a+b);
        }
    }
}
