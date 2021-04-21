package boj.구현;

import java.io.*;
import java.util.*;

public class Main_감시_15683 {
    private static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    private static int N,M,answer;
    private static int[][] map;
    private static ArrayList<Camera> cameras;
    // 1번 : RIGHT -> DOWN -> LEFT -> UP
    // 2번 : LEFT, RIGHT -> UP, DOWN -> LEFT, RIGHT -> UP, DOWN
    // 3번 : UP, RIGHT -> RIGHT, DOWN -> DOWN, LEFT -> LEFT, UP
    // 4번 : UP, LEFT, RIGHT -> UP, RIGHT, DOWN -> RIGHT, DOWN, LEFT -> DOWN, LEFT, UP
    private static final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3, WALL = 6, CAN_SEE = 9;
    private static int[][][] d = {{}, {{RIGHT},{DOWN},{LEFT},{UP}}, {{LEFT, RIGHT}, {UP, DOWN}, {LEFT, RIGHT}, {UP,DOWN}}, {{UP,RIGHT}, {RIGHT, DOWN}, {DOWN, LEFT}, {LEFT, UP}}, {{UP,LEFT,RIGHT},{ UP,RIGHT,DOWN},{RIGHT,DOWN,LEFT},{DOWN,LEFT,UP}}};

    private static class Camera {
        int x;
        int y;
        int type;

        public Camera(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        @Override
        public String toString() {
            return "Camera{" +
                    "x=" + x +
                    ", y=" + y +
                    ", type=" + type +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException {
        input();
        solve();
    }

    private static void input() throws IOException {
        StringTokenizer nm = new StringTokenizer(bufferedReader.readLine());
        N = Integer.parseInt(nm.nextToken());
        M = Integer.parseInt(nm.nextToken());
        map = new int[N][M];
        answer = Integer.MAX_VALUE;
        cameras = new ArrayList<>();
        for(int i = 0 ; i < N; i++) {
            StringTokenizer line = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < M; j++) {
                map[i][j] = Integer.parseInt(line.nextToken());
                if(map[i][j] > 0 && map[i][j] < 6) {
                    cameras.add(new Camera(i,j, map[i][j]));
                }
            }
        }
        ArrayList<Camera> newCameras = new ArrayList<>();
        for(Camera c : cameras) {
            if(c.type == 5) {
                show(map, c.x, c.y, UP);
                show(map, c.x, c.y, DOWN);
                show(map, c.x, c.y, RIGHT);
                show(map, c.x, c.y, LEFT);
            } else {
                newCameras.add(c);
            }
        }
        cameras = newCameras;
    }

    private static int getEdgeCount(int[][] map) {
        int sum = 0;
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < M; j++) {
                if(map[i][j] == 0) sum++;
            }
        }
        return sum;
    }

    private static void printMap(int[][] map) {
        for(int i = 0 ; i < N; i++) {
            for(int j = 0 ; j < M; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("====================");
    }

    private static int[][] copyMap(int[][] map) {
        int[][] result = new int[N][M];
        for(int i = 0 ; i < N; i++) {
            result[i] = map[i].clone();
        }
        return result;
    }


    private static void show(int[][] map, int cameraX, int cameraY, int showDir) {
        if(showDir == UP) {
            for(int x = cameraX; x >= 0; x--) {
                if(map[x][cameraY] == WALL) break;
                if(map[x][cameraY] == 0 || map[x][cameraY] == CAN_SEE) {
                    map[x][cameraY] = CAN_SEE;
                }
            }
        } else if(showDir == DOWN) {
            for(int x = cameraX; x < N; x++) {
                if(map[x][cameraY] == WALL) break;
                if(map[x][cameraY] == 0 || map[x][cameraY] == CAN_SEE) {
                    map[x][cameraY] = CAN_SEE;
                }
            }
        }else if(showDir == LEFT) {
            for(int y = cameraY; y >= 0; y--) {
                if(map[cameraX][y] == WALL) break;
                if(map[cameraX][y] == 0 ||map[cameraX][y] == CAN_SEE) {
                    map[cameraX][y] = CAN_SEE;
                }
            }
        }else if(showDir == RIGHT) {
            for(int y = cameraY; y < M; y++) {
                if(map[cameraX][y] == WALL) break;
                if(map[cameraX][y] == 0 ||map[cameraX][y] == CAN_SEE) {
                    map[cameraX][y] = CAN_SEE;
                }
            }
        }
    }

    private static void solve() throws IOException {
        answer = getEdgeCount(map);
        Queue<Camera> queue = new LinkedList<>(cameras);
        int cnt = 0;
        while (cnt < cameras.size()) {
            int[][] newMap = copyMap(map);
            for(Camera c : cameras) {
                int min = Integer.MAX_VALUE;
                int[][] leastEdgeMap = copyMap(newMap);
                //System.out.println("실행전 : " + getEdgeCount(leastEdgeMap));
                //printMap(leastEdgeMap);
                for(int i = 0 ; i < 4; i++) {
                    int[] nextDir = d[c.type][i];
                    int[][] beforeFourShow = copyMap(newMap);
                    for(int j = 0 ; j < nextDir.length; j++) {
                        show(beforeFourShow, c.x, c.y, nextDir[j]); // 볼 방향 다 보고 나서
                    }
                    int edges = getEdgeCount(beforeFourShow); // 사각지대 구하고
                    //System.out.println(edges);
                    //printMap(beforeFourShow);
                    if(min >= edges) {
                        leastEdgeMap = copyMap(beforeFourShow);
                        min = edges;
                    }
                } // 해당 카메라를 다 보고 나면,
                newMap = copyMap(leastEdgeMap);
            }
            int edgeCount = getEdgeCount(newMap);
            answer = Math.min(answer, edgeCount);
            queue.add(queue.poll());
            cnt++;
            cameras = new ArrayList<>(queue);
        }

        System.out.println(answer);
    }
}