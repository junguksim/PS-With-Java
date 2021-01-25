package 스택;

import java.io.*;
import java.util.Stack;

public class 스택수열_1874 {
    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    public static Stack<Integer> stack = new Stack<>();
    public static int current = 1;
    public static int[] array;
    public static int index = 0;
    public static void setArray(int n) {
        array = new int[n];
    }
    public static void getInput() throws IOException {
        int target = Integer.parseInt(bufferedReader.readLine());
        System.out.println("\n=========현재 스택==========");
        for (Integer integer : stack) {
            System.out.print(integer + " ");
        }
        System.out.println("\n===================");
        System.out.println("target : " + target);
        System.out.println("current : " + current);
        if(current < target) {
            while(current <= target) {
                bufferedWriter.write("+\n");
                stack.push(current);
                current++;
            }
            array[index] = current;
            current--;
        }
        else {
            while(stack.peek() > target) {
                bufferedWriter.write("-\n");
                stack.pop();
            }
            bufferedWriter.write("-\n");
            array[index] = stack.pop();
        }
        bufferedWriter.write("==================\n");
        index++;
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(bufferedReader.readLine());
        setArray(n);
        for(int i = 0; i < n; i++) {
            getInput();
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
