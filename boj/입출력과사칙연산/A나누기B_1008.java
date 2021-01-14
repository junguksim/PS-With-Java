package boj.입출력과사칙연산;

import java.util.Scanner;

public class A나누기B_1008 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        double result = -1;
        for(int i = 0; i < 2; i++) {
            if(result == -1) {
                result = Double.parseDouble(input[i]);
            }
            else {
                result /= Double.parseDouble(input[i]);
            }
        }
        System.out.println(result);
    }
}
