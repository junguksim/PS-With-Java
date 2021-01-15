package boj.if문;

import java.util.Scanner;

public class 시험성적_9498 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int point = sc.nextInt();
        switch (point / 10) {
            case 10:
                System.out.println("A");
                break;
            case 9:
                System.out.println("A");
                break;
            case 8:
                System.out.println("B"); break;
            case 7:
                System.out.println("C"); break;
            case 6:
                System.out.println("D"); break;
            default:
                System.out.println("F");
        }
    }
}
