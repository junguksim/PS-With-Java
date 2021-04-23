package boj.구현;

import java.io.*;
import java.util.*;

public class Main_낚시왕_17143 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int R,C,M, fisherPos, answer;
    private static ArrayList<Shark> sharks;
    private static int[] dx = {0,-1,1,0,0};
    private static int[] dy = {0,0,0,1,-1};
    private static Shark[][] map;

    private static class Shark {
        int r;
        int c;
        int velocity;
        int dir;
        int size;

        public Shark(int r, int c, int velocity, int dir, int size) {
            this.r = r;
            this.c = c;
            this.velocity = velocity;
            this.dir = dir;
            this.size = size;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    private static void input() throws IOException {
        StringTokenizer rcm = new StringTokenizer(bufferedReader.readLine());
        R = Integer.parseInt(rcm.nextToken());
        C = Integer.parseInt(rcm.nextToken());
        M = Integer.parseInt(rcm.nextToken());
        sharks = new ArrayList<>();
        map = new Shark[R+1][C+1];
        for(int i = 0 ; i < M; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int velocity = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            Shark shark = new Shark(r,c,velocity,dir,size);
            sharks.add(shark);
            map[r][c] = shark;
        }
        //System.out.println(sharks.toString());
    }

    private static void hunt() {
        for(int r = 1; r <= R; r++) {
            if(map[r][fisherPos] != null) {
                answer += map[r][fisherPos].size;
                sharks.remove(map[r][fisherPos]);
                break;
            }
        }
    }

    private static int changeDir(int dir) {
        switch (dir) {
            case 1:
                return 2;
            case 2:
                return 1;
            case 3:
                return 4;
            case 4:
                return 3;
        }
        return 0;
    }

    private static void moveSharks() {
        for(Shark shark : sharks) {
            int dir = shark.dir;
            int nr = shark.r;
            int nc = shark.c;
            for(int v = 0; v < shark.velocity; v++) {
                if(nr + dx[dir] < 1 || nr + dx[dir] > R || nc + dy[dir] < 1 || nc + dy[dir] > C) {
                    dir = changeDir(dir);
                }
                nr += dx[dir];
                nc += dy[dir];
            }
            shark.r = nr;
            shark.c = nc;
            shark.dir = dir;
        }
    }

    private static void killSharks() {
        map = new Shark[R+1][C+1];
        int size = sharks.size();
        for(int i = size - 1; i >= 0; i--) {
            Shark s = sharks.get(i);
            if(map[s.r][s.c] == null) {
                map[s.r][s.c] = s;
            } else {
                if(s.size > map[s.r][s.c].size) {
                    sharks.remove(map[s.r][s.c]);
                    map[s.r][s.c] = s;
                } else {
                    sharks.remove(s);
                }
            }
        }
    }

    private static void solve() throws IOException {
        fisherPos = 0;
        while (fisherPos < C) {
            fisherPos++;
            hunt();
            moveSharks();
            killSharks();
        }
        System.out.println(answer);
    }
}
