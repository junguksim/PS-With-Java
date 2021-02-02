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
        // deque : [1,3], [2,2], [3,1], [4,-3], [5,-1]
        // temp :

        // deque :
        while(!deque.isEmpty()) {
            int[] peekArr = deque.pollFirst();
            int idx = peekArr[0];
            int value = peekArr[1];
            bufferedWriter.write(idx + " ");
            if(value > 0) {
                value -= 1;
                while(value > 0) {
                    if(!deque.isEmpty()) {
                        temp.addLast(deque.pollFirst());
                    }
                    else {
                        deque.addFirst(temp.pollLast());
                    }
                    value--;
                }
            }
            else {
                value += 1;
                while(value < 0) {
                    if(!temp.isEmpty()) {
                        deque.addLast(temp.pollFirst());
                    }
                    else {
                        deque.addLast(deque.pollFirst());
                    }
                    value++;
                }
            }
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
