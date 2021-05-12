package boj.자료구조;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main_가운데를말해요_1655 {
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static PriorityQueue<Integer> minHeap, maxHeap;
    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.close();
    }
    
    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        minHeap = new PriorityQueue<>((o1, o2) -> o1 - o2);
        maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for(int i = 0 ; i < N; i++) {
            int num = Integer.parseInt(bufferedReader.readLine());
            if(minHeap.size() == maxHeap.size()) maxHeap.offer(num);
            else minHeap.offer(num);

            if(!minHeap.isEmpty() && !maxHeap.isEmpty()) {
                if(minHeap.peek() < maxHeap.peek()) {
                    int tmp = minHeap.poll();
                    minHeap.offer(maxHeap.poll());
                    maxHeap.offer(tmp);
                }
            }
            bufferedWriter.write(maxHeap.peek()+"\n");
        }
    }

    private static void solve() {
    }
}
