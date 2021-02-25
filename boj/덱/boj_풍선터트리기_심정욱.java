package boj.덱;

import java.io.*;
import java.util.*;

public class boj_풍선터트리기_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    static class Node {
        int idx;
        int value;

        public Node(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "idx=" + idx +
                    ", value=" + value +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(bufferedReader.readLine());
        Deque<Node> deque = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        for(int i = 0 ; i < N; i++) {
            deque.addLast(new Node(i+1, Integer.parseInt(st.nextToken())));
        }
        //System.out.println(deque.toString());
        while(!deque.isEmpty()) {
            Node node = deque.poll();

            int idx = node.idx;
            int value = node.value;
            bufferedWriter.write(idx+" ");
            while (value > 1) {
                if(!deque.isEmpty()) {
                    deque.addLast(deque.pollFirst());
                }
                value--;
            }
            while (value < 0) {
                if(!deque.isEmpty()) {
                    deque.addFirst(deque.pollLast());
                }
                value++;
            }
            //System.out.println(deque.toString());
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
