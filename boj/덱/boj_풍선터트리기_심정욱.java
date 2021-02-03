package boj.덱;

import java.io.*;
import java.util.*;

public class boj_풍선터트리기_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(bufferedReader.readLine());
        Deque<int[]> deque = new ArrayDeque<>();
        Deque<int[]> temp = new ArrayDeque<>();
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for(int i = 0 ; i < N ; i++) {
            deque.addLast(new int[] {i+1, Integer.parseInt(stringTokenizer.nextToken())});
        }
        while(!deque.isEmpty()) {
            int idx = deque.peekFirst()[0];
            int val = deque.peekFirst()[1];
            boolean isFirst = true;
            if(val > 0) {
                while(val > 0) {
                    System.out.println("deque");
                    for(int[] arr : deque) {
                        System.out.print(Arrays.toString(arr) + " ");
                    }
                    System.out.println("\n========");
                    System.out.println("temp");
                    for(int[] arr : temp) {
                        System.out.print(Arrays.toString(arr) + " ");
                    }
                    System.out.println("\n========");
                    if(isFirst) {
                        bufferedWriter.write(idx+ " ");
                        deque.pollFirst();
                        isFirst = false;
                    }
                    else {
                    }
                    val--;
                }
            }
            else {
                while(val < 0) {
                    if(isFirst) {
                        bufferedWriter.write(idx+ " ");
                        deque.pollFirst();
                        isFirst = false;
                    } else {
                        if(!temp.isEmpty()) {
                            deque.addFirst(temp.pollLast());
                        }
                        else if(!deque.isEmpty()){
                            deque.addFirst(deque.pollLast());
                        }
                        val++;
                    }

                }
            }

        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
