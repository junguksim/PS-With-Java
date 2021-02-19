package boj.그리디;

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_택배_심정욱 {
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N,C,M;
    static Village[] villages;

    static class Village {
        int sendNo;
        int receiveNo;
        int count;

        public Village(int sendNo, int receiveNo, int count) {
            this.sendNo = sendNo;
            this.receiveNo = receiveNo;
            this.count = count;
        }
    }
    static void input() throws IOException {
        StringTokenizer nc = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nc.nextToken());
        C = Integer.parseInt(nc.nextToken());
        M = Integer.parseInt(bufferedReader.readLine());
        villages = new Village[N];
        for(int i = 0 ; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            villages[i] = (new Village(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken())));
        }
    }

    static void solve() {

    }

    public static void main(String[] args) {

    }
}
