package swea;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_프로세서연결하기 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, min, totalCnt, max;
    static int[][] map;
    static ArrayList<Node> cores;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }
    }


    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        min = Integer.MAX_VALUE;
        max = 0;
        map = new int[N][N];
        totalCnt = 0;
        cores = new ArrayList<>();
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if((i == 0 || j == 0 || i == N-1 || j == N-1) && map[i][j] == 1) continue;
                if(map[i][j] == 1) {
                    cores.add(new Node(i,j));
                    totalCnt++;
                }
            }
        }
    }

    static boolean isAvailable(int r, int c, int d) {
        int nr = r, nc = c;
        while(true) {
            nr += dx[d];
            nc += dy[d];
            if(nr < 0 || nr >= N || nc < 0 || nc >= N) break;
            if(map[nr][nc] >= 1) {
                return  false;
            }
        }
        return true;
    }

    static void setStatus(int r, int c, int d, int s) {
        int nr = r, nc = c;
        while(true) {
            nr += dx[d];
            nc += dy[d];
            if(nr < 0 || nr >= N || nc < 0 || nc >= N) break;
            map[nr][nc] = s;
        }
    }
    static int getLength() {
        int lCnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j]==2) ++lCnt;
            }
        }
        return lCnt;
    }
    private static void go(int index,int cCnt) { //cCnt : 현재까지 연결된 코어수
        // 가지치기
        if(totalCnt-index+cCnt<max) return; // totalCnt-index: 남은 코어수
        if(index == totalCnt) {
            int res = getLength();
            if(max<cCnt) {
                max = cCnt;
                min = res;
            }else if(max==cCnt) {
                if(min>res) min = res;
            }
            return;
        }
        Node cur = cores.get(index);
        int r = cur.x;
        int c = cur.y;
        for (int d = 0; d < 4; d++) {
            if(isAvailable(r, c, d)) { // 해당방향으로 가장자리까지 닿을 수 있다면 전원연결 가능
                setStatus(r, c, d, 2);
                go(index+1,cCnt+1); // 다음 코어로 넘어감
                setStatus(r,c,d,0);
            }
        }
        go(index+1,cCnt); // 해당 코어를 전원에 연결하지 않고 다음 코어로 넘어감
    }
    static void solve(int idx) throws IOException {
        go(0,0);
        bufferedWriter.write("#"+idx+" "+min+"\n");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 1; i <= T; i++) {
            input();
            solve(i);
        }
        bufferedWriter.close();
        bufferedReader.close();

    }
}
