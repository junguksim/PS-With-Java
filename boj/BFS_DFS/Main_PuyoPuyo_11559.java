package boj.BFS_DFS;

import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Main_PuyoPuyo_11559 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static char[][] map;
    private static ArrayList<Node> puyos;
    private static boolean[][] picked;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static boolean canBoom;
    private static int answer;

    private static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    private static void input() throws IOException {
        map = new char[12][6];
        puyos = new ArrayList<>();
        picked = new boolean[12][6];
        for(int i = 0; i < 12; i++) {
            char[] line = bufferedReader.readLine().toCharArray();
            for(int j = 0 ; j < 6; j++) {
                map[i][j] = line[j];
                if(map[i][j] != '.') {
                    puyos.add(new Node(i,j));
                }
            }
        }
    }

    private static void shiftToDown() {
        for(int y = 0; y < 6; y++) {
            int bottom = 11;
            for(int x = 11; x >= 0; x--) {
                if(map[x][y] != '.') {
                    char temp = map[x][y];
                    map[x][y] = '.';
                    map[bottom][y] = temp;
                    bottom--;
                }
            }
        }
        //printMap();
        findWillBoomPuyos();
    }

    private static void boom(boolean[][] willBoom) {
        for(int i = 0 ; i < 12; i++) {
            for(int j = 0 ; j < 6; j++) {
                if(willBoom[i][j]) {
                    map[i][j] = '.';
                }
            }
        }
        shiftToDown();
    }

    private static void printMap() {
        for(int i = 0 ; i < 12; i++) {
            for(int j = 0 ; j < 6; j++) {
                System.out.print(map[i][j]);
            }
            System.out.println();
        }
    }

    private static void findWillBoomPuyos() {
        boolean[][] willBoom = new boolean[12][6];
        canBoom = false;
        for(Node puyo : puyos) {
            if(willBoom[puyo.x][puyo.y] || map[puyo.x][puyo.y] == '.') continue;
            Deque<Node> deque = new ArrayDeque<>();
            deque.add(puyo);
            boolean[][] visited = new boolean[12][6];
            ArrayList<Node> booms = new ArrayList<>();
            booms.add(puyo);
            while (!deque.isEmpty()) {
                Node p = deque.poll();
                int px = p.x;
                int py = p.y;
                visited[px][py] = true;
                for(int i = 0 ; i < 4; i++) {
                    int nx = px + dx[i];
                    int ny = py + dy[i];
                    if(nx < 0 || ny < 0 || nx >= 12 || ny >= 6) continue;
                    if(visited[nx][ny]) continue;
                    if(map[nx][ny] == map[px][py]) {
                        visited[nx][ny] = true;
                        deque.add(new Node(nx, ny));
                        booms.add(new Node(nx, ny));
                    }
                }
            }
            if(booms.size() >= 4) {
                canBoom = true;
                for(Node boom : booms) {
                    willBoom[boom.x][boom.y] = true;
                }
            }
        }
        if(!canBoom) return;
        answer++;
//        for(int i = 0 ; i < 12; i++) {
//            for(int j = 0 ; j < 6; j++) {
//                if(willBoom[i][j]) System.out.print("O ");
//                else System.out.print("X ");
//            }
//            System.out.println();
//        }
//        System.out.println();
        boom(willBoom);
    }

    private static char[][] cloneMap() {
        char[][] result = new char[12][6];
        for(int i = 0 ; i < 12; i++) {
            result[i] = map[i].clone();
        }
        return result;
    }

    private static void solve() throws IOException {
        findWillBoomPuyos();
        System.out.println(answer);
    }
}
