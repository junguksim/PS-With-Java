package boj.큐;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_요세푸스문제_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer nk = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.parseInt(nk.nextToken());
        int k = Integer.parseInt(nk.nextToken());
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1 ; i <= n; i++) {
            queue.add(i);
        }
        int cnt = 1;
        StringBuilder result = new StringBuilder();
        result.append("<");
        while(!queue.isEmpty()) {
            if(cnt == k) {
                result.append(queue.poll());
                if(queue.size() > 0){
                    result.append(", ");
                }
                cnt = 1;
            } else {
                queue.add(queue.poll());
                cnt++;
            }
        }
        result.append(">");
        bufferedWriter.write(result.toString());
        bufferedReader.close();
        bufferedWriter.close();
    }
}
