package boj.while문;

import java.util.Scanner;

public class 더하기사이클_1110 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int units = n % 10;
        int tens = n / 10;
        int unitsPlusTens = units + tens;
        int newNum = units * 10 + unitsPlusTens % 10;
        if(newNum == n) {
            System.out.println(1);
            return;
        }
        int count = 2;
        while(true) {
            units = newNum % 10;
            tens = newNum / 10;
            unitsPlusTens = units + tens;
            newNum = units * 10 + unitsPlusTens % 10;
            if(newNum == n) {
                System.out.println(count);
                break;
            }
            count++;
        }
    }
}
