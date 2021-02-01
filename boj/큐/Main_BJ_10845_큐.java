package boj.큐;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_BJ_10845_큐 {
    static Queue<Integer> queue = new LinkedList<>();
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int back;

    static void input() throws IOException {
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        String op = stringTokenizer.nextToken();
        switch (op) {
            case "push":
                int x = Integer.parseInt(stringTokenizer.nextToken());
                back = x;
                queue.add(x);
                break;
            case "pop":
                if (queue.isEmpty()) {
                    bufferedWriter.write("-1\n");
                } else {
                    bufferedWriter.write(queue.poll() + "\n");
                }
                break;
            case "size":
                bufferedWriter.write(queue.size() + "\n");
                break;
            case "empty":
                if (queue.isEmpty()) {
                    bufferedWriter.write("1\n");
                } else {
                    bufferedWriter.write("0\n");
                }
                break;
            case "front":
                if (queue.isEmpty()) {
                    bufferedWriter.write("-1\n");
                } else {
                    bufferedWriter.write(queue.peek() + "\n");
                }
                break;
            default:
                if (queue.isEmpty()) {
                    bufferedWriter.write("-1\n");
                } else {
                    bufferedWriter.write(back + "\n");
                }
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < N; i++) {
            input();
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
