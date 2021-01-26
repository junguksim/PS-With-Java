package 스택;

import java.io.*;
import java.util.Stack;

public class 스택수열_1874 {
    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public static Stack<Integer> stack = new Stack<>();
    public static int current = 1;
    public static String getInput() throws IOException {
        int target = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        while(current <= target) {
            stack.push(current++);
            stringBuilder.append("+\n");
        }
        if(!stack.isEmpty() && stack.peek() == target) {
            stack.pop();
            stringBuilder.append("-\n");
        }
        else {
            return "NO";
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(bufferedReader.readLine());
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < n; i++) {
            String result = getInput();
            if(result != null &&result.equals("NO")) {
                System.out.println("NO");
                return;
            }
            stringBuilder.append(result);
        }
        System.out.println(stringBuilder);
        bufferedReader.close();
    }
}
