package boj.BFS_DFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main_알파벳_1987 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int R,C, ans;
    static Node[][] map;
    static boolean[] isCharExist;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Node {
        int x;
        int y;
        char alphabet;

        public Node(int x, int y, char alphabet) {
            this.x = x;
            this.y = y;
            this.alphabet = alphabet;
        }
    }

    static void input() throws IOException {
        StringTokenizer rc = new StringTokenizer(bufferedReader.readLine());
        R = Integer.parseInt(rc.nextToken());
        C = Integer.parseInt(rc.nextToken());
        map = new Node[R][C];
        ans = 1;
        isCharExist = new boolean[26];
        for(int i = 0 ; i < R; i++) {
            char[] line = bufferedReader.readLine().toCharArray();
            for(int j = 0 ; j  <C; j++) {
                map[i][j] = new Node(i, j , line[j]);
            }
        }
        isCharExist[charToAscii(map[0][0].alphabet)] = true;
    }

    static int charToAscii(char ch) {
        return (int)ch - 65;
    }

    static void dfs(int sx, int sy, int depth) {
        isCharExist[charToAscii(map[sx][sy].alphabet)] = true;
        for(int i = 0 ; i < 4; i++) {
            int nx = sx + dx[i];
            int ny = sy + dy[i];
            if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
            if(!isCharExist[charToAscii(map[nx][ny].alphabet)]) {
                dfs(nx, ny, depth + 1);
            }
        }
        isCharExist[charToAscii(map[sx][sy].alphabet)] = false;
        ans = Math.max(ans, depth);
    }

    static void solve() {
        dfs(0,0, 1);
        System.out.println(ans);
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }
}
