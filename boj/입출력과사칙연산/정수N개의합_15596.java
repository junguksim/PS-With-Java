package boj.입출력과사칙연산;

public class 정수N개의합_15596 {
    long sum(int[] a) {
        long result = 0;
        for (int value : a) {
            result += value;
        }
        return result;
    }
}
