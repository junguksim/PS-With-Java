package boj.그리디;

import java.io.*;
import java.util.*;

public class Main_강의실배정_11000 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static Lecture[] lectures;
    static PriorityQueue<Integer> ends;

    static class Lecture{
        int s;
        int t;

        public Lecture(int s, int t) {
            this.s = s;
            this.t = t;
        }
    }
    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        ends = new PriorityQueue<>();
        lectures = new Lecture[N];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            lectures[i] = new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(lectures, (o1, o2) -> o1.s == o2.s ? o1.t - o2.t : o1.s - o2.s);
        ends.offer(lectures[0].t);
        for(int i = 1; i < N; i++) {
            if(ends.peek() <= lectures[i].s) {
                ends.poll();
            }
            ends.offer(lectures[i].t);
        }
        bufferedWriter.write(ends.size() + "\n");
    }


    public static void main(String[] args) throws IOException {
        input();
        bufferedWriter.close();
        bufferedReader.close();
    }
}
