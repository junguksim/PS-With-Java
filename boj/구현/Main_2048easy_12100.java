package boj.구현;
import java.io.*;
import java.util.StringTokenizer;

public class Main_2048easy_12100 {
    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, max;
    static int[][] map;

    static void input() throws IOException {
        N = Integer.parseInt(bufferedReader.readLine());
        map = new int[N][N];
        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bufferedReader.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static boolean isOut(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= N || ny >= N;
    }

    static int[][] swap(int[][] map, int x, int y, int nx, int ny) {
        int temp = map[x][y];
        map[x][y] = map[nx][ny];
        map[nx][ny] = temp;
        return map;
    }

    static void findMax(int[][] map) {
        for(int i = 0 ; i < N; i++) {
            for(int j = 0; j < N; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
    }

    static int[][] shift(int[][] map, int dir, boolean[][] summed, int depth) {
        if(depth == 5) {
            return null;
        }
        //System.out.println(depth + "회차 : " + "방향 : " + dir);
        switch (dir) {
            case 0:
                for(int x = 1; x < N; x++) {
                    for(int y = 0; y < N; y++) {
                        int nx = x;
                        while (!isOut(nx-1, y) && map[nx-1][y] == 0) {
                            map = swap(map, nx, y, nx-1, y);
                            nx--;
                        }
                        int nnx = nx;
                        while (!isOut(nnx-1, y)) {
                            if(map[nnx][y] == map[nnx-1][y] && !summed[nnx-1][y] && !summed[nnx][y]) {
                                map[nnx-1][y] *= 2;
                                map[nnx][y] = 0;
                                summed[nnx-1][y] = true;
                            } else {
                                break;
                            }
                            nnx--;
                        }
                    }
                }
                break;
            case 1:
                for(int x = 0; x < N; x++) {
                    for(int y = N-2; y >= 0; y--) {
                        int ny = y;
                        while (!isOut(x, ny+1) && map[x][ny+1] == 0) {
                            map = swap(map, x, ny, x, ny+1);
                            ny++;
                        }
                        int nny = ny;
                        while (!isOut(x, nny+1)) {
                            if(map[x][nny] == map[x][nny+1] && !summed[x][nny+1] && !summed[x][nny]) {
                                map[x][nny+1] *= 2;
                                map[x][nny] = 0;
                                summed[x][nny+1] = true;
                            } else {
                                break;
                            }
                            nny++;
                        }
                    }
                }
                break;
            case 2:
                for(int x = N-2; x >= 0; x--) {
                    for(int y = 0; y < N; y++) {
                        int nx = x;
                        while (!isOut(nx+1, y) && map[nx+1][y] == 0) {
                            map = swap(map, nx, y, nx+1, y);
                            nx++;
                        }
                        int nnx = nx;
                        while (!isOut(nnx+1, y)) {
                            if(map[nnx][y] == map[nnx+1][y] && !summed[nnx+1][y] && !summed[nnx][y]) {
                                map[nnx+1][y] *= 2;
                                map[nnx][y] = 0;
                                summed[nnx+1][y] = true;
                            } else {
                                break;
                            }
                            nnx++;
                        }
                    }
                }
                break;
            case 3:
                for(int x = 0; x < N; x++) {
                    for(int y = 1; y < N; y++) {
                        int ny = y;
                        while (!isOut(x, ny-1) && map[x][ny-1] == 0) {
                            map = swap(map, x, ny, x, ny-1);
                            ny--;
                        }
                        int nny = ny;
                        while (!isOut(x, nny-1)) {
                            if(map[x][nny] == map[x][nny-1] && !summed[x][nny-1] && !summed[x][nny]) {
                                map[x][nny-1] *= 2;
                                map[x][nny] = 0;
                                summed[x][nny-1] = true;
                            } else {
                                break;
                            }
                            nny--;
                        }
                    }
                }
                break;
        }
        findMax(map);
        for(int i = 0 ; i < 4; i++) {
            shift(cloneMap(map), i, new boolean[N][N], depth+1);
        }
        return map;
    }

    static int[][] cloneMap(int[][] map) {
        int[][] clone = new int[N][N];
        for(int i = 0; i < N; i++) {
            clone[i] = map[i].clone();
        }
        return clone;
    }

    static void solve() {
        for(int j = 0; j < 4; j++) {
            shift(cloneMap(map), j, new boolean[N][N], 0);
        }
        System.out.println(max);
    }


    public static void main(String[] args) throws IOException {
        input();
        solve();
    }
}
