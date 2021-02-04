package boj.스택;

import java.io.*;
import java.util.*;

public class boj_탑_심정욱 {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(bufferedReader.readLine());
        StringTokenizer s = new StringTokenizer(bufferedReader.readLine());
        // 1. 완전 탐색 : 시간초과
//        int[] heights = new int[N];
//        int[] logs = new int[N];
//        Arrays.fill(logs, -1);
//        for(int i = 0 ; i < N; i++) {
//            int input = Integer.parseInt(s.nextToken());
//            heights[i] = input;
//        }
//        for(int i = 1; i < N; i++) {
//            for(int j = i-1; j >= 0; j--) {
//                if(heights[j] > heights[i]) {
//                    logs[i] = j;
//                    break;
//                }
//            }
//        }
//        for(int i = 0 ; i < N; i++) {
//            bufferedWriter.write((logs[i]+1) + " ");
//        }
        // 2. 스택 + 큐 : 메모리 초과
//        Stack<Integer> stack = new Stack<>();
//        Queue<Integer> temp = new LinkedList<>();
//        for(int i = 0 ; i < N; i++) {
//            int input = Integer.parseInt(towers.nextToken());
//            if(stack.isEmpty()) {
//                stack.push(input);
//                bufferedWriter.write(i + " ");
//                continue;
//            }
//            else if(input < stack.peek()) {
//                bufferedWriter.write(i + " ");
//            } else {
//                int popped = 0;
//                while(!stack.isEmpty()) {
//                    if(input < stack.peek()) {
//                        break;
//                    }
//                    temp.add(stack.pop());
//                    popped++;
//
//                }
//                bufferedWriter.write(i - popped + " ");
//                while (!temp.isEmpty()) {
//                    stack.push(temp.poll());
//                    popped--;
//                }
//            }
//            stack.push(input);
//        }
        Stack<int[]> stack = new Stack<>();
        for(int i = 1; i <= N; i++) {
            int input = Integer.parseInt(s.nextToken());
            while(!stack.isEmpty()) {
                if(stack.peek()[1] >= input) {
                    bufferedWriter.write(stack.peek()[0] + " ");
                    break;
                }
                stack.pop();
            }
            if(stack.isEmpty()) bufferedWriter.write("0 ");
            stack.push(new int[] {i, input});
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
