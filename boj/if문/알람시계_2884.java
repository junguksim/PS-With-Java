package boj.if문;

import java.util.Scanner;

public class 알람시계_2884 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int h = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        if(m >= 45) {
            System.out.println(h + " " + (m - 45));
        }
        else {
            m = m + 15;
            if(h == 0) {
                h = 23;
            }
            else {
                h -= 1;
            }
            System.out.println(h + " " + m);
        }
    }
}
