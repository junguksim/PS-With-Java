package boj.큐;

import java.io.*;
import java.util.*;

public class Main_BJ_1966_프린터큐 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

    static void solve() throws IOException {
        StringTokenizer NM = new StringTokenizer(bufferedReader.readLine());
        Queue<Integer> queue = new LinkedList<>();
        int N = Integer.parseInt(NM.nextToken());
        int M = Integer.parseInt(NM.nextToken()) + 1;
        StringTokenizer importantString = new StringTokenizer(bufferedReader.readLine());
        ArrayList<Integer> importants = new ArrayList<>();
        for(int i = 1 ; i <= N; i++) {
            importants.add(Integer.parseInt(importantString.nextToken()));
            queue.add(i);
        }
        if(N == 1) {
            bufferedWriter.write("1\n");
            return;
        }
        int count = 0;
        while(queue.size() > 0) {
            int max = 0;
            System.out.println(queue.toString());
            for(int i = 1; i < importants.size(); i++) {
                if(max < importants.get(i)) {
                    max = importants.get(i);
                }
            }
            importants.remove(max);
            if(importants.get(queue.peek()) < max) {
                queue.add(queue.poll());
            }
            else {
                if(queue.poll() == M) {
                    bufferedWriter.write(count+"\n");
                    break;
                }
                count++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            solve();
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
