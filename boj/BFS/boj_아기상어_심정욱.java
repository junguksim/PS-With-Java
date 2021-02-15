package boj.BFS;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_아기상어_심정욱 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static int N, sharkSize = 2, answer, minX, minY, minDist, eatingCount;
    static Node shark;
    static int[][] map;
    static int[][] counts;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,1,-1};

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        map = new int[N][N];
        counts = new int[N][N];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0 ; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9) {
                    shark = new Node(i,j);
                    map[i][j] = 0;
                }
            }
        }
    }
    static boolean isInArea(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }
    static boolean isAbleToMove(int x, int y) {
        return map[x][y] <= sharkSize;
    }
    static boolean isEatable(int x, int y) {
        return map[x][y] != 0 && map[x][y] < sharkSize;
    }
    static void BFS(int x, int y) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(new Node(x, y));
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            int sx = node.x;
            int sy = node.y;
            for(int i = 0 ; i < 4; i++) {
                int nx = sx + dx[i];
                int ny = sy + dy[i];
                if(isInArea(nx, ny) && isAbleToMove(nx, ny) &&  counts[nx][ny] == 0) {
                    counts[nx][ny] = counts[sx][sy] + 1;
                    if(isEatable(nx, ny)) {
                        if(minDist > counts[nx][ny]) {
                            minDist = counts[nx][ny];
                            minX = nx;
                            minY = ny;
                        } else if(minDist == counts[nx][ny]) {
                            if(minX == nx) {
                                if(minY > ny) {
                                    minX = nx;
                                    minY = ny;
                                }
                            } else if(minX > nx) {
                                minX = nx;
                                minY = ny;
                            }
                        }
                    }
                    queue.add(new Node(nx, ny));
                }
            }
        }
    }

    static void solve() {
        while (true) {
            counts = new int[N][N];
            minX = Integer.MAX_VALUE;
            minY = Integer.MAX_VALUE;
            minDist = Integer.MAX_VALUE;
            BFS(shark.x, shark.y);

            if (minX != Integer.MAX_VALUE && minY != Integer.MAX_VALUE) {
                eatingCount++;
                map[minX][minY] = 0;
                shark.x = minX;
                shark.y = minY;
                answer += counts[minX][minY];

                if (eatingCount == sharkSize) {
                    sharkSize++;
                    eatingCount = 0;
                }
            } else {
                break;
            }
        }
        System.out.println(answer);
    }
    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
