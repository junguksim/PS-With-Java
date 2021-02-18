package boj.BFS_DFS;

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_탈출_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int R,C, caveX, caveY, waterX, waterY, hogX, hogY, min;
    static char[][] map;
    static int[][] counts;
    static boolean[][] waterVisited, hogVisited;
    static Queue<Node> waterQueue;
    static int[] dy = {0,0,1,-1};
    static int[] dx = {-1,1,0,0};

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    static void input() throws IOException {
        StringTokenizer RC = new StringTokenizer(bufferedReader.readLine());
        R = Integer.parseInt(RC.nextToken());
        C = Integer.parseInt(RC.nextToken());
        map = new char[R][C];
        waterVisited = new boolean[R][C];
        hogVisited = new boolean[R][C];
        counts = new int[R][C];
        waterQueue = new LinkedList<>();
        for(int i = 0; i < R; i++) {
            map[i] = bufferedReader.readLine().toCharArray();
            for(int j = 0 ; j < C; j++) {
                counts[i][j] = Integer.MAX_VALUE;
                if(map[i][j] == 'D') {
                    caveX = i;
                    caveY = j;
                } else if(map[i][j] == '*') {
                    waterQueue.add(new Node(i, j));
                } else if(map[i][j] == 'S') {
                    hogX = i;
                    hogY = j;
                    counts[i][j] = 0;
                } else if(map[i][j] == 'X') {
                    waterVisited[i][j] = true;
                    hogVisited[i][j] = true;
                }
            }
        }
    }

    static void bfs() {
        //hogX + dx -> caveX, hogY + dy -> caveY 가 될 때 가지
        //waterX + dx != hogX 여야 한다.
        Queue<Node> hogQueue = new LinkedList<>();
        int waterCnt = waterQueue.size();
        int nextWaterCnt = waterCnt;
        int hogCnt = 1;
        int nextHogCnt = 1;
        hogVisited[hogX][hogY] = true;
        waterVisited[waterX][waterY] = true;
        hogQueue.add(new Node(hogX, hogY));
        boolean isWaterTurn = true;
        while (!hogQueue.isEmpty()) {
            if(isWaterTurn) {
                waterCnt = nextWaterCnt;
                nextWaterCnt = 0;
                while (!waterQueue.isEmpty() && waterCnt > 0) {
                    Node water = waterQueue.poll();
                    int wx = water.x;
                    int wy = water.y;
                    for(int i = 0 ; i < 4; i++) {
                        int nx = wx + dx[i];
                        int ny = wy + dy[i];
                        if(nx < 0 || ny < 0 || nx >= R || ny >= C) {
                            continue;
                        }
                        if(waterVisited[nx][ny] || (nx == caveX && ny == caveY)) {
                            continue;
                        }
                        waterQueue.add(new Node(nx,ny));
                        nextWaterCnt++;
                        waterVisited[nx][ny] = true;
                        hogVisited[nx][ny] = true;
                        //System.out.printf("물이 %d %d 방문\n", nx, ny);
                    }
                    waterCnt--;
                }
                isWaterTurn = false;
            } else {
                hogCnt = nextHogCnt;
                nextHogCnt = 0;
                while (!hogQueue.isEmpty() && hogCnt > 0) {
                    Node hog = hogQueue.poll();
                    int hx = hog.x;
                    int hy = hog.y;
                    for(int i = 0 ; i < 4; i++) {
                        int nx = hx + dx[i];
                        int ny = hy + dy[i];
                        if(nx < 0 || ny < 0 || nx >= R || ny >= C) {
                            continue;
                        }
                        if(hogVisited[nx][ny]) {
                            continue;
                        }
                        if(counts[nx][ny] > counts[hx][hy] + 1) {
                            counts[nx][ny] = counts[hx][hy] + 1;
                            hogQueue.add(new Node(nx,ny));
                            nextHogCnt++;
                            //System.out.printf("고슴도치 가 %d %d 방문\n", nx, ny);
                            hogVisited[nx][ny] = true;
                        }
                        if(nx == caveX && ny == caveY) {
                            min = counts[nx][ny];
                            //System.out.println("도착");
                            return;
                        }
                    }
                    hogCnt--;
                }
                isWaterTurn = true;
            }
        }
    }

    static void solve() {
        bfs();
        if(min == 0) {
            System.out.println("KAKTUS");
        } else{
            System.out.println(min);
        }

    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
        bufferedReader.close();
    }
}
