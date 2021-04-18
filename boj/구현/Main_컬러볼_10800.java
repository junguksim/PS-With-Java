package boj.구현;
import java.io.*;
import java.util.*;

public class Main_컬러볼_10800 {
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static int N, sum;
    private static Ball[] balls;
    private static long[] sums;
    private static class Ball implements Comparable<Ball> {
        int num;
        int color;
        int size;

        public Ball(int num, int color, int size) {
            this.num = num;
            this.color = color;
            this.size = size;
        }

        @Override
        public int compareTo(Ball o) {
            return this.size == o.size ? this.color - o.color : this.size - o.size;
        }

        @Override
        public String toString() {
            return "Ball{" +
                    "num=" + num +
                    ", color=" + color +
                    ", size=" + size +
                    '}';
        }
    }
    private static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        balls = new Ball[N];
        sums = new long[N+1];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
            int color = Integer.parseInt(line.nextToken());
            int size = Integer.parseInt(line.nextToken());
            balls[i] = new Ball(i,color,size);
            sums[color] += size;
            sum += size;
        }
        Arrays.sort(balls);
    }

    private static void solve() throws IOException {
        long[] results = new long[N];
        int i = N-1;
        while ( i > 0) {
            Queue<Ball> q = new LinkedList<>();
            q.add(balls[i]);
            sums[balls[i].color] -= balls[i].size;
            sum -= balls[i].size;
            while (i > 0 && q.peek().size == balls[i-1].size) {
                q.add(balls[i-1]);
                sums[balls[i-1].color] -= balls[i-1].size;
                sum -= balls[i-1].size;
                i--;
            }
            i--;
            while (!q.isEmpty()) {
                Ball next = q.poll();
                results[next.num] = sum - sums[next.color];
            }
        }
        for( i = 0 ; i < N; i++) {
            bufferedWriter.write(results[i]+"\n");
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.close();

    }
}