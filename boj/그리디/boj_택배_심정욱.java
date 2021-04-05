package boj.그리디;

import java.io.*;
import java.util.*;

public class boj_택배_심정욱 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N,C,M;
    static int[] villages;
    static Box[] boxes;

    static class Box implements Comparable<Box> {
        int sendNo;
        int receiveNo;
        int count;

        public Box(int sendNo, int receiveNo, int count) {
            this.sendNo = sendNo;
            this.receiveNo = receiveNo;
            this.count = count;
        }

        @Override
        public int compareTo(Box o) {
            return this.receiveNo == o.receiveNo ? this.sendNo - o.sendNo : this.receiveNo - o.receiveNo ;
        }

        @Override
        public String toString() {
            return "Village{" +
                    "sendNo=" + sendNo +
                    ", receiveNo=" + receiveNo +
                    ", count=" + count +
                    '}';
        }
    }
    static void input() throws IOException {
        StringTokenizer nc = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nc.nextToken());
        C = Integer.parseInt(nc.nextToken());
        M = Integer.parseInt(bufferedReader.readLine());
        boxes = new Box[M];
        villages = new int[N+1];
        for(int i = 0 ; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            boxes[i] = (new Box(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }
        Arrays.sort(boxes);
    }

    static void solve() {
        int boxCount = 0;
        for(Box box : boxes) {
            int sendNo = box.sendNo;
            int receiveNo = box.receiveNo;
            int count = box.count;
            int max = 0;
            boolean isLoad = true;
            for(int i = sendNo; i < receiveNo; i++) {
                if(villages[i] >= C) {
                    isLoad = false;
                    break;
                }
                max = Math.max(max, villages[i]);
            }
            if(isLoad) {
                int unloads = C - max;
                if(unloads > count) {
                    unloads = count;
                }
                boxCount += unloads;

                for(int i = sendNo; i < receiveNo; i++) {
                    villages[i] += unloads;
                }
            }
        }
        System.out.println(boxCount);
    }

    public static void main(String[] args) throws Exception{
        input();
        solve();
        bufferedReader.close();
        bufferedWriter.close();
    }
}
