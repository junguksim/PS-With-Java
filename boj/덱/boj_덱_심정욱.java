package boj.덱;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class boj_덱_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static Deque<Integer> deque = new ArrayDeque<>();

    private static void func() throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        String op = stringTokenizer.nextToken();
        int num;
        switch (op) {
            case "push_front":
                num = Integer.parseInt(stringTokenizer.nextToken());
                deque.addFirst(num);
                break;
            case "push_back":
                num = Integer.parseInt(stringTokenizer.nextToken());
                deque.addLast(num);
                break;
            case "pop_front":
                if(deque.size() > 0) {
                    bufferedWriter.write(deque.pollFirst() + "\n");
                }
                else {
                    bufferedWriter.write("-1\n");
                }
                break;
            case "pop_back":
                if(deque.size() > 0) {
                    bufferedWriter.write(deque.pollLast() + "\n");
                }
                else {
                    bufferedWriter.write("-1\n");
                }
                break;
            case "size":
                bufferedWriter.write(deque.size()+"\n");
                break;
            case "empty":
                if(deque.size() > 0) {
                    bufferedWriter.write("0\n");
                }
                else {
                    bufferedWriter.write("1\n");
                }
                break;
            case "front":
                if(deque.size() > 0) {
                    bufferedWriter.write(deque.peekFirst() + "\n");
                }
                else {
                    bufferedWriter.write("-1\n");
                }
                break;
            case "back":
                if(deque.size() > 0) {
                    bufferedWriter.write(deque.peekLast() + "\n");
                }
                else {
                    bufferedWriter.write("-1\n");
                }
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < N; i++) {
            func();
        }
        bufferedWriter.close();
        bufferedReader.close();
    }


}
