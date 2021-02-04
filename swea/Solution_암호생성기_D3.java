package swea;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_암호생성기_D3 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));



    public static void main(String[] args) throws IOException {

        for(int i = 0; i < 10; i++) {
            Queue<Integer> queue = new LinkedList<>();
            int n = Integer.parseInt(bufferedReader.readLine());
            StringTokenizer data = new StringTokenizer(bufferedReader.readLine());
            while(data.hasMoreTokens()) {
                queue.add(Integer.parseInt(data.nextToken()));
            }
            int val = 1;
            while(!queue.isEmpty() && queue.peek() > 0) {
                int minus = queue.poll() - val;
                minus = Math.max(minus, 0);
                queue.add(minus);
                if(minus == 0) break;
                val = val > 4 ? 0 : val;
                val++;
            }
            bufferedWriter.write("#"+(i+1)+" ");
            while (!queue.isEmpty()) {
                bufferedWriter.write(queue.poll()+" ");
            }
            bufferedWriter.write("\n");
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
