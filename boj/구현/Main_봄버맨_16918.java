package boj.구현;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_봄버맨_16918 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int R,C,N;
    static char[][] map;
    static ArrayList<Bomb> bombs;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};

    static class Bomb {
        int x;
        int y;

        public Bomb(int x, int y) {
            this.x = x;
            this.y = y;
        }

    }

    /***
     *  R * C 격자판
     *  폭탄 3초 뒤 폭발 -> 빈 칸, 상하좌우 폭발
     *  연쇄폭발 X
     *  첫 1초 아무것도 안함
     *  두번째 1초 -> 설치 안된 모든 칸에 설치
     *  세번째 1초 -> 3초전에 설치된 폭탄 폭발
     */

    static void input() throws IOException {
        StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        bombs = new ArrayList<>();
        map = new char[R][C];
        for(int i = 0 ; i < R; i++) {
            char[] line = bufferedReader.readLine().toCharArray();
            for(int j = 0; j < C; j++) {
                map[i][j] = line[j];
                if(line[j] == 'O') {
                    map[i][j] = '1';
                }
            }
        }
    }

    static void makeBlankToBomb() {
        for(int i = 0 ; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] == '.') {
                    map[i][j] = '0';
                }
            }
        }
    }

    static void addOneSecToBombs() {
        for(int i = 0 ; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] != '.') {
                    map[i][j] = Character.forDigit(Character.getNumericValue(map[i][j]) + 1, 10);
                }
            }
        }
    }

    static void checkBoom() {
        Queue<Bomb> queue = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        for(int i = 0 ; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] == '3') {
                    queue.add(new Bomb(i,j));
                }
            }
        }
        while(!queue.isEmpty()) {
            Bomb b = queue.poll();
            int x = b.x;
            int y = b.y;
            map[x][y] = '.';
            visited[x][y] = true;
            for(int k= 0 ; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if(visited[nx][ny]) continue;
                map[nx][ny] = '.';
            }
        }
    }

    static void printMap() {
        for(int i = 0 ; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(map[i][j] != '.') {
                    map[i][j] = 'O';
                }
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    static void solve() {
        for(int i = 1; i < N; i++) { // N초 동안
            addOneSecToBombs();
            if(i % 2 != 0) {
                makeBlankToBomb();
            } else {
                checkBoom();
            }
        }
        printMap();
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
