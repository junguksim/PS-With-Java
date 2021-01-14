package boj.if문;

import java.util.Scanner;

public class 두수비교하기_1330 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        int[] nums = new int[2];
        for(int i = 0 ; i < input.length; i++) {
            nums[i] = Integer.parseInt(input[i]);
        }
        if(nums[0] < nums[1]) {
            System.out.println("<");
        }
        else if(nums[0] == nums[1]) {
            System.out.println("==");
        }
        else {
            System.out.println(">");
        }
    }
}
