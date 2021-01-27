package boj.게임이론;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class 돌게임3_9657 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        if(N % 7 == 0 || N % 7 == 2) {
            System.out.println("CY");
        }
        else {
            System.out.println("SK");
        }
    }
}
