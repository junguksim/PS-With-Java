package swea;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_무선충전 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int totalMoveTime, bcCount;
    private static int[][] map;
    private static Node[] aPath, bPath;
    private static BC[] bcs;
    private static int[] dx = {0,-1,0,1,0};
    private static int[] dy = {0,0,1,0,-1};

    private static class Node {
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

    private static class BC extends Node {
        int c;
        int p;

        @Override
        public String toString() {
            return "BC{" +
                    "c=" + c +
                    ", p=" + p +
                    '}';
        }

        public BC(int x, int y, int c, int p) {
            super(x, y);
            this.c = c;
            this.p = p;
        }
    }

    private static Node move(int sx, int sy, int dir) {
        return new Node(sx + dx[dir], sy + dy[dir]);
    }

    private static void input() throws IOException {
        map = new int[10][10];
        StringTokenizer mbc = new StringTokenizer(bufferedReader.readLine());
        totalMoveTime = Integer.parseInt(mbc.nextToken());
        bcCount = Integer.parseInt(mbc.nextToken());
        bcs = new BC[bcCount];
        aPath = new Node[totalMoveTime+1];
        aPath[0] = new Node(0,0);
        bPath = new Node[totalMoveTime+1];
        bPath[0] = new Node(9,9);
        StringTokenizer aMoves = new StringTokenizer(bufferedReader.readLine());
        StringTokenizer bMoves = new StringTokenizer(bufferedReader.readLine());
        for(int i = 1 ; i <= totalMoveTime; i++) {
            aPath[i] = move(aPath[i-1].x, aPath[i-1].y, Integer.parseInt(aMoves.nextToken()));
            bPath[i] = move(bPath[i-1].x, bPath[i-1].y, Integer.parseInt(bMoves.nextToken()));
        }
        for(int i = 0 ; i < bcCount; i++) {
            StringTokenizer bcInfo = new StringTokenizer(bufferedReader.readLine());
            int y = Integer.parseInt(bcInfo.nextToken()) - 1;
            int x = Integer.parseInt(bcInfo.nextToken()) - 1;
            bcs[i] = new BC(x, y, Integer.parseInt(bcInfo.nextToken()), Integer.parseInt(bcInfo.nextToken()));
        }
    }

    private static int getDistance(Node from, Node to) {
        return Math.abs(from.x - to.x) + Math.abs(from.y - to.y);
    }

    private static int getCharge(BC bc, Node a) {
        return getDistance(bc, a) <= bc.c ? bc.p : 0;
    }

    private static void solve(int t) throws IOException {
        int result = 0;
        for(int i = 0; i <= totalMoveTime; i++) {
            Node aPos = aPath[i];
            Node bPos = bPath[i];
            int max = 0;
            for(int a = 0 ; a < bcCount; a++) {
                int aSum = getCharge(bcs[a], aPos);
                for(int b = 0; b < bcCount; b++) {
                    int sum = 0;
                    int bSum =  getCharge(bcs[b], bPos);
                    if(a != b) {
                        sum = aSum + bSum;
                    } else {
                        sum = Math.max(aSum, bSum);
                    }
                    if(max < sum) max = sum;
                }
            }
            result += max;
        }
        bufferedWriter.write("#"+t + " " +result+"\n");
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(bufferedReader.readLine());
        for(int i = 1 ; i <= T; i++) {
            input();
            solve(i);
        }
        bufferedWriter.close();
        bufferedReader.close();
    }
}
