package boj.덱;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class boj_키로거_심정욱 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    static void solve() throws IOException {
        char[] inputs = bufferedReader.readLine().toCharArray();
        Deque<Character> deque = new ArrayDeque<>();
        Deque<Character> tempDeque = new ArrayDeque<>();
        for(int i = 0 ; i < inputs.length; i++) {
            switch (inputs[i]) {
                case '<':
                    if(!deque.isEmpty()) {
                        tempDeque.addLast(deque.pollLast());
                    }
                    break;
                case '>':
                    if(!tempDeque.isEmpty()) {
                        deque.addLast(tempDeque.pollLast());
                    }
                    break;
                case '-':
                    if(!deque.isEmpty()) {
                        deque.pollLast();
                    }
                    break;
                default:
                    deque.addLast(inputs[i]);
                    break;
            }
        }
        while(!tempDeque.isEmpty()) {
            deque.addLast(tempDeque.pollLast());
        }
        while(!deque.isEmpty()) {
            bufferedWriter.write(deque.pollFirst());
        }
        bufferedWriter.write("\n");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 0 ; i < T; i++) {
            solve();
        }
        bufferedReader.close();
        bufferedWriter.close();
    }
}
