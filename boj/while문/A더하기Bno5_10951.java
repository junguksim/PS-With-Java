package boj.while문;

import java.util.Scanner;

public class A더하기Bno5_10951 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println(a+b);
        }
        scanner.close();
    }
}
